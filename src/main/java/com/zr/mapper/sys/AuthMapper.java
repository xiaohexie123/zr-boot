package com.zr.mapper.sys;

import com.zr.vo.sys.Auth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AuthMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Auth record);

    int insertSelective(Auth record);

    Auth selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    List<Auth> list();

    List<Auth> selectList();

    List<Auth> listById()
}