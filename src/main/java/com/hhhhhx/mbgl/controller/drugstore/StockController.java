package com.hhhhhx.mbgl.controller.drugstore;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hhhhhx.mbgl.dto.stock.StockNameDTO;
import com.hhhhhx.mbgl.entity.Stock;
import com.hhhhhx.mbgl.entity.User;
import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.param.BasePage;
import com.hhhhhx.mbgl.param.user.UserPageParam;
import com.hhhhhx.mbgl.service.drugstore.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 库存表 前端控制器
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    IStockService stockService;

    @GetMapping("/page/list")
    public RestResponse<IPage<StockNameDTO>> pageList(@Valid BasePage param) {
        return RestResponse.ok(stockService.pageList(param));
    }

    @GetMapping("/get/{id}")
    public RestResponse<Stock> getOne(@NotNull @PathVariable("id") Integer id) {
        return RestResponse.ok(stockService.getById(id));
    }

    @PostMapping("/su")
    public RestResponse<Boolean> pageList(@RequestBody Stock stock) {
        return RestResponse.ok(stockService.saveOrUpdate(stock));
    }
    @PostMapping("/delete/{id}")
    public RestResponse<Boolean> delete(@NotNull @PathVariable("id") Integer id) {
        return RestResponse.ok(stockService.removeById(id));
    }
}
