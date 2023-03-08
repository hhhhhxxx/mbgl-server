package com.hhhhhx.mbgl.mapper;

import com.hhhhhx.mbgl.dto.StockDTO;
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
   List<StockDTO> getStockDTOByDrugIds(List<Integer> drugIds);

   List<Stock> getStockByDrugIds(List<Integer> drugIds);

   int updateStockWithVersion(Stock stock);

   int batchUpdate(@Param("update_list") List<Stock> updateList);

}
