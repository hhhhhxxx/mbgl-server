package com.hhhhhx.mbgl.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.entity.wx.WxAccessTokenResult;
import com.hhhhhx.mbgl.entity.wx.WxRunDataResult;
import com.hhhhhx.mbgl.param.wx.WxDecodeDataVM;
import com.hhhhhx.mbgl.param.wx.WxSendSubscribeMessage;
import com.hhhhhx.mbgl.service.other.WxRunService;
import com.hhhhhx.mbgl.task.RedissonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.time.*;
import java.util.List;

@RestController
@RequestMapping("/wx")
public class WxController {


    @Autowired
    private WxRunService wxRunService;

    @Autowired
    private RedissonService redissonService;

    @GetMapping("/set/sessionkey")
    public RestResponse sessionkey(@RequestHeader("user_id") Integer userId, String code) {

       boolean ok =  wxRunService.sessionkey(userId,code);

       if(!ok) {
           return RestResponse.fail();
       }
        return RestResponse.ok();
    }

    @PostMapping("/getNowStep")
    public RestResponse getNowStep(@RequestHeader("user_id") Integer userId, @RequestBody WxDecodeDataVM model) {


        String data =  wxRunService.decode(userId,model);

        if(data == null) {
            return RestResponse.fail();
        }

        if(data.charAt(0) != '{') {
            return RestResponse.fail();
        }

        JSONObject jsonObject = JSONUtil.parseObj(data);
        JSONArray stepInfoList = jsonObject.getJSONArray("stepInfoList");

        List<WxRunDataResult.stepItem> stepItemList = JSONUtil.toList(stepInfoList, WxRunDataResult.stepItem.class);

        WxRunDataResult wxRunDataResult = new WxRunDataResult();
        wxRunDataResult.setStepInfoList(stepItemList);

        WxRunDataResult.stepItem stepItem = stepItemList.get(stepInfoList.size() - 1);

        return RestResponse.ok(stepItem.getStep());
    }

    @PostMapping("/decode")
    public RestResponse decode(@RequestHeader("user_id") Integer userId, @RequestBody WxDecodeDataVM model) {


        String data =  wxRunService.decode(userId,model);

        System.out.println(data.charAt(0));
        System.out.println(data.charAt(1)+"<<<");
        JSONObject jsonObject = JSONUtil.parseObj(data);

        JSONArray stepInfoList = jsonObject.getJSONArray("stepInfoList");

        List<WxRunDataResult.stepItem> stepItemList = JSONUtil.toList(stepInfoList, WxRunDataResult.stepItem.class);

        WxRunDataResult wxRunDataResult = new WxRunDataResult();
        wxRunDataResult.setStepInfoList(stepItemList);

        // System.out.println(">>> "+wxRunDataResult);

        WxRunDataResult.stepItem stepItem = stepItemList.get(stepInfoList.size() - 1);


        //获得时间戳
        // long milliseconds = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();

        long timestamp = Long.parseLong(stepItem.getTimestamp());

        // 将时间戳转为当前时间
        LocalDate localDate = Instant.ofEpochSecond(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate();

        System.out.println(localDate);

        return RestResponse.ok();
    }


    @PostMapping("/send/message")
    public RestResponse sendMessage(@RequestBody WxSendSubscribeMessage param) {
        // return RestResponse.ok(wxRunService.sendMessage(param));

        Long timeLong = param.getTimeLong();
        LocalDateTime time = Instant.ofEpochMilli(timeLong).atZone(ZoneId.systemDefault()).toLocalDateTime();
        param.setTime(time);
        redissonService.addDelay(param);
        return RestResponse.ok();
    }
}

