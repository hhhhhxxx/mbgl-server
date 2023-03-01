package com.hhhhhx.mbgl.service.drugstore.impl;

import com.hhhhhx.mbgl.entity.Stock;
import com.hhhhhx.mbgl.mapper.StockMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhhhx.mbgl.service.drugstore.IStockService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 库存表 服务实现类
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {

}
