package com.lihd.test;

import com.lihd.mybatis.mapper.SQLMapper;
import com.lihd.mybatis.mapper.SelectMapper;
import com.lihd.mybatis.pojo.User;
import com.lihd.mybatis.util.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
/**
 * 在三个角度解释了 为什么 有的时候需要使用 ${}
 * 一般情况下 尽量采用#{} 迫不得已时采用 ${}
 */
public class SQLMapperTest {

    private final SQLMapper mapper;
    {
        SqlSession sqlSession = SqlSessionUtils.build();
        mapper = sqlSession.getMapper(SQLMapper.class);
    }
    /*
     * 模糊查询不能使用 '%#{}%' 因为#{}会被替换为 ?,而?在引号中不会被解析
     */
    @Test
    public void selectFuzzyUsername() {
        for (User user : mapper.selectFuzzyUsername("a")) {
            System.out.println(user);
        }
    }

    /*
     * 批量删除
     * 如果使用#{} 报错信息 -> 数据截断：截断不正确的 DOUBLE 值
     * 错误的写法 这样会被解析成 '6,10,15' 由于是字符串，会隐式转换为0，无法做到批量删除
     */
    @Test
    public void deleteBatch() {
        int batch = mapper.deleteBatch("6,10,15");
        System.out.println("batch = " + batch);
    }

    /*
     * 动态表名
     * 由于#{} 解析会加上单引号，而mysql中表名是不能加单引号的
     * 字符串，日期，字段可以加上单引号
     * 使用#{}的报错信息 You have an error in your SQL syntax
     */
    @Test
    public void selectFromDynamicTable() {
        List<User> list = mapper.selectFromDynamicTable("t_user");
        list.forEach(System.out::println);
    }
}