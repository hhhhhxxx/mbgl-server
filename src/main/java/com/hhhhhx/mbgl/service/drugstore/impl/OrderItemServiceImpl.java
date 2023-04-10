package com.hhhhhx.mbgl.service.drugstore.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.entity.OrderItem;
import com.hhhhhx.mbgl.mapper.OrderItemMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhhhx.mbgl.param.drugstore.order.OrderItemPageParam;
import com.hhhhhx.mbgl.service.drugstore.IOrderItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {

    @Override
    public IPage<OrderItem> pageList(OrderItemPageParam param) {
        IPage<OrderItem> page = new Page<>(param.getPageIndex(), param.getPageSize());

        return this.lambdaQuery().eq(param.getOrderId() != null, OrderItem::getOrderId, param.getOrderId())
                .page(page);
    }

    @Override
    @Transactional
    public Boolean deleteByOrderId(Integer orderId) {

        LambdaQueryWrapper<OrderItem> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(OrderItem::getOrderId, orderId);
        return this.remove(wrapper);
    }
}
