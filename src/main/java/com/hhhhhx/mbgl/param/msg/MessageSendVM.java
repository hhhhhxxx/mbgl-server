package com.hhhhhx.mbgl.param.msg;

import com.hhhhhx.mbgl.param.drugstore.order.Shop;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;


@Data
public class MessageSendVM {
        @NotNull
        private String content;
        @NotNull
        private Integer sendUserId;
        @NotNull
        private Integer receiveUserId;
        @NotNull
        private Integer type;

        private List<Shop>shopList;
}
