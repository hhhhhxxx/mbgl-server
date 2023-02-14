package com.hhhhhx.mbgl.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hhhhhx.mbgl.entity.DoctorDTO;
import com.hhhhhx.mbgl.entity.User;
import com.hhhhhx.mbgl.entity.enums.RoleEnum;
import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.param.doctor.DoctorPageVM;
import com.hhhhhx.mbgl.service.IDoctorService;
import com.hhhhhx.mbgl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private IDoctorService doctorService;

    @GetMapping("/page/list")
    public RestResponse<IPage<DoctorDTO>> pageList(DoctorPageVM param) {

        return RestResponse.ok(doctorService.pageDoctorByParm(param));
    }

    @GetMapping("/user/{userId}")
    public RestResponse getById(@PathVariable @NotNull Integer userId) {

        return RestResponse.ok(doctorService.getDoctorByUserId(userId));
    }

    @PostMapping("/update")
    public RestResponse update(@RequestBody @Valid DoctorDTO doctor) {


        return RestResponse.ok(doctorService.updateByUserId(doctor));
    }
}
