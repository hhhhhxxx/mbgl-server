package com.hhhhhx.mbgl.controller.drugstore;


import com.hhhhhx.mbgl.dto.DrugInfoDTO;
import com.hhhhhx.mbgl.dto.OrderDTO;
import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.param.drugstore.order.OrderPayParam;
import com.hhhhhx.mbgl.param.drugstore.order.OrderPrePayParam;
import com.hhhhhx.mbgl.service.drugstore.IOrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/pay")
    public RestResponse<Boolean> pay(@RequestBody @Valid OrderPayParam param) {

        return RestResponse.ok(orderService.pay(param));
    }

    /*支付处方订单*/
    @PostMapping("/pre/pay")
    public RestResponse<Boolean> payPre(@RequestBody @Valid OrderPrePayParam param) {

        return RestResponse.ok(orderService.payPre(param));
    }

    @GetMapping("/list/{userId}")
    public RestResponse<List<OrderDTO>> getOrderDTO(@PathVariable Integer userId) {

        return RestResponse.ok(orderService.getOrderDTOList(userId));
    }

    @GetMapping("/get/{userId}/{orderId}")
    public RestResponse<OrderDTO> getOne(@PathVariable("userId") Integer userId, @PathVariable("orderId") Integer orderId) {

        return RestResponse.ok(orderService.getOne(userId, orderId));
    }
}
