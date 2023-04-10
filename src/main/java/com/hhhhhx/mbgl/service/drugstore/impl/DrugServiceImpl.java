package com.hhhhhx.mbgl.service.drugstore.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.dto.ClassificationDTO;
import com.hhhhhx.mbgl.dto.DrugInfoDTO;
import com.hhhhhx.mbgl.dto.DrugShopItem;
import com.hhhhhx.mbgl.dto.DrugViewDto;
import com.hhhhhx.mbgl.entity.Drug;
import com.hhhhhx.mbgl.mapper.DrugMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhhhx.mbgl.param.BasePage;
import com.hhhhhx.mbgl.param.drugstore.drug.DrugSearchParam;
import com.hhhhhx.mbgl.service.drugstore.IDrugService;
import com.hhhhhx.mbgl.service.drugstore.IPrescriptionService;
import com.hhhhhx.mbgl.utils.MoneyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public IPage<Drug> pageList(DrugSearchParam param) {
        IPage<Drug> drugPage = new Page<>(param.getPageIndex(), param.getPageSize());

        LambdaQueryWrapper<Drug> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper
                .eq(ObjectUtil.isNotNull(param.getClassification()) && !param.getClassification().equals(0),
                        Drug::getClassification, param.getClassification())
                .like(StrUtil.isNotBlank(param.getName()), Drug::getName, param.getName());

        return this.page(drugPage, queryWrapper);
    }

    @Override
    public List<ClassificationDTO> getClazz() {

        List<ClassificationDTO> list = new ArrayList<>();


        ClassificationDTO c1 = new ClassificationDTO("全部",0);
        ClassificationDTO c2 = new ClassificationDTO("感冒咳嗽",1);
        ClassificationDTO c3 = new ClassificationDTO("肠胃用药",2);
        ClassificationDTO c4 = new ClassificationDTO("儿科用药",3);
        ClassificationDTO c5 = new ClassificationDTO("妇科用药",4);

        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        list.add(c5);

        return list;
    }
}
