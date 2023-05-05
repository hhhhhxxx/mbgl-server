package com.hhhhhx.mbgl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hhhhhx.mbgl.dto.StockCombineDTO;
import com.hhhhhx.mbgl.dto.StockItemWithValueDTO;
import com.hhhhhx.mbgl.dto.stock.StockNameDTO;
import com.hhhhhx.mbgl.entity.Stock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 库存表 Mapper 接口
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
@Mapper
public interface StockMapper extends BaseMapper<Stock> {
   List<StockCombineDTO> getStockCombineDTOByDrugIds(List<Integer> drugIds);

   List<StockItemWithValueDTO> getStockItemWithValueDTOByDrugIds(List<Integer> drugIds);

   int updateManyStock(@Param("update_list") List<Stock> updateList);

   /* 获取低于50库存 和半年内过期的 */
   List<Stock> getDangerStock();

   int updateDangerStock(@Param("update_list") List<Stock> updateList);

   IPage<StockNameDTO> pageStockNameDTO(@Param("page") IPage<StockNameDTO> page);
}
