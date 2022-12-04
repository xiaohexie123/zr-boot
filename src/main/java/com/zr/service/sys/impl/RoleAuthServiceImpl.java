package com.zr.service.sys.impl;

import com.zr.mapper.sys.RoleAuthMapper;
import com.zr.service.sys.RoleAuthService;
import com.zr.vo.sys.Auth;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleAuthServiceImpl  implements RoleAuthService {

    @Resource
    private RoleAuthMapper roleAuthMapper;

    @Override
    public List<Auth> list() {
        return roleAuthMapper.list();
    }
}
