package com.hhhhhx.mbgl.param.connect;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class ConnectApplyVM {
    @NotNull
    private Integer patientUserId;
    @NotNull
    private Integer doctorId;
}
