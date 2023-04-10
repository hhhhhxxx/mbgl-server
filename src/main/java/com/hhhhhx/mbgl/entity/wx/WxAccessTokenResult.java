package com.hhhhhx.mbgl.entity.wx;

import lombok.Data;

@Data
public class WxAccessTokenResult {
    private String access_token;
    private String expires_in;
}
