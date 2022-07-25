package com.lihd.mybatis.mapper;

import com.lihd.mybatis.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SelectMapper {

    User selectByUsername(@Param("username") String username);

    List<User> selectALl();

    Integer selectCount();

    Map<String , Object> selectMapByUsername(@Param("username") String username);


    List<Map<String, Object>> selectListMap(@Param("username") String username);

    @MapKey("id")
    Map<String ,Object> selectMapMap(@Param("username") String username);

}
