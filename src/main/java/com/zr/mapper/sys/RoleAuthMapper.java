package com.zr.mapper.sys;

import com.zr.vo.sys.roleAuth;

public interface RoleAuthMapper {
    int insert(roleAuth record);

    int insertSelective(roleAuth record);
}