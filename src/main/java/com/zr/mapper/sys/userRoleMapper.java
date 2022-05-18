package com.zr.mapper.sys;

import com.zr.vo.sys.userRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface userRoleMapper {
    int insert(userRole record);

    int insertSelective(userRole record);


    void del(String userId);
}