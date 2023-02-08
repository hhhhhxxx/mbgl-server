package com.hhhhhx.mbgl.controller;


import com.hhhhhx.mbgl.context.WebContext;
import com.hhhhhx.mbgl.entity.Record;
import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.param.RecordAddVM;
import com.hhhhhx.mbgl.param.record.RecordAllDeatilData;
import com.hhhhhx.mbgl.param.record.RecordAllVM;
import com.hhhhhx.mbgl.param.record.RecordPageVM;
import com.hhhhhx.mbgl.param.record.RecordWeekData;
import com.hhhhhx.mbgl.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    WebContext context;

    @Autowired
    private IRecordService recordService;


    @PostMapping("/add")
    public RestResponse add(@RequestBody RecordAddVM model) {

        boolean ok = recordService.addRecord(model);

        if(!ok) return RestResponse.fail();
        return RestResponse.ok();
    }

    @GetMapping("/getWeekData")
    public RestResponse get(RecordPageVM model) {

        model.setPatientUserId(context.getCurrentUserId());

        RecordWeekData recordWeekData = recordService.getWeekData(model);

        return RestResponse.ok(recordWeekData);
    }

    @GetMapping("/getAllDetail")
    public RestResponse getAllDetail(RecordAllVM model) {


        RecordAllDeatilData allDetail = recordService.getAllDetail(model);

        return RestResponse.ok(allDetail);
    }
}
