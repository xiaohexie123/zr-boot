package com.zr.mapper.sys;

import com.zr.vo.sys.userRole;

public interface userRoleMapper {
    int insert(userRole record);

    int insertSelective(userRole record);
}