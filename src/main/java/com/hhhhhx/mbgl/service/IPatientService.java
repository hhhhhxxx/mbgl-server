package com.hhhhhx.mbgl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhhhhx.mbgl.dto.PatientDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhhhx.mbgl.param.patient.PatientPageVM;
import com.hhhhhx.mbgl.param.patient.PatientUpdateParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
public interface IPatientService {

    PatientDTO getPatientByUserId(Integer userId);

    Boolean updateByUserId(PatientUpdateParam patient);

    IPage<PatientDTO> pagePatientByParm(PatientPageVM param);
}
