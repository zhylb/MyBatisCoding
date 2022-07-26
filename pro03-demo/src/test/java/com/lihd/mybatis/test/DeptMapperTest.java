package com.lihd.mybatis.test;

import com.lihd.mybatis.mapper.DeptMapper;
import com.lihd.mybatis.pojo.Dept;
import com.lihd.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
/**
 * 解决字段名和属性名不一致的情况：
 * a>为字段起别名，保持和属性名的一致
 * b>设置全局配置，将_自动映射为驼峰
 * <setting name="mapUnderscoreToCamelCase" value="true"/>
 * c>通过resultMap设置自定义的映射关系
 * <resultMap id="empResultMap" type="Emp">
 *     <id property="eid" column="eid"></id>
 *     <result property="empName" column="emp_name"></result>
 *     <result property="age" column="age"></result>
 *     <result property="sex" column="sex"></result>
 *     <result property="email" column="email"></result>
 * </resultMap>
 *
 * 处理多对一的映射关系：
 * a>级联属性赋值
 * b>association
 * c>分步查询
 *
 * 处理一对多的映射关系
 * a>collection
 * b>分步查询
 */
public class DeptMapperTest {
    private final DeptMapper mapper;
    {
        SqlSession sqlSession = SqlSessionUtils.build();
        mapper = sqlSession.getMapper(DeptMapper.class);
    }

    @Test
    public void selectById() {
    }

    @Test
    public void selectAllInfoById() {
        Dept dept = mapper.selectAllInfoById(1);
        System.out.println(dept);
    }

    @Test
    public void selectAllInfoByStep() {
        Dept dept = mapper.selectAllInfoByStep(1);
        System.out.println(dept.getDeptName());
        System.out.println("-----------------");
        System.out.println(dept.getEmps());
    }
}