package com.hhhhhx.mbgl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.dto.PatientDTO;
import com.hhhhhx.mbgl.dto.UserDTO;
import com.hhhhhx.mbgl.entity.DoctorDTO;
import com.hhhhhx.mbgl.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhhhx.mbgl.param.UserLoginParam;
import com.hhhhhx.mbgl.param.doctor.DoctorPageVM;
import com.hhhhhx.mbgl.param.patient.PatientPageVM;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserLoginParam param);

    User getUserByUsername(String username);

    IPage<PatientDTO> pagePatientOfDoctor(PatientPageVM param);

    IPage<DoctorDTO> pageDoctorOfPatient(DoctorPageVM param);
}
