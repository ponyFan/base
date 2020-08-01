package com.base.common.sercurity.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.common.sercurity.entity.Menu;
import com.base.common.sercurity.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
public interface MenuDao extends BaseMapper<Menu> {

    /**
     * 根据角色获取菜单列表
     * @param id
     * @return
     */
    @Select("select * from t_menu m,t_role_menus rm where m.deleted=0 and m.id=rm.menu_id \n" +
            "and rm.role_id = #{id}\n")
    List<Menu> getRoleMenuByRoles(Long id);
}
