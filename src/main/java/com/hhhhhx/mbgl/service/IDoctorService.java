package com.hhhhhx.mbgl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.dto.PatientDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhhhx.mbgl.entity.DoctorDTO;
import com.hhhhhx.mbgl.param.doctor.DoctorPageVM;
import com.hhhhhx.mbgl.param.patient.PatientPageVM;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
public interface IDoctorService {


    DoctorDTO getDoctorByUserId(Integer userId);

    Boolean updateByUserId(DoctorDTO doctor);

    IPage<DoctorDTO> pageDoctorByParm(DoctorPageVM param);
}
