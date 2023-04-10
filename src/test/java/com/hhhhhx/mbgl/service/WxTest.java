package com.hhhhhx.mbgl.service;
import java.time.LocalDateTime;

import com.hhhhhx.mbgl.MbglApplication;
import com.hhhhhx.mbgl.param.wx.WxSendSubscribeMessage;
import com.hhhhhx.mbgl.service.other.WxRunService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MbglApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class WxTest {

    @Autowired
    WxRunService wxRunService;

    @Test
    public void test() {
        WxSendSubscribeMessage wxSendSubscribeMessage = new WxSendSubscribeMessage();
        wxSendSubscribeMessage.setUserId(1);
        wxSendSubscribeMessage.setDrugName("");
        wxSendSubscribeMessage.setAdvice("");
        wxSendSubscribeMessage.setNum("");
        wxSendSubscribeMessage.setInfo("");
        wxSendSubscribeMessage.setTime(LocalDateTime.now());


        wxRunService.sendMessage(wxSendSubscribeMessage);
    }
}
