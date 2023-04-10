package com.hhhhhx.mbgl.task;

import com.alibaba.fastjson.JSON;
import com.hhhhhx.mbgl.param.wx.WxSendSubscribeMessage;
import com.hhhhhx.mbgl.service.other.WxRunService;
import lombok.extern.log4j.Log4j2;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * spring-boot项目启动完成运行
 */
@Log4j2
@Component
public class AppStartupRunner implements CommandLineRunner {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private WxRunService wxRunService;

    @Override
    public void run(String... args) {
        new Thread(()->{
            System.out.println("----------------开始延时任务");
            RBlockingQueue<WxSendSubscribeMessage> blockingFairQueue = redissonClient.getBlockingQueue("delay_queue_call");
            // 开启客户端监听（必须调用），否者系统重启时拿不到已过期数据，要等到系统第一次调用getDelayedQueue方法时才能开启监听
            redissonClient.getDelayedQueue(blockingFairQueue);
            while (true){
                WxSendSubscribeMessage dto = null;
                try {
                    dto = blockingFairQueue.take();
                } catch (Exception e) {
                    continue;
                }
                if (Objects.isNull(dto)) {
                    continue;
                }
                wxRunService.sendMessage(dto);
                System.out.println(String.format("receive1=======dto:%s", JSON.toJSONString(dto)));
            }
        }).start();
    }

}
