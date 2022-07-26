package com.lihd.mybatis.test;

import com.lihd.mybatis.mapper.EmpMapper;
import com.lihd.mybatis.pojo.Emp;
import com.lihd.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
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
public class EmpMapperTest {

    private final EmpMapper mapper;
    {
        SqlSession sqlSession = SqlSessionUtils.build();
        mapper = sqlSession.getMapper(EmpMapper.class);
    }

    /*
     * 属性名和字段名不一致，不会报错，但是值不会被赋上去
     */
    @Test
    public void selectAll() {
        List<Emp> emps = mapper.selectAll();
        emps.forEach(System.out::println);
    }

    @Test
    public void selectAllByMapping() {
        for (Emp emp : mapper.selectAllByMapping()) {
            System.out.println(emp);
        }

    }

    @Test
    public void selectAllInfoById() {
        Emp emp = mapper.selectAllInfoById(1);
        System.out.println(emp);
    }

    @Test
    public void selectAllInfoByStep() {
        Emp emp = mapper.selectAllInfoByStep(1);
        System.out.println(emp);
    }
    /*
     * 开启延迟加载之后 才会有效
     */
    @Test
    public void testLazy01(){
        Emp emp = mapper.selectAllInfoByStep(1);

        System.out.println(emp.getEmpName());

        System.out.println("---------------------");

        System.out.println(emp.getDept().getDeptName());

    }
}