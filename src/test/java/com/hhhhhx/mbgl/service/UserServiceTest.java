package com.hhhhhx.mbgl.service;


import com.hhhhhx.mbgl.MbglApplication;
import com.hhhhhx.mbgl.entity.User;
import com.hhhhhx.mbgl.entity.enums.RoleEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MbglApplication.class)
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    IUserService userService;

    @Test
    public void test1() {

        User user = userService.lambdaQuery()
                .eq(User::getId, 1)
                .eq(User::getRoleId, 1)
                .one();
    }
}
