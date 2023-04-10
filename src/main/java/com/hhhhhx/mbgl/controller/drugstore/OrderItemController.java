package com.hhhhhx.mbgl.controller.drugstore;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hhhhhx.mbgl.entity.OrderItem;
import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.param.drugstore.order.OrderItemPageParam;
import com.hhhhhx.mbgl.service.drugstore.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
@RestController
@RequestMapping("/orderitem")
public class OrderItemController {

    @Autowired
    private IOrderItemService orderItemService;

    @GetMapping("/page/list")
    public RestResponse<IPage<OrderItem>> pageList(@Valid OrderItemPageParam param) {
        return RestResponse.ok(orderItemService.pageList(param));
    }

    @GetMapping("/get/{id}")
    public RestResponse<OrderItem> getOne(@NotNull @PathVariable("id") Integer id) {
        return RestResponse.ok(orderItemService.getById(id));
    }

    @PostMapping("/su")
    public RestResponse<Boolean> pageList(@RequestBody OrderItem orderItem) {
        return RestResponse.ok(orderItemService.saveOrUpdate(orderItem));
    }
    @PostMapping("/delete/{id}")
    public RestResponse<Boolean> delete(@NotNull @PathVariable("id") Integer id) {
        return RestResponse.ok(orderItemService.removeById(id));
    }
}
