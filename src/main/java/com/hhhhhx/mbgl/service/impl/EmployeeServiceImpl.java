package com.hhhhhx.mbgl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.dto.DoctorDTO;
import com.hhhhhx.mbgl.dto.PatientDTO;
import com.hhhhhx.mbgl.dto.user.EmployeeDTO;
import com.hhhhhx.mbgl.entity.User;
import com.hhhhhx.mbgl.entity.enums.RoleEnum;
import com.hhhhhx.mbgl.exception.MbglServiceException;
import com.hhhhhx.mbgl.massage.value.SystemValue;
import com.hhhhhx.mbgl.massage.value.UserValue;
import com.hhhhhx.mbgl.param.patient.PatientPageVM;
import com.hhhhhx.mbgl.param.patient.PatientUpdateParam;
import com.hhhhhx.mbgl.service.IEmployeeService;
import com.hhhhhx.mbgl.service.IPatientService;
import com.hhhhhx.mbgl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    IUserService userService;


    @Override
    public EmployeeDTO getEmployeeByUserId(Integer userId) {
        User user = userService.lambdaQuery()
                .eq(User::getId, userId)
                .eq(User::getRoleId, RoleEnum.EMPLOYEE.getCode())
                .one();

        if (user == null) throw new MbglServiceException(SystemValue.SELECT_FAIL);

        return BeanUtil.toBean(user, EmployeeDTO.class);
    }

    @Override
    @Transactional
    public Boolean updateByUserId(EmployeeDTO employeeDTO) {
        EmployeeDTO employee = getEmployeeByUserId(employeeDTO.getId());

        if (employee == null) {
            throw new MbglServiceException(UserValue.ROLE_ERROR);
        }
        /*这里参数那个 不是查出来那个*/
        User user = BeanUtil.toBean(employeeDTO, User.class);

        if (!userService.updateById(user)) {
            throw new MbglServiceException(SystemValue.UPDATE_FAIL);
        }

        return Boolean.TRUE;
    }

    @Override
    public List<EmployeeDTO> listEmp() {

        List<User> list = userService.lambdaQuery()
                .eq(User::getRoleId, RoleEnum.EMPLOYEE.getCode()).list();

       return list.stream().map(e -> BeanUtil.toBean(e, EmployeeDTO.class)).collect(Collectors.toList());
    }
}
