package com.base.common.sercurity.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.common.sercurity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author zelei.fan
 * @date 2020/7/30 23:32
 * @description
 */
@Mapper
@Repository
public interface UserDao extends BaseMapper {

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    @Select("select * from t_user where username=#{username}")
    UserEntity getUserByUsername(String username);

    /**
     * 新增用户
     * @param user
     */
    @Insert("insert into t_user (username,password) values(#{user.username},#{user.password})")
    void insertUser(@Param("user") UserEntity user);

}
