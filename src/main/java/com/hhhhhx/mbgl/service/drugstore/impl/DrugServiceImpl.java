package com.hhhhhx.mbgl.service.drugstore.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.dto.DrugInfoDTO;
import com.hhhhhx.mbgl.dto.DrugShopItem;
import com.hhhhhx.mbgl.dto.DrugViewDto;
import com.hhhhhx.mbgl.entity.Drug;
import com.hhhhhx.mbgl.mapper.DrugMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhhhx.mbgl.param.drugstore.drug.DrugSearchParam;
import com.hhhhhx.mbgl.service.drugstore.IDrugService;
import com.hhhhhx.mbgl.service.drugstore.IPrescriptionService;
import com.hhhhhx.mbgl.utils.MoneyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 药品表 服务实现类
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
@Service
public class DrugServiceImpl extends ServiceImpl<DrugMapper, Drug> implements IDrugService {

    @Autowired
    IPrescriptionService prescriptionService;

    @Override
    public IPage<DrugViewDto> pageDrugByParam(DrugSearchParam param) {


        IPage<Drug> drugPage = new Page<>(param.getPageIndex(), param.getPageSize());

        LambdaQueryWrapper<Drug> queryWrapper = new LambdaQueryWrapper<>();


        queryWrapper
                .eq(ObjectUtil.isNotNull(param.getClassification()) && !param.getClassification().equals(0),
                        Drug::getClassification, param.getClassification())
                .like(StrUtil.isNotBlank(param.getName()), Drug::getName, param.getName());

        IPage<Drug> page = this.page(drugPage, queryWrapper);

        IPage<DrugViewDto> convert = page.convert(e -> {
            DrugViewDto drugViewDto = new DrugViewDto();
            drugViewDto.setId(e.getId());
            drugViewDto.setName(e.getName());

            drugViewDto.setPrice(e.getPrice());
            drugViewDto.setTempPrice(MoneyUtil.toDouble(e.getPrice()));

            drugViewDto.setImage(e.getImage());
            drugViewDto.setInfo(e.getInfo());
            return drugViewDto;
        });

        return convert;
    }

    @Override
    public DrugInfoDTO getDrugInfoById(Integer id) {
        Drug drug = this.getById(id);

        DrugInfoDTO drugInfoDTO = BeanUtil.copyProperties(drug, DrugInfoDTO.class);

        drugInfoDTO.setTempPrice(MoneyUtil.toDouble(drug.getPrice()));

        return drugInfoDTO;
    }

    @Override
    public List<DrugShopItem> getPreShopList(Integer preId) {
        return  this.baseMapper.getPreShopList(preId);
    }

    /*点击处方药房 替换本地购物车的数据*/
}
