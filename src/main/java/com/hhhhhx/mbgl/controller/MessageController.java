package com.hhhhhx.mbgl.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.entity.Message;
import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.param.msg.MessageListVM;
import com.hhhhhx.mbgl.param.msg.MessageSendVM;
import com.hhhhhx.mbgl.service.IMessageService;
import com.hhhhhx.mbgl.service.drugstore.IPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
@RestController
@RequestMapping("/message")
public class  MessageController {

    @Autowired
    private IMessageService messageService;



    @PostMapping("/send")
    public RestResponse send(@RequestBody @Valid MessageSendVM model) {

        boolean ok = messageService.send(model);

        if(!ok) return RestResponse.fail();

        return RestResponse.ok();
    }

    @PostMapping("/getBefore")
    public RestResponse getBefore(@RequestBody @Valid MessageListVM model) {

        if(model.getTargetTime() == null) {
            model.setTargetTime(LocalDateTime.now());
        }

        List<Message> chatRecord = messageService.getBeforeChat(model);

        return RestResponse.ok(chatRecord);
    }
}
