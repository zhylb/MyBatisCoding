package com.lihd.mybatis.mapper;

import com.lihd.mybatis.pojo.User;

import java.util.List;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/7/25 9:48
 */
public interface UserMapper {

    int insert();

    void delete();

    void update();

    User selectById();

    List<User> selectAll();



}
