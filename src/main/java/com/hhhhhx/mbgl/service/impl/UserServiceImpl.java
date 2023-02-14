package com.hhhhhx.mbgl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.dto.PatientDTO;
import com.hhhhhx.mbgl.dto.UserDTO;
import com.hhhhhx.mbgl.entity.DoctorDTO;
import com.hhhhhx.mbgl.entity.User;
import com.hhhhhx.mbgl.exception.MbglServiceException;
import com.hhhhhx.mbgl.mapper.UserMapper;
import com.hhhhhx.mbgl.massage.value.LoginValue;
import com.hhhhhx.mbgl.param.UserLoginParam;
import com.hhhhhx.mbgl.param.doctor.DoctorPageVM;
import com.hhhhhx.mbgl.param.patient.PatientPageVM;
import com.hhhhhx.mbgl.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserDTO login(UserLoginParam model) {
        User queryUser = this.getUserByUsername(model.getUsername());

        if(queryUser != null && queryUser.getPassword().equals(model.getPassword())) {

            UserDTO userDTO = BeanUtil.toBean(queryUser, UserDTO.class);
            return userDTO;
        } else {
            throw new MbglServiceException(LoginValue.NO_USER);
        }
    }

    @Override
    public User getUserByUsername(String username) {

        User user = this.lambdaQuery().eq(User::getUsername, username).one();

        return user;
    }


    @Override
    public IPage<PatientDTO> pagePatientOfDoctor(PatientPageVM param) {

        IPage<PatientDTO> page = new Page<>(param.getPageIndex(), param.getPageSize());

        return this.baseMapper.getPatientOfDoctor(page, param.getDoctorUserId(), param.getKey());
    }

    @Override
    public IPage<DoctorDTO> pageDoctorOfPatient(DoctorPageVM param) {

        IPage<DoctorDTO> page = new Page<>(param.getPageIndex(), param.getPageSize());

        return this.baseMapper.getDoctorOfPatient(page, param.getPatientUserId(), param.getKey());

    }
}
