package com.zr.service.sys;

import com.zr.vo.sys.Auth;

import java.util.List;

public interface AuthService {
    List<Auth> list();

    int add(Auth auth);

    int edit(Auth auth);

    int del(Long id);
}
