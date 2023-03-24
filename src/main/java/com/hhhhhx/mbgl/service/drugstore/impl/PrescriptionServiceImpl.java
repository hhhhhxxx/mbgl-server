package com.hhhhhx.mbgl.service.drugstore.impl;
import java.time.LocalDateTime;

import com.hhhhhx.mbgl.entity.Prescription;
import com.hhhhhx.mbgl.entity.PrescriptionItem;
import com.hhhhhx.mbgl.entity.enums.PrescriptionState;
import com.hhhhhx.mbgl.exception.MbglServiceException;
import com.hhhhhx.mbgl.mapper.PrescriptionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhhhx.mbgl.param.drugstore.order.Shop;
import com.hhhhhx.mbgl.service.drugstore.IPrescriptionItemService;
import com.hhhhhx.mbgl.service.drugstore.IPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hhhhhx
 * @since 2023-03-16
 */
@Service
public class PrescriptionServiceImpl extends ServiceImpl<PrescriptionMapper, Prescription> implements IPrescriptionService {

    @Autowired
    IPrescriptionItemService prescriptionItemService;

    @Override
    @Transactional
    public Integer createPrescription(Integer doctorId, Integer patientId, List<Shop> shopList) {

        Prescription prescription = new Prescription();

        prescription.setDoctorId(doctorId);
        prescription.setPatientId(patientId);
        prescription.setCreateTime(LocalDateTime.now());
        prescription.setState(PrescriptionState.NO_USE.getCode());


        boolean save = this.save(prescription);

        if(!save) throw new MbglServiceException();

        Integer preId = prescription.getId();

        List<PrescriptionItem> prescriptionItems = new ArrayList<>(shopList.size());

        for (Shop shop : shopList) {
            PrescriptionItem prescriptionItem = new PrescriptionItem();

            prescriptionItem.setPrescriptionId(preId);
            prescriptionItem.setDrugId(shop.getId());
            prescriptionItem.setQuantity(shop.getQuantity());

            prescriptionItems.add(prescriptionItem);
        }

        boolean b = prescriptionItemService.saveBatch(prescriptionItems);

        if(!b) throw new MbglServiceException();

        return preId;
    }

    @Override
    public Boolean judgePreValid(Integer id) {

        Prescription byId = this.getById(id);

        if(byId == null) return false;
        return PrescriptionState.NO_USE.getCode() == byId.getState();
    }
}
