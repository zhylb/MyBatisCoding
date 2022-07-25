package com.lihd.mybatis;

import com.lihd.mybatis.mapper.UserMapper;
import com.lihd.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/7/25 10:30
 */
public class MyTest {

    /**
     * Test02 :
     *
     *
     *
     */
    @Test
    public void test02 () throws IOException {
        //使用匿名的方式减少代码行数 其实和下面的道理是一样的。
        UserMapper userMapper = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml")).openSession(true).getMapper(UserMapper.class);

        //userMapper.insert();
        //userMapper.delete();
        //userMapper.update();


        /*User user = userMapper.selectById();
        System.out.println(user);*/

        List<User> users = userMapper.selectAll();
        users.forEach(System.out::println);


    }


    /**
     * Test01 :
     *
     *
     *
     */
    @Test
    public void test01 () {
        try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
            // 1 创建抽象工程模式对象
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            // 2 创建工程模式对象
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
            // 3 获取sqlSession对象 可以在参数中设置true
            // 其含义是开启自动提交sqlSessionFactory.openSession(true);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            // 4 动态代理模式创建 mapper对象
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int val = mapper.insert();
            //如果没有设置自动提交，需要手动提交
//            sqlSession.commit();

        } catch (IOException e) {

        }
    }

}
