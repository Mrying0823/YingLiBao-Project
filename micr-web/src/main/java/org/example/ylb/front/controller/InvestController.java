package org.example.ylb.front.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.ylb.api.pojo.invest.InvestRankInfo;
import org.example.ylb.common.constants.RedisKey;
import org.example.ylb.common.utils.CommonUtils;
import org.example.ylb.front.view.RespResult;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author 邓和颖
 * @since 2023/9/26 16:27
 */
@Api(tags = "投资相关")
@RequestMapping("/v1")
@RestController
public class InvestController extends BaseController {

    // 投资排行榜
    @ApiOperation(value = "投资排行榜",notes = "投资金额前三名用户的手机号、投资金额")
    @GetMapping("/invest/rank")
    public RespResult showInvestRank() {
        // 从 redis 查询数据
        // 取倒序前三个数据
        Set<ZSetOperations.TypedTuple<String>> sets = stringRedisTemplate.boundZSetOps(RedisKey.KEY_INVEST_RANK).reverseRangeWithScores(0, 2);

        RespResult respResult = RespResult.ok();

        // lambda 表达式遍历 sets 集合
        if (sets != null && !sets.isEmpty()) {
            List<InvestRankInfo> rankInfoList = new ArrayList<>();

            sets.forEach(tuple -> {
                // 手机号、投资金额
                rankInfoList.add(new InvestRankInfo(CommonUtils.maskPhone(tuple.getValue()),tuple.getScore()));
            });

            respResult.setList(rankInfoList);

            return respResult;
        }

        // sets 集合不为空则使用缓存数据
        List<InvestRankInfo> rankInfoList = bidInfoService.getInvestRank();

        rankInfoList = rankInfoList.stream().peek(rank -> {
            // 添加至缓存
            stringRedisTemplate.opsForZSet().add(RedisKey.KEY_INVEST_RANK,rank.getPhone(),rank.getMoney());

            // 一小时后过期
            stringRedisTemplate.expire(RedisKey.KEY_INVEST_RANK,1, TimeUnit.HOURS);

            // 对手机号进行敏感处理
            rank.setPhone(CommonUtils.maskPhone(rank.getPhone()));
        }).collect(Collectors.toList());

        respResult.setList(rankInfoList);

        return respResult;
    }
}
