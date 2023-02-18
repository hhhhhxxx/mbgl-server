package com.hhhhhx.mbgl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hhhhhx.mbgl.dto.DoctorDTO;
import com.hhhhhx.mbgl.param.doctor.DoctorPageVM;

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
