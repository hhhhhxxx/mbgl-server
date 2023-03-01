package com.hhhhhx.mbgl.service.drugstore.impl;

import com.hhhhhx.mbgl.entity.Order;
import com.hhhhhx.mbgl.mapper.OrderMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhhhx.mbgl.service.drugstore.IOrderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
