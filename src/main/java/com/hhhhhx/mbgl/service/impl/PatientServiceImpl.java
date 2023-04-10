package com.hhhhhx.mbgl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.dto.PatientDTO;
import com.hhhhhx.mbgl.entity.User;
import com.hhhhhx.mbgl.entity.enums.RoleEnum;
import com.hhhhhx.mbgl.exception.MbglServiceException;
import com.hhhhhx.mbgl.massage.value.SystemValue;
import com.hhhhhx.mbgl.massage.value.UserValue;
import com.hhhhhx.mbgl.param.patient.PatientPageVM;
import com.hhhhhx.mbgl.param.patient.PatientUpdateParam;
import com.hhhhhx.mbgl.service.IPatientService;
import com.hhhhhx.mbgl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class PatientServiceImpl implements IPatientService {

    @Autowired
    IUserService userService;

    @Override
    public PatientDTO getPatientByUserId(Integer userId) {

        User user = userService.lambdaQuery()
                .eq(User::getId, userId)
                .eq(User::getRoleId, RoleEnum.PATIENT.getCode())
                .one();

        if (user == null) throw new MbglServiceException(SystemValue.SELECT_FAIL);

        return BeanUtil.toBean(user, PatientDTO.class);
    }

    @Override
    public Boolean updateByUserId(PatientUpdateParam param) {

        PatientDTO patient = getPatientByUserId(param.getId());

        if (patient == null) {
            throw new MbglServiceException(UserValue.ROLE_ERROR);
        }

        User user = BeanUtil.toBean(param, User.class);

        if (!userService.updateById(user)) {
            throw new MbglServiceException(SystemValue.UPDATE_FAIL);
        }

        return Boolean.TRUE;
    }

    @Override
    public IPage<PatientDTO> pagePatientByParm(PatientPageVM param) {

        IPage<PatientDTO> page;


        if (param.getDoctorUserId() != null) {
            /* 某个医生的*/
            page = userService.pagePatientOfDoctor(param);

        } else {

            IPage<User> userPage = new Page<>(param.getPageIndex(), param.getPageSize());

            LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();

            // 日后补充模糊查询
            query.eq(User::getRoleId,RoleEnum.PATIENT.getCode())
                    .like(StrUtil.isNotBlank(param.getKey()), User::getName, param.getKey());


            page = userService.page(userPage, query)
                    .convert(e -> BeanUtil.toBean(e, PatientDTO.class));
        }

        return page;
    }

    @Override
    public List<PatientDTO> listConsult() {
        return userService.listConsult();
    }
}
