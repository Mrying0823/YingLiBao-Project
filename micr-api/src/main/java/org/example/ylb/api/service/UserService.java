package org.example.ylb.api.service;

import org.example.ylb.api.model.User;

import java.util.List;

/**
 * @author 邓和颖
 * @since 2023/10/4 20:10
 */
public interface UserService {

    // 查询用户表中所有的手机号
    List<String> queryUserPhone();

    // 注册用户
    int userRegister(String phone,String password);

    // 用户登录
    User queryUserByPhoneAndPwd(String phone,String passwd);

    User queryUserByPhone(String phone);
}
