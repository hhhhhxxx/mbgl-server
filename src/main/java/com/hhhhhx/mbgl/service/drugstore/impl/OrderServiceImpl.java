package com.hhhhhx.mbgl.service.drugstore.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ArrayUtil;
import com.hhhhhx.mbgl.dto.StockCombineDTO;
import com.hhhhhx.mbgl.dto.StockItemWithValueDTO;
import com.hhhhhx.mbgl.entity.Charge;
import com.hhhhhx.mbgl.entity.Order;
import com.hhhhhx.mbgl.entity.OrderItem;
import com.hhhhhx.mbgl.entity.Stock;
import com.hhhhhx.mbgl.entity.enums.OrderState;
import com.hhhhhx.mbgl.exception.MbglServiceException;
import com.hhhhhx.mbgl.mapper.OrderMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhhhx.mbgl.mapper.StockMapper;
import com.hhhhhx.mbgl.massage.value.StockMessage;
import com.hhhhhx.mbgl.param.drugstore.order.AddressParam;
import com.hhhhhx.mbgl.param.drugstore.order.OrderPayParam;
import com.hhhhhx.mbgl.param.drugstore.order.Shop;
import com.hhhhhx.mbgl.service.drugstore.IChargeService;
import com.hhhhhx.mbgl.service.drugstore.IOrderItemService;
import com.hhhhhx.mbgl.service.drugstore.IOrderService;
import com.hhhhhx.mbgl.service.drugstore.IStockService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {


    @Autowired
    IStockService stockService;

    @Autowired
    IOrderItemService orderItemService;

    @Autowired
    IChargeService chargeService;

    @Override
    @Transactional
    public Boolean pay(OrderPayParam param) {

        Integer userId = param.getUserId();

        AddressParam addressParam = param.getAddressParam();

        List<Shop> shopList = param.getShopList();


        // 判断库存
        List<Integer> drugIds = shopList.stream().map(Shop::getId).collect(Collectors.toList());

        List<StockCombineDTO> stockQuantityList = stockService.getStockCombineDTOByDrugIds(drugIds);


        // 先判断有没有库存
        if (stockQuantityList.size() != drugIds.size()) {
            throw new MbglServiceException(StockMessage.NO_STOCK);
        }
        for (int i = 0; i < stockQuantityList.size(); i++) {
            if (stockQuantityList.get(i).getDrugId() != shopList.get(i).getId() ||
                    stockQuantityList.get(i).getQuantity() < shopList.get(i).getQuantity()) {
                throw new MbglServiceException(StockMessage.NO_STOCK);
            }
        }


        // 查询库存
        List<StockItemWithValueDTO> stockPriceList = stockService.getStockItemWithValueDTOByDrugIds(drugIds);

        // 返回更新stock
        JudgeResult judgeResult = judgeStock(shopList, stockPriceList);
        List<Stock> updateList = judgeResult.getStockList();
        List<OrderItem> orderItemList = judgeResult.getOrderItemList();
        Integer sum = judgeResult.getSum();

        if (updateList == null || updateList.isEmpty()) {
            throw new MbglServiceException(StockMessage.CAL_ERROR);
        }


        // 做批量更新
        if (!stockService.updateManyStock(updateList)) {
            throw new MbglServiceException(StockMessage.UPDATE_ERROR);
        }

        // 生成订单
        Order order = new Order();

        order.setUserId(userId);
        order.setAddress(addressParam.getAddress());
        order.setAddressName(addressParam.getName());
        order.setAddressPhone(addressParam.getPhone());
        order.setAddressArea(addressParam.getArea());
        order.setStep(OrderState.HAS_PAY.getCode());
        order.setPay(OrderState.HAS_PAY.getCode());


        if (!this.save(order)) throw new MbglServiceException();

        // 插入成功会注入id
        Integer orderMainId = order.getId();

        // 生成订单子项目

        orderItemList.forEach(e -> e.setOrderId(orderMainId));

        if (!orderItemService.saveBatch(orderItemList)) {
            throw new MbglServiceException(StockMessage.UPDATE_ERROR);
        }

        // 计算价格 扣除费用
        Charge charge = new Charge();

        charge.setCost(sum);
        charge.setOrderId(orderMainId);
        charge.setPayment(1);

        if (!chargeService.save(charge)) {
            throw new MbglServiceException();
        }

        return true;
    }

    @Data
    @AllArgsConstructor
    class JudgeResult {
        List<Stock> stockList;
        List<OrderItem> orderItemList;
        Integer sum;
    }

    private JudgeResult judgeStock(List<Shop> shopList, List<StockItemWithValueDTO> stockList) {
        List<Stock> updateList = new ArrayList<>(shopList.size());
        List<OrderItem> orderItemList = new ArrayList<OrderItem>(shopList.size());

        Integer sum = 0;
        boolean error = false;

        Iterator<Shop> shopIterator = shopList.iterator();
        Iterator<StockItemWithValueDTO> stockIterator = stockList.iterator();

        Shop shop = shopIterator.next();
        StockItemWithValueDTO stock = stockIterator.next();

        while (true) {

            if (!shop.getId().equals(stock.getDrugId()) || error) {
                error = true;
                break;
            }

            // 够减
            if (shop.getQuantity() <= stock.getQuantity()) {
                // 设置要扣的库存 而不是覆盖更新
                stock.setQuantity(shop.getQuantity());
                updateList.add(BeanUtil.toBean(stock, Stock.class));

                OrderItem orderItem = new OrderItem();
                orderItem.setQuantity(shop.getQuantity());
                orderItem.setStockId(stock.getId());
                orderItem.setPrice(stock.getPrice() * shop.getQuantity());

                sum += orderItem.getPrice();

                orderItemList.add(orderItem);

                if (!shopIterator.hasNext()) {
                    break;
                }

                // 下一个商品
                shop = shopIterator.next();
            } else {
                // 最后一个没满足 或者 数据库查询异常
                if (!stock.getQuantity().equals(0)) {
                    // 不够减
                    shop.setQuantity(shop.getQuantity() - stock.getQuantity());

                    OrderItem orderItem = new OrderItem();
                    // 全扣
                    orderItem.setQuantity(stock.getQuantity());
                    orderItem.setStockId(stock.getId());
                    orderItem.setPrice(stock.getPrice() * stock.getQuantity());

                    sum += orderItem.getPrice();

                    orderItemList.add(orderItem);

                    // 设置要扣的库存
                    updateList.add(BeanUtil.toBean(stock, Stock.class));
                } else {
                    // 出现0数量 查询异常
                    error = true;
                    break;
                }

            }


            // 跳过 前一个库存
            while (stockIterator.hasNext()) {
                stock = stockIterator.next();
                if (stock.getDrugId().equals(shop.getId())) {
                    break;
                }
            }
        }

        if (error) {
            return null;
        }

        return new JudgeResult(updateList,orderItemList,sum);
    }
}
