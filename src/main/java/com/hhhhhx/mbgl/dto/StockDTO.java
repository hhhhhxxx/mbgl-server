package com.hhhhhx.mbgl.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
@Setter
@Getter
public class StockDTO {
    private Integer drugId;
    private Integer quantity;
}
