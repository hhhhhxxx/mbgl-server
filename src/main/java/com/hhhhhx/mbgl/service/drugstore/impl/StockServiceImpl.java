package com.hhhhhx.mbgl.service.drugstore.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.dto.StockCombineDTO;
import com.hhhhhx.mbgl.dto.StockItemWithValueDTO;
import com.hhhhhx.mbgl.entity.Stock;
import com.hhhhhx.mbgl.entity.User;
import com.hhhhhx.mbgl.mapper.StockMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhhhx.mbgl.param.BasePage;
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
    public List<StockCombineDTO> getStockCombineDTOByDrugIds(List<Integer> drugIds) {
        return stockMapper.getStockCombineDTOByDrugIds(drugIds);
    }

    @Override
    public List<StockItemWithValueDTO> getStockItemWithValueDTOByDrugIds(List<Integer> drugIds) {
        return stockMapper.getStockItemWithValueDTOByDrugIds(drugIds);
    }

    @Override
    @Transactional
    public boolean updateManyStock(List<Stock> updateList) {

        int i = stockMapper.updateManyStock(updateList);
        // 有没有全部更新
        return i == updateList.size();
    }

    @Override
    public IPage<Stock> pageList(BasePage param) {
        Page<Stock> page = new Page<>(param.getPageIndex(),param.getPageSize());
        return this.page(page);
    }

    @Override
    public List<Stock> getDangerStock() {
        return this.baseMapper.getDangerStock();
    }

    @Override
    public int updateDangerStock(List<Stock> updateList) {
        return stockMapper.updateDangerStock(updateList);
    }
}
