package com.hhhhhx.mbgl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hhhhhx.mbgl.dto.PatientDTO;
import com.hhhhhx.mbgl.entity.Connect;
import com.hhhhhx.mbgl.dto.DoctorDTO;
import com.hhhhhx.mbgl.entity.enums.ConnectState;
import com.hhhhhx.mbgl.exception.MbglServiceException;
import com.hhhhhx.mbgl.mapper.ConnectMapper;
import com.hhhhhx.mbgl.massage.value.ConnectValue;
import com.hhhhhx.mbgl.massage.value.UserValue;
import com.hhhhhx.mbgl.param.connect.ConnectApplyVM;
import com.hhhhhx.mbgl.service.IConnectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhhhx.mbgl.service.IDoctorService;
import com.hhhhhx.mbgl.service.INoticeService;
import com.hhhhhx.mbgl.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hhx
 * @since 2022-09-22
 */
@Service
public class ConnectServiceImpl extends ServiceImpl<ConnectMapper, Connect> implements IConnectService {

    @Autowired
    INoticeService noticeService;
    @Autowired
    IDoctorService doctorService;
    @Autowired
    IPatientService patientService;

    @Override
    public Boolean apply(ConnectApplyVM model) {


        PatientDTO patient = patientService.getPatientByUserId(model.getPatientUserId());
        DoctorDTO doctor = doctorService.getDoctorByUserId(model.getDoctorId());

        if(patient == null && doctor == null) {
            throw new MbglServiceException(UserValue.ROLE_ERROR);
        }



        Connect oldConnect = this.getConnectByDoubleId(patient.getId(), doctor.getId());

        // 已经有了 就不要重复
        if(oldConnect != null) {
            throw new MbglServiceException(ConnectValue.HAS_OLD);
        }

        /*新建拦截*/
        Connect connect = new Connect();
        connect.setPatientUserId(patient.getId());
        connect.setDoctorUserId(doctor.getId());
        connect.setState(ConnectState.APPLY.getCode());

        boolean save = this.save(connect);

        noticeService.sendApplyConnect(doctor.getId(),patient.getId());

        return save;
    }

    @Override
    public Boolean confirm(Connect connect) {

        return this.lambdaUpdate()
                .eq(Connect::getPatientUserId, connect.getPatientUserId())
                .eq(Connect::getDoctorUserId, connect.getDoctorUserId())
                .set(Connect::getState, ConnectState.SUCEESS.getCode()).update();
    }

    @Override
    public Boolean refuse(Connect connect) {

        LambdaQueryWrapper<Connect> query =  new LambdaQueryWrapper<>();

        query.eq(Connect::getPatientUserId, connect.getPatientUserId())
                .eq(Connect::getDoctorUserId, connect.getDoctorUserId());

        return this.remove(query);
    }

    @Override
    public Boolean disconnect(ConnectApplyVM model) {


        PatientDTO patient = patientService.getPatientByUserId(model.getPatientUserId());
        DoctorDTO doctor = doctorService.getDoctorByUserId(model.getDoctorId());


        Connect connect = new Connect();
        connect.setPatientUserId(patient.getId());
        connect.setDoctorUserId(doctor.getId());

        return this.refuse(connect);
    }

    @Override
    public Connect getConnectByDoubleId(Integer patientUserId, Integer doctorUserId) {

        return  this.lambdaQuery()
                .eq(Connect::getPatientUserId, patientUserId)
                .eq(Connect::getDoctorUserId, doctorUserId).one();

    }


}
