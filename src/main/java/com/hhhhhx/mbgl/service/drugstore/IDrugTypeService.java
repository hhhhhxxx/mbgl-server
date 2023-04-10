package com.hhhhhx.mbgl.service.drugstore;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hhhhhx.mbgl.entity.DrugType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhhhx.mbgl.param.drugstore.drug.DrugTypeSearchParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hhhhhx
 * @since 2023-04-09
 */
public interface IDrugTypeService extends IService<DrugType> {

    IPage<DrugType> pageList(DrugTypeSearchParam param);
}
