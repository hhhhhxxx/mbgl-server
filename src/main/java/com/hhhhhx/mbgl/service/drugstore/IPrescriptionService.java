package com.hhhhhx.mbgl.service.drugstore;

import cn.hutool.core.util.BooleanUtil;
import com.hhhhhx.mbgl.dto.prescription.PrescriptionDTO;
import com.hhhhhx.mbgl.entity.Prescription;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhhhx.mbgl.param.drugstore.order.Shop;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hhhhhx
 * @since 2023-03-16
 */
public interface IPrescriptionService extends IService<Prescription> {

    Integer createPrescription(Integer doctorId, Integer patientId,List<Shop> shopList,String info);

    Boolean judgePreValid(Integer id);

    PrescriptionDTO getPrescriptionDTO(Integer id);
}
