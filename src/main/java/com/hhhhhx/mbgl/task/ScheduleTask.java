package com.hhhhhx.mbgl.task;

import com.hhhhhx.mbgl.entity.Stock;
import com.hhhhhx.mbgl.service.drugstore.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling    //开启定时任务
@Slf4j
public class ScheduleTask {

    @Autowired
    IStockService stockService;

    //每3秒执行一次
    @Scheduled(fixedDelay = 1260000)
    private void myTasks() {
        List<Stock> dangerStock = stockService.getDangerStock();

        if(dangerStock.size() > 0) {


            int i = stockService.updateDangerStock(dangerStock);

            log.error("有预警数据{}条,更新了{}条",dangerStock.size(),i);
        } else {
            log.error("没有预警数据",dangerStock.size());
        }
    }
}
