package com.lihd.test;

import com.lihd.mybatis.mapper.SQLMapper;
import com.lihd.mybatis.mapper.SelectMapper;
import com.lihd.mybatis.pojo.User;
import com.lihd.mybatis.util.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.*;
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
    /*
     * 获取自增的主键值
     * userGeneratedKeys 代表包含了主键
     * keyProperty 代表 把获取的自增主键设置到 User的id属性中
     * 注意 不能通过返回值设置，因为返回值 代表的含义已经固定 ： 就是受到影响的行数。
     */
    @Test
    public void insertUser() {
        User user = new User(null, "北京东路", "ts", 18, "女", "nan@mem.org");
        System.out.println(user);
        mapper.insertUser(user);
        System.out.println(user);
    }

    /*
     * 回顾jdbc 如何获取自增的主键值。
     * 既然jdbc能够做到，mybatis作为优秀的框架也一定有一种机制可以获取到这个值。
     */
    @Test
    public void testJDBC() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:13306/mybatis";
        String username = "root";
        String password = "abc123";
        Connection conn = DriverManager.getConnection(url, username, password);
        String sql ="insert into t_user(id, username) values (?,?)";
        //预编译sql对象时，要开启获取自增主键值，（不开启好像也没报错）
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        //设置 占位符的值 注意 index从1开始
        ps.setObject(1, 0);
        ps.setObject(2,"jdbc");

        //不是update 因此要执行 executeUpdate 返回受到影响的行数
        int line = ps.executeUpdate();

        //通过 ps.getGeneratedKeys 获取结果集，自增的主键值就在其中。
        ResultSet rs = ps.getGeneratedKeys();
        ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();
        if(rs.next()){
            for (int i = 0; i < count; i++) {
                System.out.println(rs.getObject(i + 1));
            }
        }

        System.out.println("line = " + line);
    }
}