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
}
