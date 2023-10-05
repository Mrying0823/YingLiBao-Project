package org.example.ylb.dataservice.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.example.ylb.api.service.UserService;
import org.example.ylb.dataservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 邓和颖
 * @since 2023/10/4 20:12
 */

@DubboService(interfaceClass = UserService.class,version = "1.0")
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 查询所有用户手机号
    @Override
    public List<String> queryUserPhone() {
        return userMapper.selectUserPhone();
    }
}
