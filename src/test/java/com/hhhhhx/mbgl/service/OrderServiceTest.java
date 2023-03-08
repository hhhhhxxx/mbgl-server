package com.hhhhhx.mbgl.service;

import cn.hutool.core.collection.ListUtil;
import com.hhhhhx.mbgl.MbglApplication;
import com.hhhhhx.mbgl.entity.Stock;
import com.hhhhhx.mbgl.entity.User;
import com.hhhhhx.mbgl.mapper.StockMapper;
import com.hhhhhx.mbgl.service.drugstore.IStockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@SpringBootTest(classes = MbglApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class OrderServiceTest {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private IStockService stockService;

    @Test
    public void test1() {

        // stockMapper.getStockByDrugIds(ListUtil.toList(1,2,3,4));
        // stockMapper.getStockByDrugIds(ListUtil.toList(1,2,3,4));


        ArrayList<Stock> stocks = new ArrayList<>();

        Stock stock1 = new Stock();
        stock1.setId(1);
        stock1.setQuantity(13333);
        stock1.setVersion(6);

        Stock stock2 = new Stock();
        stock2.setId(2);
        stock2.setQuantity(111);
        stock2.setVersion(6);


        Stock stock3 = new Stock();
        stock3.setId(3);
        stock3.setQuantity(111);
        stock3.setVersion(6);

        stocks.add(stock1);
        // stocks.add(stock2);
        // stocks.add(stock3);

        // Integer b = stockMapper.batchUpdate(stocks);

        // boolean b = stockService.updateBatchById(stocks);

        // System.out.println(b+"---------------");
    }

    @Test
    public void test2() {

        ArrayList<Stock> stocks = new ArrayList<>();

        Stock stock1 = new Stock();
        stock1.setId(1);
        stock1.setQuantity(1);
        stock1.setVersion(1);

        Stock stock2 = new Stock();
        stock2.setId(2);
        stock2.setQuantity(1);
        stock2.setVersion(2);

        Stock stock3 = new Stock();
        stock3.setId(3);
        stock3.setQuantity(1);
        stock3.setVersion(1);

        int sum = 0;

        long l = System.currentTimeMillis();
        // for (int i = 0; i < 1000; i++) {
        //     stocks.add(stock1);
        //     // sum += stockMapper.updateStockWithVersion(stock1);
        // }

        stocks.add(stock1);
        stocks.add(stock2);
        stocks.add(stock3);

        long l2 = System.currentTimeMillis();
        System.out.println("消耗时间1:" + (l2 - l) / 1000.0);

        // stockMapper.batchUpdate(stocks); 1s

        System.out.println(stockService.updateBatchById(stocks));
        // 0.5s

        System.out.println("update返回值" + sum);
        long l3 = System.currentTimeMillis();
        System.out.println("消耗时间2:" + (l3 - l2) / 1000.0);
    }

}
