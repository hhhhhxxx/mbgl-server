package com.hhhhhx.mbgl.mapper;

import com.hhhhhx.mbgl.dto.OrderDTO;
import com.hhhhhx.mbgl.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {
    List<OrderDTO> getOrderDTOList(@Param("user_id") Integer userId, @Param("order_id") Integer orderId);
}
