package com.hhhhhx.mbgl.service.drugstore;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hhhhhx.mbgl.dto.StockCombineDTO;
import com.hhhhhx.mbgl.dto.StockItemWithValueDTO;
import com.hhhhhx.mbgl.dto.stock.StockNameDTO;
import com.hhhhhx.mbgl.entity.Stock;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhhhx.mbgl.param.BasePage;

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

    List<StockCombineDTO> getStockCombineDTOByDrugIds(List<Integer> drugIds);

    List<StockItemWithValueDTO> getStockItemWithValueDTOByDrugIds(List<Integer> drugIds);

    boolean updateManyStock(List<Stock> updateList);

    IPage<StockNameDTO> pageList(BasePage param);

    List<Stock> getDangerStock();

    int updateDangerStock(List<Stock> updateList);
}
