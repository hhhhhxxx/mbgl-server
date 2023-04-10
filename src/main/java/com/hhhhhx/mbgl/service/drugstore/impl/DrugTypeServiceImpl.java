package com.hhhhhx.mbgl.service.drugstore.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.entity.Drug;
import com.hhhhhx.mbgl.entity.DrugType;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhhhx.mbgl.mapper.DrugTypeMapper;
import com.hhhhhx.mbgl.param.drugstore.drug.DrugTypeSearchParam;
import com.hhhhhx.mbgl.service.drugstore.IDrugTypeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hhhhhx
 * @since 2023-04-09
 */
@Service
public class DrugTypeServiceImpl extends ServiceImpl<DrugTypeMapper, DrugType> implements IDrugTypeService {

    @Override
    public IPage<DrugType> pageList(DrugTypeSearchParam param) {

        IPage<DrugType> drugTypePage = new Page<>(param.getPageIndex(), param.getPageSize());

        LambdaQueryWrapper<DrugType> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(StrUtil.isNotBlank(param.getName()), DrugType::getName, param.getName());

        return this.page(drugTypePage, queryWrapper);
    }
}
