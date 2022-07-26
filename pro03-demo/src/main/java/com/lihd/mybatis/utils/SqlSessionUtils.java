package com.lihd.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/7/25 15:42
 */
public class SqlSessionUtils {

    public static SqlSession build() {
        try {

            return new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession(true);
        } catch (IOException e) {
            throw new RuntimeException("获取失败");
        }
    }
}
