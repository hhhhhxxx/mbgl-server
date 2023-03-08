package com.hhhhhx.mbgl.service.drugstore;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.hhhhhx.mbgl.dto.StockDTO;
import com.hhhhhx.mbgl.entity.Stock;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 库存表 服务类
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
public interface IStockService extends IService<Stock> {


    List<StockDTO> getStockDTOList(List<Integer> drugIds);

    List<Stock> getStockListByDrugIds(List<Integer> drugIds);

    // int batchUpdate(List<Stock> updateList);
    //
    // int updateTest(List<Stock> updateList);
}
