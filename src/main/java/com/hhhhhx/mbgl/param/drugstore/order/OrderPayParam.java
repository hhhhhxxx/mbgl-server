package com.hhhhhx.mbgl.param.drugstore.order;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderPayParam {
    @NotNull
    private Integer userId;
    @NotNull
    private AddressParam addressParam;
    @NotNull
    private List<Shop> shopList;
}




