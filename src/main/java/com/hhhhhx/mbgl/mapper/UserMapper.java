package com.hhhhhx.mbgl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hhhhhx.mbgl.dto.PatientDTO;
import com.hhhhhx.mbgl.dto.DoctorDTO;
import com.hhhhhx.mbgl.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    IPage<PatientDTO> getPatientOfDoctor(IPage<PatientDTO> page, @Param("doctor_user_id") Integer doctorUserId,
                                         @Param("key") String key);

    IPage<DoctorDTO> getDoctorOfPatient(IPage<DoctorDTO> page, @Param("patient_user_id") Integer patientUserId,
                                        @Param("key") String key);

    List<PatientDTO> getConsultPatient();
}
