package com.lihd.mybatis.mapper;

import com.lihd.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {

    List<Emp> selectAll();

    List<Emp> selectAllByMapping();


    Emp selectAllInfoById(@Param("eid") Integer eid);

    Emp selectAllInfoByStep(@Param("eid") Integer eid);

    List<Emp> selectByDept(@Param("did") Integer did );

}
