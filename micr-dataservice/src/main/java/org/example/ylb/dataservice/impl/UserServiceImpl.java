package org.example.ylb.dataservice.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.example.ylb.api.model.FinanceAccount;
import org.example.ylb.api.model.User;
import org.example.ylb.api.service.UserService;
import org.example.ylb.common.constants.RedisKey;
import org.example.ylb.common.utils.CommonUtils;
import org.example.ylb.dataservice.mapper.FinanceAccountMapper;
import org.example.ylb.dataservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 邓和颖
 * @since 2023/10/4 20:12
 */

@DubboService(interfaceClass = UserService.class,version = "1.0")
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    private FinanceAccountMapper financeAccountMapper;

    private StringRedisTemplate stringRedisTemplate;

    // 为配置里的盐绑定字段
    @Value("${salt}")
    private String salt;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setFinanceAccountMapper(FinanceAccountMapper financeAccountMapper) {
        this.financeAccountMapper = financeAccountMapper;
    }

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    // 查询所有用户手机号
    @Override
    public List<String> queryUserPhone() {
        return userMapper.selectUserPhone();
    }

    // 注册新用户
    // 设置线程同步操作，保护多个线程访问共享资源时的线程安全性
    // 设置事务处理，操作失败回滚错误
    @Transactional(rollbackFor = Exception.class)
    @Override
    public synchronized int userRegister(String phone, String passwd) {

        // 默认操作失败
        int result = 0;

        // 需要在配置文件写入关于 redis 的配置信息
        SetOperations<String,String> set = stringRedisTemplate.opsForSet();

        // 检查参数
        if(CommonUtils.checkPhone(phone)
                && passwd != null && passwd.length() == 32
                // 验证手机是否已注册
                && Boolean.FALSE.equals(set.isMember(RedisKey.KEY_USER_PHONE, phone))) {

            // 密码的 MD5 二次加密，加盐
            // 使用 codec 专业的加密解密工具库
            String saltPasswd = DigestUtils.md5Hex(passwd+salt);

            // 注册新用户
            User user = new User();
            user.setPhone(phone);
            user.setLoginPassword(saltPasswd);
            user.setAddTime(new Date());

            userMapper.insertNewUserReturnId(user);

            // 开通资金账户
            FinanceAccount financeAccount = new FinanceAccount();
            financeAccount.setUid(user.getId());

            // 字符串表示法可以精确地表示小数点和所有位数，不会引入舍入误差
            // 使用字符串初始化 BigDecimal
            financeAccount.setAvailableMoney(new BigDecimal("0"));

            financeAccountMapper.insertNewFinanceAccount(financeAccount);

            // 操作成功
            result = 1;

            // 将当前注册的手机号加入 redis
            set.add(RedisKey.KEY_USER_PHONE, phone);
        }

        return result;
    }

    // 获取 redis 用户电话号码集合，这个不符合我的要求
    public Set<String> getUserPhonesFromRedis() {
        SetOperations<String, String> set = stringRedisTemplate.opsForSet();

        // 尝试从 Redis 获取用户电话号码集合
        Set<String> userPhones = set.members(RedisKey.KEY_USER_PHONE);

        if (userPhones == null || userPhones.isEmpty()) {
            // 查询用户表中所有的电话号码
            List<String> phoneList = userMapper.selectUserPhone();

            // 将电话号码添加到 Redis Set 中
            set.add(RedisKey.KEY_USER_PHONE, phoneList.toArray(new String[0]));

            // 设置过期时间
            stringRedisTemplate.expire(RedisKey.KEY_USER_PHONE, 1, TimeUnit.HOURS);
        }

        return userPhones;
    }


    // 用户登录
    @Override
    public User queryUserByPhoneAndPwd(String phone, String passwd) {

        User user = null;

        Set<String> userPhones = getUserPhonesFromRedis();

        // 检查参数
        if(CommonUtils.checkPhone(phone)
                && passwd != null && passwd.length() == 32
                // 验证手机是否已注册
                && userPhones.contains(phone)) {

            String saltPasswd = DigestUtils.md5Hex(passwd+salt);

            user = userMapper.selectUserByPhoneAndPwd(phone,saltPasswd);
            if(user != null) {
                user.setLastLoginTime(new Date());
                userMapper.updateLastLoginTime(user);
            }
        }

        return user;
    }

    @Override
    public User queryUserByPhone(String phone) {

        User user = null;

        Set<String> userPhones = getUserPhonesFromRedis();

        // 检查参数
        if(CommonUtils.checkPhone(phone)
                // 验证手机是否已注册
                && userPhones.contains(phone)) {

            user = userMapper.selectUserByPhone(phone);
            if(user != null) {
                user.setLastLoginTime(new Date());
                userMapper.updateLastLoginTime(user);
            }
        }

        return user;
    }
}
