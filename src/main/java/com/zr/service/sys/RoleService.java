package com.zr.service.sys;

import com.zr.vo.sys.Role;
import com.zr.vo.sys.User;
import com.zr.vo.sys.userRole;

import java.util.List;

public interface RoleService {
    List<Role> list(Role role);

    void add(Role role);

    void edit(Role role);

    void del(Long id);


    List<Role> selectList();
}
