package com.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.common.sercurity.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zelei.fan
 * @date 2020/12/10 11:54
 * @description
 */
@Repository
@Mapper
public interface UserMenuDAO extends BaseMapper<Menu> {

    @Select("select distinct m.* from t_menu m " +
            "inner join t_role_menu rm on m.id = rm.menu_id " +
            "inner join t_user_role ru on ru.role_id = rm.role_id " +
            "where deleted = 1 and ru.user_id = #{userId} order by m.sequence ")
    List<Menu> listByUserId(Long userId);
}
