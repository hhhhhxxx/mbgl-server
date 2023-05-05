package com.hhhhhx.mbgl.controller.user;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hhhhhx.mbgl.dto.UserDTO;
import com.hhhhhx.mbgl.entity.User;
import com.hhhhhx.mbgl.entity.result.RestResponse;
import com.hhhhhx.mbgl.param.user.UserLoginParam;
import com.hhhhhx.mbgl.param.user.UserPageParam;
import com.hhhhhx.mbgl.param.user.UserUploadImageParam;
import com.hhhhhx.mbgl.param.user.UserWeixinLoginParam;
import com.hhhhhx.mbgl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    public RestResponse<Boolean> upLoadImage(@Valid @RequestBody UserUploadImageParam param) {
        return RestResponse.ok(userService.upLoadImage(param));
    }

    @GetMapping("/get/nameImage/{id}")
    public RestResponse<UserDTO> getNameImageById(@NotNull @PathVariable("id") Integer id) {
        return RestResponse.ok(userService.getNameImageById(id));
    }


    //-----------------------

    @GetMapping("/page/list")
    public RestResponse<IPage<User>> pageList(@Valid UserPageParam param) {
        return RestResponse.ok(userService.pageList(param));
    }

    @GetMapping("/get/{id}")
    public RestResponse<User> getOne(@NotNull @PathVariable("id") Integer id) {
        return RestResponse.ok(userService.getById(id));
    }

    @PostMapping("/su")
    public RestResponse<Boolean> pageList(@RequestBody User user) {
        return RestResponse.ok(userService.saveOrUpdate(user));
    }
    @PostMapping("/delete/{id}")
    public RestResponse<Boolean> delete(@NotNull @PathVariable("id") Integer id) {
        return RestResponse.ok(userService.removeById(id));
    }
}
