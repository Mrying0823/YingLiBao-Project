package org.example.ylb.dataservice.mapper;

import org.example.ylb.api.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author 邓和颖
 */
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    // 统计注册人数
    int selectCountUser();
}