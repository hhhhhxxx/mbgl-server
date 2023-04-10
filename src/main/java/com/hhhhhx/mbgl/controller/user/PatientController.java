package com.hhhhhx.mbgl.controller.user;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.dto.PatientDTO;
import com.hhhhhx.mbgl.dto.user.EmployeeDTO;
import com.hhhhhx.mbgl.entity.User;
import com.hhhhhx.mbgl.entity.enums.RoleEnum;
import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.param.doctor.DoctorPageVM;
import com.hhhhhx.mbgl.param.patient.PatientPageVM;
import com.hhhhhx.mbgl.param.patient.PatientUpdateParam;
import com.hhhhhx.mbgl.service.IPatientService;
import com.hhhhhx.mbgl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    IPatientService patientService;

    @Autowired
    IUserService userService;

    @GetMapping("/page/list")
    public RestResponse<IPage<PatientDTO>> pageList(PatientPageVM param) {

        return RestResponse.ok(patientService.pagePatientByParm(param));
    }

    @GetMapping("/user/{userId}")
    public RestResponse<PatientDTO> getPatinetById(@PathVariable @NotNull Integer userId) {
        return RestResponse.ok(patientService.getPatientByUserId(userId));
    }


    @PostMapping("/update")
    public RestResponse<Boolean> update(@RequestBody @Valid PatientUpdateParam patient) {

        return RestResponse.ok(patientService.updateByUserId(patient));
    }

    @GetMapping("/list/consult")
    public RestResponse<List<PatientDTO>> list() {
        return RestResponse.ok(patientService.listConsult());
    }
}
