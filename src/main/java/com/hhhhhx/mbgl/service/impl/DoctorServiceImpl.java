package com.hhhhhx.mbgl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.dto.DoctorDTO;
import com.hhhhhx.mbgl.entity.User;
import com.hhhhhx.mbgl.entity.enums.RoleEnum;
import com.hhhhhx.mbgl.exception.MbglServiceException;
import com.hhhhhx.mbgl.massage.value.SystemValue;
import com.hhhhhx.mbgl.massage.value.UserValue;
import com.hhhhhx.mbgl.param.doctor.DoctorPageVM;
import com.hhhhhx.mbgl.service.IDoctorService;
import com.hhhhhx.mbgl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
@Service
public class DoctorServiceImpl implements IDoctorService {

    @Autowired
    private IUserService userService;

    @Override
    public DoctorDTO getDoctorByUserId(Integer userId) {

        User user = userService.lambdaQuery()
                .eq(User::getId, userId)
                .eq(User::getRoleId, RoleEnum.DOCTOR.getCode())
                .one();

        if (user == null) throw new MbglServiceException(SystemValue.SELECT_FAIL);

        return BeanUtil.toBean(user, DoctorDTO.class);
    }

    @Override
    public Boolean updateByUserId(DoctorDTO param) {

        DoctorDTO doctorDTO = getDoctorByUserId(param.getId());

        if (doctorDTO == null) {
            throw new MbglServiceException(UserValue.ROLE_ERROR);
        }

        User user = BeanUtil.toBean(param, User.class);

        if (!userService.updateById(user)) {
            throw new MbglServiceException(SystemValue.UPDATE_FAIL);
        }

        return Boolean.TRUE;
    }

    @Override
    public IPage<DoctorDTO> pageDoctorByParm(DoctorPageVM param) {


        IPage<DoctorDTO> page;


        if (param.getPatientUserId() != null) {

            page = userService.pageDoctorOfPatient(param);

        } else {


            IPage<User> userPage = new Page<>(param.getPageIndex(), param.getPageSize());


            LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();

            query.eq(User::getRoleId, RoleEnum.DOCTOR.getCode());

            if (StrUtil.isNotBlank(param.getKey())) {
                // 条件判断写外面 不然报错
                query.and(item ->
                                item.like(User::getName, param.getKey())
                                        .or()
                                        .like(User::getRoom, param.getKey()));
            }

            page = userService.page(userPage, query)
                    .convert(e -> BeanUtil.toBean(e, DoctorDTO.class));
        }

        return page;
    }
}
