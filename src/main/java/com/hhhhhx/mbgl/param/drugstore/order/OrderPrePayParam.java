package com.hhhhhx.mbgl.param.drugstore.order;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderPrePayParam extends OrderPayParam {
    @NotNull
    private Integer preId;
}




