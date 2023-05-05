package com.hhhhhx.mbgl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.dto.PatientDTO;
import com.hhhhhx.mbgl.dto.UserDTO;
import com.hhhhhx.mbgl.dto.DoctorDTO;
import com.hhhhhx.mbgl.entity.User;
import com.hhhhhx.mbgl.entity.wx.WxJscode2sessionResult;
import com.hhhhhx.mbgl.exception.MbglServiceException;
import com.hhhhhx.mbgl.mapper.UserMapper;
import com.hhhhhx.mbgl.massage.value.LoginValue;
import com.hhhhhx.mbgl.param.user.UserLoginParam;
import com.hhhhhx.mbgl.param.doctor.DoctorPageVM;
import com.hhhhhx.mbgl.param.patient.PatientPageVM;
import com.hhhhhx.mbgl.param.user.UserPageParam;
import com.hhhhhx.mbgl.param.user.UserUploadImageParam;
import com.hhhhhx.mbgl.param.user.UserWeixinLoginParam;
import com.hhhhhx.mbgl.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhhhx.mbgl.service.other.WxRunService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    WxRunService wxRunService;

    @Override
    public UserDTO login(UserLoginParam model) {
        User queryUser = this.getUserByUsername(model.getUsername());

        if (queryUser != null && queryUser.getPassword().equals(model.getPassword())) {

            UserDTO userDTO = BeanUtil.toBean(queryUser, UserDTO.class);
            return userDTO;
        } else {
            throw new MbglServiceException(LoginValue.INPUT_ERROR);
        }
    }

    @Override
    public UserDTO wxLogin(UserWeixinLoginParam param) {

        WxJscode2sessionResult sessionKeyAndOpenId = wxRunService.getSessionKeyAndOpenId(param.getCode());

        String openid = sessionKeyAndOpenId.getOpenid();

        log.info("openId:{}", openid);

        User user = this.lambdaQuery().eq(User::getOpenId, openid).one();

        if (user != null) {
            UserDTO userDTO = BeanUtil.toBean(user, UserDTO.class);
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

    @Override
    @Transactional
    public Boolean upLoadImage(UserUploadImageParam param) {

        return this.lambdaUpdate().eq(User::getId, param.getId()).set(User::getImage, param.getImage()).update();
    }

    @Override
    public IPage<User> pageList(UserPageParam param) {

        Page<User> page = new Page<>(param.getPageIndex(), param.getPageSize());


        return this.lambdaQuery()
                .eq(param.getRoleId() != null, User::getRoleId, param.getRoleId())
                .like(StrUtil.isNotBlank(param.getUsername()), User::getUsername, param.getUsername()).page(page);
    }

    @Override
    public List<PatientDTO> listConsult() {
        return this.baseMapper.getConsultPatient();
    }


    @Override
    public UserDTO getNameImageById(Integer id) {
        User byId = this.getById(id);
        if (byId == null) throw new MbglServiceException();
        UserDTO userDTO = BeanUtil.toBean(byId, UserDTO.class);
        return userDTO;
    }


}
