package com.yty.ssm.dao;

import com.yty.ssm.domain.Permission;
import com.yty.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface RoleDao {
    @Select("select * from role where id in (select roleId from users_role where userid=#{userid})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.yty.ssm.dao.PermissionDao.findByRoleId"))
    })
    public List<Role> findByUserId(int userid) throws Exception;//用户登录
    @Select("select * from role where id=#{roleId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.yty.ssm.dao.PermissionDao.findByRoleId"))
    })
    public Role findById(int roleId) throws Exception;
    @Select("select * from role")
    List<Role> findAll();
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void saveRole(Role role) throws Exception;
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findRoleByIdAndAllPermission(Integer roleId);
    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") Integer roleId,@Param("permissionId") Integer permissionId);
    @Delete("delete from role where id = #{roleId}")
    void delRole(Integer roleId);
    @Delete("delete from role_permission where roleId = #{roleId}")
    void delRoleAndPermission(Integer roleId);
    @Delete("delete from users_role where roleId = #{roleId}")
    void delRoleAndUser(Integer roleId);
}
