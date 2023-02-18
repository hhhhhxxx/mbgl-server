package com.hhhhhx.mbgl.entity.factory;

import com.hhhhhx.mbgl.dto.PatientDTO;
import com.hhhhhx.mbgl.dto.DoctorDTO;
import com.hhhhhx.mbgl.entity.Notice;
import com.hhhhhx.mbgl.entity.enums.NoticeOption;
import com.hhhhhx.mbgl.entity.enums.NoticeState;
import com.hhhhhx.mbgl.entity.enums.NoticeType;

import java.time.LocalDateTime;

public class ConnectNoticeFactory {



    public static Notice getApplyNotice(Integer doctorUserId, PatientDTO patient) {

        Notice notice = init();


        notice.setReceiveUserId(doctorUserId);

        notice.setType(NoticeType.APPLY_CONNECT.getCode());

        notice.setContent("患者("+patient.getName()+")申请与您绑定，同意后查看该患者的具体信息");

        notice.setAttachment(patient.getId().toString());

        return notice;
    }

    public static Notice getRefuseNotice(Integer patientUserId, DoctorDTO doctor) {

        Notice notice = init();

        notice.setReceiveUserId(patientUserId);

        notice.setType(NoticeType.INFO.getCode());

        notice.setContent("医生("+doctor.getName()+")拒绝与您绑定");

        notice.setAttachment("");

        return notice;
    }

    public static Notice getConfirmNotice(Integer patientUserId, DoctorDTO doctor) {

        Notice notice = init();

        notice.setReceiveUserId(patientUserId);

        notice.setType(NoticeType.INFO.getCode());

        notice.setContent("医生("+doctor.getName()+")同意与您绑定");

        notice.setAttachment("");

        return notice;
    }

    private static Notice init() {

        Notice notice = new Notice();

        notice.setState(NoticeState.UNREAD.getCode());
        notice.setCreateTime(LocalDateTime.now());
        notice.setOp(NoticeOption.INIT.getCode());

        return notice;
    }
}
