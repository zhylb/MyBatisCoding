package com.lihd.mybatis.test;

import com.lihd.mybatis.mapper.DeptMapper;
import com.lihd.mybatis.mapper.DynamicSQLMapper;
import com.lihd.mybatis.pojo.Emp;
import com.lihd.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
/**
 * 动态SQL：
 * 1、if：根据标签中test属性所对应的表达式决定标签中的内容是否需要拼接到SQL中
 * 2、where：
 * 当where标签中有内容时，会自动生成where关键字，并且将内容前多余的and或or去掉
 * 当where标签中没有内容时，此时where标签没有任何效果
 * 注意：where标签不能将其中内容后面多余的and或or去掉
 * 3、trim：
 * 若标签中有内容时：
 * prefix|suffix：将trim标签中内容前面或后面添加指定内容
 * suffixOverrides|prefixOverrides：将trim标签中内容前面或后面去掉指定内容
 * 若标签中没有内容时，trim标签也没有任何效果
 * 4、choose、when、otherwise，相当于if...else if...else
 * when至少要有一个，otherwise最多只能有一个
 * 5、foreach
 * collection:设置需要循环的数组或集合
 * item:表示数组或集合中的每一个数据
 * separator:循环体之间的分割符
 * open:foreach标签所循环的所有内容的开始符
 * close:foreach标签所循环的所有内容的结束符
 * 6、sql标签
 * 设置SQL片段：<sql id="empColumns">eid,emp_name,age,sex,email</sql>
 * 引用SQL片段：<include refid="empColumns"></include>
 */
public class DynamicSQLMapperTest {
    private final DynamicSQLMapper mapper;
    {
        SqlSession sqlSession = SqlSessionUtils.build();
        mapper = sqlSession.getMapper(DynamicSQLMapper.class);
    }

    @Test
    public void selectByEqCondition() {
        Emp emp = new Emp();
        emp.setEmpName("曹操");
        emp.setAge(56);
        emp.setSex("男");
        emp.setEmail("caocao@dh.cn");
        List<Emp> emps = mapper.selectByEqCondition(emp);
        emps.forEach(System.out::println);
    }

    @Test
    public void getEmpAgeJudge() {
        Emp emp = new Emp();
        emp.setAge(19);
        String judge = mapper.getEmpAgeJudge(emp);
        System.out.println(judge);
    }

    @Test
    public void insertBatchByList() {
        Emp emp1 = new Emp(0,"aaa",1,"男","a@a.com");
        Emp emp2 = new Emp(0,"bbb",2,"女","b@b.com");
        Emp emp3 = new Emp(0,"ccc",3,"男","c@c.com");
        List<Emp> list = Arrays.asList( emp1, emp2, emp3);
        int row = mapper.insertBatchByList(list);
        System.out.println("row = " + row);
    }

    /*
     * 这种情况一定要保证 arr中有元素
     */
    @Test
    public void deleteBatchByArray() {
        Integer[] arr = {7,8,9};
        arr = new Integer[0];
        mapper.deleteBatchByArray(arr);
    }
}