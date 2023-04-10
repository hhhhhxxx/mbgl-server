package com.hhhhhx.mbgl.service.drugstore;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hhhhhx.mbgl.dto.ClassificationDTO;
import com.hhhhhx.mbgl.dto.DrugInfoDTO;
import com.hhhhhx.mbgl.dto.DrugShopItem;
import com.hhhhhx.mbgl.dto.DrugViewDto;
import com.hhhhhx.mbgl.entity.Drug;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhhhx.mbgl.param.BasePage;
import com.hhhhhx.mbgl.param.drugstore.drug.DrugSearchParam;

import java.util.List;

/**
 * <p>
 * 药品表 服务类
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
public interface IDrugService extends IService<Drug> {

    IPage<DrugViewDto> pageDrugByParam(DrugSearchParam param);

    DrugInfoDTO getDrugInfoById(Integer id);

    List<DrugShopItem> getPreShopList(Integer preId);

    IPage<Drug> pageList(DrugSearchParam param);

    List<ClassificationDTO> getClazz();
}
