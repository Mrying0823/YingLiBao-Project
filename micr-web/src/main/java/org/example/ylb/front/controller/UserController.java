package org.example.ylb.front.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.example.ylb.common.constants.RedisKey;
import org.example.ylb.common.utils.CommonUtils;
import org.example.ylb.front.view.RespResult;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 邓和颖
 * @since 2023/10/4 20:25
 */
@Api(tags = "用户相关")
@RequestMapping("/v1")
@RestController
public class UserController extends BaseController {

    // 验证手机号是否已注册
    @ApiOperation(value = "检验手机号是否已注册",notes = "注册用户，检验手机是否已注册")
    @ApiImplicitParam(name = "phone",value = "手机号",required = true)
    @GetMapping("/user/phoneExists")
    public RespResult phoneExists(@RequestParam("phone") String phone) {
        RespResult respResult = RespResult.fail();

        // 检验手机号格式是否正确
        if(CommonUtils.checkPhone(phone)) {

            // 创建 Redis Set 实例，可执行与 set 相关的操作
            SetOperations<String,String> set = stringRedisTemplate.opsForSet();

            if(set.size(RedisKey.KEY_USER_PHONE) == 0) {

                // 查询用户表中所有的电话号码
                List<String> phoneList = userService.queryUserPhone();

                // 将电话号码添加到 Redis Set 中
                set.add(RedisKey.KEY_USER_PHONE, phoneList.toArray(new String[0]));

                // 设置过期时间
                stringRedisTemplate.expire(RedisKey.KEY_USER_PHONE, 1, TimeUnit.HOURS);
            }

            // 匹配 Redis 中所有的用户电话号码，检验手机号是否已注册`
            if(Boolean.FALSE.equals(set.isMember(RedisKey.KEY_USER_PHONE, phone))) {

                // 手机号未注册
                respResult = RespResult.ok();

                // 将未注册的手机号加入 redis
                set.add(RedisKey.KEY_USER_PHONE, phone);
            }
        }

        return respResult;
    }
}
