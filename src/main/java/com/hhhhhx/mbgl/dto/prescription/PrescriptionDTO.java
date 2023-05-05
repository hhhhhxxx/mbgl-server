package com.hhhhhx.mbgl.dto.prescription;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhhhhx.mbgl.dto.DrugShopItem;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PrescriptionDTO {

    private Integer id;

    private Integer doctorId;

    private Integer patientId;

    private LocalDateTime createTime;

    private Integer state;

    private String info;

    private String doctorName;

    private List<DrugShopItem> shopList;
}
