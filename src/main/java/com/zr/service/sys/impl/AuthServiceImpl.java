package com.zr.service.sys.impl;

import com.zr.mapper.sys.AuthMapper;
import com.zr.mapper.sys.RoleAuthMapper;
import com.zr.service.sys.AuthService;
import com.zr.util.TokenUtil;
import com.zr.vo.sys.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthMapper authMapper;

    @Autowired
    private TokenUtil tokenUtil;

    @Resource
    private RoleAuthMapper roleAuthMapper;


    @Override
    public List<Auth> list() {
        return authMapper.list();
    }

    @Override
    public int add(Auth auth) {
        auth.setCreateId(tokenUtil.getUserId());
        auth.setCreateTime(new Date());
        return authMapper.insertSelective(auth);
    }

    @Override
    public int edit(Auth auth) {
        auth.setModifyId(tokenUtil.getUserId());
        auth.setModifyTime(new Date());
        return authMapper.updateByPrimaryKeySelective(auth);
    }

    @Transactional
    @Override
    public int del(Long id) {
        //删除角色权限表
        roleAuthMapper.delAllByAuthId(id);
        return authMapper.deleteByPrimaryKey(id);
    }
}
