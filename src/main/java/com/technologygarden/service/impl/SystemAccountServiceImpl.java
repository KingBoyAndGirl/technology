package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.MenuMapper;
import com.technologygarden.dao.RoleMapper;
import com.technologygarden.dao.RoleRightsMapper;
import com.technologygarden.entity.Menu;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.entity.Role;
import com.technologygarden.service.SystemAccountService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("systemAccountService")
public class SystemAccountServiceImpl implements SystemAccountService {

    private final MenuMapper menuMapper;
    private final RoleMapper roleMapper;
    private final RoleRightsMapper roleRightsMapper;

    @Autowired
    public SystemAccountServiceImpl(MenuMapper menuMapper, RoleMapper roleMapper, RoleRightsMapper roleRightsMapper) {
        this.menuMapper = menuMapper;
        this.roleMapper = roleMapper;
        this.roleRightsMapper = roleRightsMapper;
    }

    @Override
    public ResultBean<?> getAllMenuWithRights() {

        List<Menu> menuList = menuMapper.selectAllMenuWithRights();
        return new ResultBean<>(menuList);
    }

    @Override
    public ResultBean<?> getAllRole(@NonNull Integer pageNum, @NonNull Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<Role> roleList =  roleMapper.selectAllAdminWithoutPassword();
        PageInfo<?> pageInfo = new PageInfo<>(roleList);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<?> distributeRightsToAccount(Integer id, List<Integer> rightsList) {

        // 先删除该账号之前的权限
        roleRightsMapper.deleteByRoleId(id);

        // 再添加现在分配的权限
        roleRightsMapper.insertForeach(id, rightsList);

        return new ResultBean<>();
    }

    @Override
    public ResultBean<?> updateAdminPassword(Integer id, String newPassword) {

        Role role = Role.builder().id(id).password(newPassword).build();
        roleMapper.updateDynamic(role);
        return new ResultBean<>();
    }
}