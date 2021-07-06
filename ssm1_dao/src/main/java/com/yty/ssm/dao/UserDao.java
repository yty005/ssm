package com.yty.ssm.dao;

import com.yty.ssm.domain.Role;
import com.yty.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "PASSWORD"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "STATUS"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.yty.ssm.dao.RoleDao.findByUserId"))
    })
    public UserInfo findByUserName(String userName) throws Exception;
    @Select("select * from users")
    public List<UserInfo> findAll() throws Exception;
    @Insert("insert into users(email,username,password,phoneNum,STATUS) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    public void saveUser(UserInfo userInfo) throws Exception;
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "PASSWORD"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "STATUS"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.yty.ssm.dao.RoleDao.findByUserId"))
    })
    public UserInfo findById(Integer id) throws Exception;
    @Select("select * from role where id not in (select roleId from users_role where userid=#{id})")
    List<Role> findUserByIdAndAllRole(Integer id) throws Exception;
    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") Integer userId,@Param("roleId") Integer roleId);//多个参数指定名字
}
