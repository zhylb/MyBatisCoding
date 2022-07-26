package com.lihd.mybatis.mapper;

import com.lihd.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

public interface CacheMapper {

    Emp selectById(@Param("eid") Integer eid);

    int insertEmp(Emp emp);


}
