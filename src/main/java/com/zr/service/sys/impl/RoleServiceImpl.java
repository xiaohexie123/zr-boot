package com.zr.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.zr.mapper.sys.RoleAuthMapper;
import com.zr.mapper.sys.RoleMapper;
import com.zr.service.sys.RoleService;
import com.zr.util.TokenUtil;
import com.zr.vo.sys.Role;

import com.zr.vo.sys.User;
import com.zr.vo.sys.roleAuth;
import com.zr.vo.sys.userRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl  implements RoleService {

    @Autowired
    private TokenUtil tokenUtil;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleAuthMapper roleAuthMapper;

    @Override
    public List<Role> list(Role role) {
        PageHelper.startPage(role.getPageNum(),role.getPageSize());
        return roleMapper.list(role);
    }

    @Override
    public void add(Role role) {
        role.setCreateId(tokenUtil.getUserId());
        role.setCreateTime(new Date());
        //新增绝色，获取新增后的角色id
        roleMapper.insertSelective(role);
        //新增角色权限
        if (role.getAuths()!= null){
            for (Long authId : role.getAuths()){
                roleAuth roleAuth = new roleAuth();
                roleAuth.setRoleId(role.getId());
                roleAuth.setAuthId(authId);
                roleAuthMapper.insert(roleAuth);
            }
        }
    }

    @Override
    public void edit(Role role) {
        role.setModifyId(tokenUtil.getUserId());
        role.setModifyTime(new Date());
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void del(Long id) {
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Role> selectList() {
        return roleMapper.selectList();
    }


}
