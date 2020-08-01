package com.base.common.sercurity.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.common.sercurity.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zelei.fan
 * @date 2020/7/30 23:33
 * @description
 */
@Mapper
@Repository
public interface RoleDao extends BaseMapper<Role> {

    /**
     * 根据用户id获取用户角色
     * @param id
     * @return
     */
    @Select("select r.* from t_role r,t_user_roles ur where r.id=ur.role_id and ur.user_id=#{id}")
    Role getUserRoleByUserId(Long id);
}
