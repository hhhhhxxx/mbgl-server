package com.hhhhhx.mbgl.controller.drugstore;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hhhhhx.mbgl.dto.DrugViewDto;
import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.param.drugstore.drug.DrugSearchParam;
import com.hhhhhx.mbgl.service.drugstore.IPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hhhhhx
 * @since 2023-03-16
 */
@RestController
@RequestMapping("/prescription")
public class PrescriptionController {

    @Autowired
    private IPrescriptionService prescriptionService;

    @GetMapping("/valid/{id}")
    public RestResponse<Boolean> judgePreValid(@PathVariable("id") @NotNull Integer id) {

        return RestResponse.ok(prescriptionService.judgePreValid(id));
    }
}
