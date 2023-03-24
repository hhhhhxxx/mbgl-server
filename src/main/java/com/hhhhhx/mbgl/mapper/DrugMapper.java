package com.hhhhhx.mbgl.mapper;

import com.hhhhhx.mbgl.dto.DrugShopItem;
import com.hhhhhx.mbgl.entity.Drug;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhhhhx.mbgl.entity.Message;
import com.hhhhhx.mbgl.param.msg.MessageListVM;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 药品表 Mapper 接口
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
public interface DrugMapper extends BaseMapper<Drug> {

    List<DrugShopItem> getPreShopList(@Param("pre_id") Integer preId);
}
