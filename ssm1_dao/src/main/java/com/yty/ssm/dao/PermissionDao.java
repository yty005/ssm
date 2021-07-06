package com.yty.ssm.dao;

import com.yty.ssm.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findByRoleId(Integer roleId) throws Exception;
    @Select("select * from permission")
    List<Permission> findAll();
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void savePermission(Permission permission);
    @Select("select * from permission where id = #{permissionId}")
    Permission findById(Integer permissionId);
    @Delete("delete from role_permission where permissionId=#{permissionId}")
    void delPermissionAndRole(Integer permissionId);
    @Delete("delete from permission where id = #{permissionId}")
    void delPermission(Integer permissionId);
}
