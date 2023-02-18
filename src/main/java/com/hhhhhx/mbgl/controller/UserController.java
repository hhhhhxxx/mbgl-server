package com.hhhhhx.mbgl.controller;


import com.hhhhhx.mbgl.dto.UserDTO;
import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.param.user.UserLoginParam;
import com.hhhhhx.mbgl.param.user.UserUploadImageParam;
import com.hhhhhx.mbgl.param.user.UserWeixinLoginParam;
import com.hhhhhx.mbgl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hhx
 * @since 2022-09-17
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public RestResponse<UserDTO> login(@Valid @RequestBody UserLoginParam param) {
        return RestResponse.ok(userService.login(param));
    }

    @PostMapping("/wx/login")
    public RestResponse<UserDTO> wxLogin(@Valid @RequestBody UserWeixinLoginParam param) {
        return RestResponse.ok(userService.wxLogin(param));
    }

    @PostMapping("/upload/image")
    public RestResponse<Boolean> login(@Valid @RequestBody UserUploadImageParam param) {
        return RestResponse.ok(userService.upLoadImage(param));
    }
}
