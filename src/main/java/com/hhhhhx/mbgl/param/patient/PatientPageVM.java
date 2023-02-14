package com.hhhhhx.mbgl.param.patient;

import com.hhhhhx.mbgl.param.BasePage;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class PatientPageVM extends BasePage {

    private String key;
    private Integer doctorUserId;
}
