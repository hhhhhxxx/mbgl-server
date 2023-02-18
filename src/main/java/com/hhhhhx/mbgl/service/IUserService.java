package com.hhhhhx.mbgl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hhhhhx.mbgl.dto.PatientDTO;
import com.hhhhhx.mbgl.dto.UserDTO;
import com.hhhhhx.mbgl.dto.DoctorDTO;
import com.hhhhhx.mbgl.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhhhx.mbgl.param.user.UserLoginParam;
import com.hhhhhx.mbgl.param.doctor.DoctorPageVM;
import com.hhhhhx.mbgl.param.patient.PatientPageVM;
import com.hhhhhx.mbgl.param.user.UserUploadImageParam;
import com.hhhhhx.mbgl.param.user.UserWeixinLoginParam;

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

    UserDTO wxLogin(UserWeixinLoginParam param);

    User getUserByUsername(String username);

    IPage<PatientDTO> pagePatientOfDoctor(PatientPageVM param);

    IPage<DoctorDTO> pageDoctorOfPatient(DoctorPageVM param);

    Boolean upLoadImage(UserUploadImageParam param);


}
