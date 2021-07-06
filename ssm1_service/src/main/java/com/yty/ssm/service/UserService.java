package com.yty.ssm.service;

import com.yty.ssm.domain.Role;
import com.yty.ssm.domain.UserInfo;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public List<UserInfo> findAll() throws Exception;

    public void saveUser(UserInfo userInfo) throws Exception;

    public UserInfo findById(Integer id) throws Exception;

    List<Role> findUserByIdAndAllRole(Integer id) throws Exception;

    void addRoleToUser(Integer userId, Integer[] roles);
}
