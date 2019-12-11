package com.technologygarden.service.impl;

import com.technologygarden.dao.RoleMapper;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Role;
import com.technologygarden.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public ResultBean<Role> getRoleByAccount(String account) {

        Role role = roleMapper.selectByAccount(account);
        return new ResultBean<>(role);
    }
}