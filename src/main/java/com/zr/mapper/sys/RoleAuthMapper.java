package com.zr.mapper.sys;

import com.zr.vo.sys.Auth;
import com.zr.vo.sys.roleAuth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleAuthMapper {
    int insert(roleAuth record);

    int insertSelective(roleAuth record);

    List<Auth> list();

    int delAllByAuthId(Long authId);
}