package com.hhhhhx.mbgl.param.drugstore.order;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.hhhhhx.mbgl.param.BasePage;
import lombok.Data;

@Data
public class OrderItemPageParam extends BasePage {
    private Integer orderId;
}
