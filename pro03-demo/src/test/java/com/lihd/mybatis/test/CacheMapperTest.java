package com.lihd.mybatis.test;

import com.lihd.mybatis.mapper.CacheMapper;
import com.lihd.mybatis.pojo.Emp;
import com.lihd.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * 在mapper配置文件中添加的cache标签可以设置一些属性：
 * eviction属性：缓存回收策略
 * LRU（Least Recently Used） – 最近最少使用的：移除最长时间不被使用的对象。
 * FIFO（First in First out） – 先进先出：按对象进入缓存的顺序来移除它们。
 * SOFT – 软引用：移除基于垃圾回收器状态和软引用规则的对象。
 * WEAK – 弱引用：更积极地移除基于垃圾收集器状态和弱引用规则的对象。
 * 默认的是 LRU。
 * flushInterval属性：刷新间隔，单位毫秒
 * 默认情况是不设置，也就是没有刷新间隔，缓存仅仅调用语句时刷新
 * size属性：引用数目，正整数
 * 代表缓存最多可以存储多少个对象，太大容易导致内存溢出
 * readOnly属性：只读，true/false
 * true：只读缓存；会给所有调用者返回缓存对象的相同实例。因此这些对象不能被修改。这提供了
 * 很重要的性能优势。
 * false：读写缓存；会返回缓存对象的拷贝（通过序列化）。这会慢一些，但是安全，因此默认是
 * false。
 */
public class CacheMapperTest {

    /*
     * 使用上 一级缓存 的情况 在一个SqlSession中，尽管是不同的Mapper 可以只查询了一次
     *
     */
    @Test
    public void testCacheLevel1_1() {
        SqlSession sqlSession = SqlSessionUtils.build();
        CacheMapper mapper1 = sqlSession.getMapper(CacheMapper.class);
        CacheMapper mapper2 = sqlSession.getMapper(CacheMapper.class);
        System.out.println(mapper1 == mapper2);
        Emp emp1 = mapper1.selectById(1);
        Emp emp2 = mapper2.selectById(1);
    }
    /*
     * 一级缓存失效
     * 使用了不同的SqlSession
     *
     */
    @Test
    public void testCacheLevel1_2() {
        SqlSession sqlSession1 = SqlSessionUtils.build();
        SqlSession sqlSession2 = SqlSessionUtils.build();
        CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
        CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);
        Emp emp1 = mapper1.selectById(1);
        Emp emp2 = mapper2.selectById(1);
    }

    /*
     * 一级缓存失效情况二
     * 查询了不同的数据
     */
    @Test
    public void testCacheLevel1_3() {
        SqlSession sqlSession = SqlSessionUtils.build();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);

        Emp emp1 = mapper.selectById(1);
        Emp emp2 = mapper.selectById(2);
    }

    /*
     * 一级缓存失效情况三
     * 进行了 任何一次DML DDL操作 会清空所有一级缓存
     */
    @Test
    public void testCacheLevel1_4() {
        SqlSession sqlSession = SqlSessionUtils.build();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);

        Emp emp1 = mapper.selectById(1);
        Emp emp = new Emp();
        emp.setEmpName("吕布");
        mapper.insertEmp(emp);
        Emp emp2 = mapper.selectById(1);
    }

    /*
     * 一级缓存失效情况四
     * 手动清空了缓存
     */
    @Test
    public void testCacheLevel1_5() {
        SqlSession sqlSession = SqlSessionUtils.build();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);

        Emp emp1 = mapper.selectById(1);
        sqlSession.clearCache();
        Emp emp2 = mapper.selectById(1);
    }

    /*
     * 测试二级缓存
     */
    @Test
    public void testCacheLevel2_1(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
            SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
            SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
            System.out.println(sqlSession1 == sqlSession2);

            CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
            CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);
            mapper1.selectById(1);
            /*  不能进行增删改操作
            Emp emp = new Emp();
            emp.setEmpName("new");
            mapper1.insertEmp(emp);
            */
            // 必须 提交或者关闭后 才会将一级缓存 缓存到 二级缓存中
//            sqlSession1.close();
            sqlSession1.commit();
            mapper2.selectById(1);
            sqlSession2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}