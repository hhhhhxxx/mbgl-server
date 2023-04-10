package com.hhhhhx.mbgl.task;

import com.hhhhhx.mbgl.param.wx.WxSendSubscribeMessage;
import com.hhhhhx.mbgl.service.other.WxRunService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedissonService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    WxRunService wxRunService;

    public void addDelay(WxSendSubscribeMessage param) {
        RBlockingQueue<WxSendSubscribeMessage> blockingFairQueue = redissonClient.getBlockingQueue("delay_queue_call");
        RDelayedQueue<WxSendSubscribeMessage> delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);

        LocalDateTime time = param.getTime();
        if(!time.isAfter(LocalDateTime.now())) {
            return;
        }
        final Duration duration = Duration.between(LocalDateTime.now(),time);

        if(duration.toMillis() < 0) {
            log.error(duration.toMillis()+"：间隔小于0");
            wxRunService.sendMessage(param);
        } else {
            delayedQueue.offer(param, duration.toMillis(), TimeUnit.MILLISECONDS);
        }


        // 不要调用下面的方法,否者会导致消费不及时
//        delayedQueue.destroy();
    }
}
