package com.yty.ssm.service;

import com.yty.ssm.domain.Permission;

import java.util.List;

public interface PermissionService {
    public List<Permission> findAll() throws Exception;

    void savePermission(Permission permission);

    Permission findById(Integer permissionId);

    void delPermission(Integer permissionId);
}
