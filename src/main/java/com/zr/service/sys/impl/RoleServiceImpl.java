package com.zr.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.zr.mapper.sys.RoleMapper;
import com.zr.service.sys.RoleService;
import com.zr.util.TokenUtil;
import com.zr.vo.sys.Role;

import com.zr.vo.sys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl  implements RoleService {

    @Autowired
    private TokenUtil tokenUtil;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> list(Role role) {
        PageHelper.startPage(role.getPageNum(),role.getPageSize());
        return roleMapper.list(role);
    }

    @Override
    public void add(Role role) {
        role.setCreateId(tokenUtil.getUserId());
        role.setCreateTime(new Date());
        roleMapper.insertSelective(role);
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
}
