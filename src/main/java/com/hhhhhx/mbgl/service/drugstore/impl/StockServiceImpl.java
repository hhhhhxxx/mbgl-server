package com.hhhhhx.mbgl.service.drugstore.impl;

import com.hhhhhx.mbgl.dto.StockDTO;
import com.hhhhhx.mbgl.entity.Stock;
import com.hhhhhx.mbgl.mapper.StockMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhhhx.mbgl.service.drugstore.IStockService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


    @Autowired
    StockMapper stockMapper;

    @Override
    public List<StockDTO> getStockDTOList(List<Integer> drugIds) {
        return stockMapper.getStockDTOByDrugIds(drugIds);
    }

    @Override
    public List<Stock> getStockListByDrugIds(List<Integer> drugIds) {
        return stockMapper.getStockByDrugIds(drugIds);
    }

    // @Override
    // @Transactional
    // public int batchUpdate(List<Stock> updateList) {
    //     return stockMapper.batchUpdate(updateList);
    // }
    //
    // @Override
    // @Transactional
    // public int updateTest(List<Stock> updateList) {
    //     return stockMapper.updateTest(updateList);
    // };
}
