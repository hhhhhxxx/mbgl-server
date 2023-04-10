package com.hhhhhx.mbgl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hhhhhx.mbgl.dto.DoctorDTO;
import com.hhhhhx.mbgl.dto.PatientDTO;
import com.hhhhhx.mbgl.dto.user.EmployeeDTO;
import com.hhhhhx.mbgl.param.patient.PatientPageVM;
import com.hhhhhx.mbgl.param.patient.PatientUpdateParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
public interface IEmployeeService {

    EmployeeDTO getEmployeeByUserId(Integer userId);

    Boolean updateByUserId(EmployeeDTO employeeDTO);

    List<EmployeeDTO> listEmp();
}
