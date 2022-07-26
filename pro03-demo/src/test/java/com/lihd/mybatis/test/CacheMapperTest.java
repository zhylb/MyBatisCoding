package com.lihd.mybatis.test;

import com.lihd.mybatis.mapper.CacheMapper;
import com.lihd.mybatis.pojo.Emp;
import com.lihd.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import static org.junit.Assert.*;

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
}