package com.lihd.test;

import com.lihd.mybatis.mapper.ParameterMapper;
import com.lihd.mybatis.pojo.User;
import com.lihd.mybatis.util.SqlSessionUtils;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/7/25 15:45
 */
public class ParameterTest {
    private final ParameterMapper mapper;
    {
        SqlSession sqlSession = SqlSessionUtils.build();
        mapper = sqlSession.getMapper(ParameterMapper.class);
    }

    /**
     * Test05 : 测试有多个参数的情况2
     * mybatis不允许函数重载。
     * 我们自己把参数放到一个map中，这属于手动的方式。比如下面的代码。
     * 这样在配置文件中可以做到见名知意。 但是这里的代码就复杂了。
     */
    @Test
    public void test05 (){
        Map<String ,Object> map = new HashMap<>();
        map.put("username","update");
        map.put("password","123");
        User user = mapper.selectUserByMap(map);
        System.out.println(user);
    }


    /**
     * Test04 : 测试有多个参数的情况
     * mybatis会把多个参数放到一个map中 每个值多对应了两个键
     * 格式一 ： arg0, arg1, arg2 ... 从0开始
     * 格式二 ：param1,param2, ... 从2开始
     * 两种格式可以混合使用，因为都在一个map中。但是由于名字很奇怪，这种方式可能不常用。
     */
    @Test
    public void test04 (){
        User user = mapper.selectUserByLogin("update", "123");
        System.out.println(user);
    }

    /**
     * Test03 : 测试只有一个参数时的情况。
     * 这个参数名可以随便写，但是最好见名知意
     */
    @Test
    public void test03 (){
        User user = mapper.selectUserByUsername("update");
        System.out.println(user);
    }



    /**
     * Test02 : 引出 ${} 和 #{}
     * ${} : 类似于下面的拼串拼接参数 -> 使用时一定要注意字符串的拼接问题（单引号）可能出现sql注入
     * #{} : 类似于下面的占位符
     *
     */
    @Test
    public void test02 () throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:13306/mybatis";
        String user = "root";
        String password = "abc123";
        Connection conn = DriverManager.getConnection(url, user, password);
        String username = "update";
        String sql;
        //方式一 使用拼串
        //问题一 ：拼串需要自己拼，容易出错，可读性差
        //问题二 ：sql注入问题
        sql = "select * from t_user where username = '"+username+"'";
        //方式二 使用占位符
        sql = "select * from t_user where username = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        //......后续操作......
        ps.setObject(1,"update");

        ResultSet rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        if (rs.next()) {
            for (int i = 0; i < columnCount; i++) {
                System.out.println(rs.getObject(i + 1));
            }
        }
    }


    /**
     * Test01 : 测试功能是否完整
     */
    @Test
    public void test01 (){
        List<User> list = mapper.selectAll();
        list.forEach(System.out::println);
    }
}
