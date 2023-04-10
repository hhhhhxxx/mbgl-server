package com.hhhhhx.mbgl.service.drugstore;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hhhhhx.mbgl.entity.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhhhx.mbgl.param.drugstore.order.OrderItemPageParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
public interface IOrderItemService extends IService<OrderItem> {

    IPage<OrderItem> pageList(OrderItemPageParam param);

    Boolean deleteByOrderId(Integer orderId);
}
