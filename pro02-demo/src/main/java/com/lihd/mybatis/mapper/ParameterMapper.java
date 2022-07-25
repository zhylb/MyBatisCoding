package com.lihd.mybatis.mapper;

import com.lihd.mybatis.pojo.User;

import java.util.List;
import java.util.Map;

public interface ParameterMapper {



    User selectUserByUsername(String username);

    User selectUserByLogin(String username, String password);

    User selectUserByMap(Map<String ,Object> map);

    List<User> selectAll();





}
