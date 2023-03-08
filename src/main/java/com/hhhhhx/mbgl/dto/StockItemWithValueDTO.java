package com.hhhhhx.mbgl.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
@Setter
@Getter
public class StockItemWithValueDTO {
    private Integer id;
    private Integer drugId;
    private Integer quantity;
    private Integer version;

    // stock 没有price
    private Integer price;
}
