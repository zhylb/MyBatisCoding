package com.lihd.mybatis.mapper;

import com.lihd.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ParameterMapper {


    User selectUserByParam(@Param("username") String username, @Param("password") String password);

    void insertUser(User user);



    User selectUserByUsername(String username);

    User selectUserByLogin(String username, String password);

    User selectUserByMap(Map<String ,Object> map);

    List<User> selectAll();





}
