package com.hhhhhx.mbgl.service.drugstore;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hhhhhx.mbgl.dto.OrderDTO;
import com.hhhhhx.mbgl.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhhhx.mbgl.param.drugstore.order.OrderPayParam;
import com.hhhhhx.mbgl.param.drugstore.order.OrderPrePayParam;
import com.hhhhhx.mbgl.param.drugstore.order.OrderPageParam;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
public interface IOrderService extends IService<Order> {

    Boolean pay(OrderPayParam param);

    List<OrderDTO> getOrderDTOList(Integer userId);

    OrderDTO getOne(Integer userId, Integer orderId);

    Boolean payPre(OrderPrePayParam param);

    IPage<Order> pageList(OrderPageParam param);

    Boolean deleteById(Integer id);
}
