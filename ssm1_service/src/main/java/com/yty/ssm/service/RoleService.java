package com.yty.ssm.service;

import com.yty.ssm.domain.Permission;
import com.yty.ssm.domain.Role;

import java.util.List;

public interface RoleService {
    public List<Role> findAll() throws Exception;

    void saveRole(Role role) throws Exception;

    Role findById(Integer roleid) throws Exception;

    List<Permission> findRoleByIdAndAllPermission(Integer roleId);

    void addPermissionToRole(Integer roleId, Integer[] permissionIds);

    void delRole(Integer roleId);
}
