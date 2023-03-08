package com.hhhhhx.mbgl.service.drugstore.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ArrayUtil;
import com.hhhhhx.mbgl.dto.StockDTO;
import com.hhhhhx.mbgl.entity.Order;
import com.hhhhhx.mbgl.entity.Stock;
import com.hhhhhx.mbgl.exception.MbglServiceException;
import com.hhhhhx.mbgl.mapper.OrderMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhhhx.mbgl.mapper.StockMapper;
import com.hhhhhx.mbgl.massage.value.StockMessage;
import com.hhhhhx.mbgl.param.drugstore.order.AddressParam;
import com.hhhhhx.mbgl.param.drugstore.order.OrderPayParam;
import com.hhhhhx.mbgl.param.drugstore.order.Shop;
import com.hhhhhx.mbgl.service.drugstore.IOrderService;
import com.hhhhhx.mbgl.service.drugstore.IStockService;
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

    @Override
    @Transactional
    public Boolean pay(OrderPayParam param) {

        Integer userId = param.getUserId();

        AddressParam addressParam = param.getAddressParam();

        List<Shop> shopList = param.getShopList();
        // 判断库存
        List<Integer> drugIds = shopList.stream().map(Shop::getId).collect(Collectors.toList());

        List<StockDTO> stockDTOList = stockService.getStockDTOList(drugIds);

        // if (stockDTOList.size() != drugIds.size()) {
        //     throw new MbglServiceException(StockMessage.NO_STOCK);
        // }
        //
        // for (int i = 0; i < stockDTOList.size(); i++) {
        //     if (stockDTOList.get(i).getDrugId() != shopList.get(i).getId() ||
        //             stockDTOList.get(i).getQuantity() < shopList.get(i).getQuantity()) {
        //         throw new MbglServiceException(StockMessage.NO_STOCK);
        //     }
        // }


        // 扣除库存
        List<Stock> stockListByDrugIds = stockService.getStockListByDrugIds(drugIds);

        // 返回更新stock
        List<Stock> updateList = judgeStock(shopList, stockListByDrugIds);

        if (updateList == null || updateList.isEmpty()) {
            throw new MbglServiceException(StockMessage.CAL_ERROR);
        }

        // int updateRow = stockService.updateBatchById(updateList);

        // log.error("自定义批量更新返回值{}", updateRow);
        //
        // if (updateRow != updateList.size()) {
        //     throw new MbglServiceException(StockMessage.UPDATE_ERROR);
        // }
        // 生成订单


        // 计算价格 扣除费用

        //
        return true;
    }


    private List<Stock> judgeStock(List<Shop> shopList, List<Stock> stockList) {
        List<Stock> updateList = new ArrayList<>(shopList.size());

        boolean error = false;

        Iterator<Shop> shopIterator = shopList.iterator();
        Iterator<Stock> stockIterator = stockList.iterator();

        Shop shop = shopIterator.next();
        Stock stock = stockIterator.next();

        while (true) {

            if (!shop.getId().equals(stock.getDrugId()) || error) {
                error = true;
                break;
            }

            // 够减
            if (shop.getQuantity() <= stock.getQuantity()) {
                // 设置要扣的库存 而不是覆盖更新
                stock.setQuantity(shop.getQuantity());
                updateList.add(stock);

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
                    // 设置要扣的库存
                    updateList.add(stock);
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

        if(error) {
            return null;
        }
        return updateList;
    }
}
