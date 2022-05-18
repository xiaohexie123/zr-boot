package com.zr.service.sys.impl;

import com.zr.mapper.sys.AuthMapper;
import com.zr.service.sys.AuthService;
import com.zr.util.TokenUtil;
import com.zr.vo.sys.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthMapper authMapper;

    @Autowired
    private TokenUtil tokenUtil;


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

    @Override
    public int del(Long id) {
        return authMapper.deleteByPrimaryKey(id);
    }
}
