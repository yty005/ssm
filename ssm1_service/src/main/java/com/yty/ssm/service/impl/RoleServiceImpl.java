package com.yty.ssm.service.impl;

import com.yty.ssm.dao.RoleDao;
import com.yty.ssm.domain.Permission;
import com.yty.ssm.domain.Role;
import com.yty.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public void saveRole(Role role) throws Exception{
        roleDao.saveRole(role);
    }

    @Override
    public Role findById(Integer roleid) throws Exception {
        Role role = roleDao.findById(roleid);
        return role;
    }

    @Override
    public List<Permission> findRoleByIdAndAllPermission(Integer roleId) {
        return roleDao.findRoleByIdAndAllPermission(roleId);
    }

    @Override
    public void addPermissionToRole(Integer roleId, Integer[] permissionIds) {
        for (Integer permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    @Override
    public void delRole(Integer roleId) {
        //1.先删除role_permission表数据
        roleDao.delRoleAndPermission(roleId);
        //2.删除users_role表中相关数据
        roleDao.delRoleAndUser(roleId);
        //3.删除role
        roleDao.delRole(roleId);
    }
}
