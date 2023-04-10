package com.hhhhhx.mbgl.controller.user;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hhhhhx.mbgl.dto.DoctorDTO;
import com.hhhhhx.mbgl.dto.PatientDTO;
import com.hhhhhx.mbgl.dto.user.EmployeeDTO;
import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.param.patient.PatientPageVM;
import com.hhhhhx.mbgl.param.patient.PatientUpdateParam;
import com.hhhhhx.mbgl.service.IEmployeeService;
import com.hhhhhx.mbgl.service.IPatientService;
import com.hhhhhx.mbgl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    IEmployeeService employeeService;

    @GetMapping("/user/{userId}")
    public RestResponse<EmployeeDTO> getById(@PathVariable @NotNull Integer userId) {

        return RestResponse.ok(employeeService.getEmployeeByUserId(userId));
    }

    @PostMapping("/update")
    public RestResponse<Boolean> update(@RequestBody @Valid EmployeeDTO employeeDTO) {

        return RestResponse.ok(employeeService.updateByUserId(employeeDTO));
    }
    @GetMapping("/list")
    public RestResponse<List<EmployeeDTO>> list() {
        return RestResponse.ok(employeeService.listEmp());
    }



}
