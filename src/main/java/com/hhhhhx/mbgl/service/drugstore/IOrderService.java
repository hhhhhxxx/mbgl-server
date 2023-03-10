package com.hhhhhx.mbgl.service.drugstore;

import com.hhhhhx.mbgl.dto.OrderDTO;
import com.hhhhhx.mbgl.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhhhx.mbgl.param.drugstore.order.OrderPayParam;

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
}
