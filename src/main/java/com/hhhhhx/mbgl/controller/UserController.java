package com.hhhhhx.mbgl.controller;


import cn.hutool.core.bean.BeanUtil;
import com.hhhhhx.mbgl.dto.UserDTO;
import com.hhhhhx.mbgl.entity.User;
import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.param.UserLoginParam;
import com.hhhhhx.mbgl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
}
