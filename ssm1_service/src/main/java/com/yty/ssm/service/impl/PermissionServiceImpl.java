package com.yty.ssm.service.impl;

import com.yty.ssm.dao.PermissionDao;
import com.yty.ssm.domain.Permission;
import com.yty.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public void savePermission(Permission permission) {
        permissionDao.savePermission(permission);
    }

    @Override
    public Permission findById(Integer permissionId) {
        return permissionDao.findById(permissionId);
    }

    @Override
    public void delPermission(Integer permissionId) {
        //1.首先删除role_permission表中相关数据
        permissionDao.delPermissionAndRole(permissionId);
        //2.删除permission表中的相关数据
        permissionDao.delPermission(permissionId);
    }
}
