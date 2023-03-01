package com.hhhhhx.mbgl.controller.drugstore;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hhhhhx.mbgl.dto.DoctorDTO;
import com.hhhhhx.mbgl.dto.DrugViewDto;
import com.hhhhhx.mbgl.entity.Drug;
import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.param.doctor.DoctorPageVM;
import com.hhhhhx.mbgl.param.drug.DrugSearchParam;
import com.hhhhhx.mbgl.service.IDoctorService;
import com.hhhhhx.mbgl.service.drugstore.IDrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 药品表 前端控制器
 * </p>
 *
 * @author hhhhhx
 * @since 2023-02-25
 */
@RestController
@RequestMapping("/drug")
public class DrugController {
    @Autowired
    private IDrugService drugService;

    @GetMapping("/page/view")
    public RestResponse<IPage<DrugViewDto>> pageList(DrugSearchParam param) {

        return RestResponse.ok(drugService.pageDrugByParam(param));
    }

    @GetMapping("/get/info/{id}")
    public RestResponse<Drug> getInfo(@PathVariable @NotNull Integer id) {

        return RestResponse.ok(drugService.getDrugInfoById(id));
    }
}
