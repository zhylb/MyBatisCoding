package com.lihd.mybatis.mapper;

import com.lihd.mybatis.pojo.Dept;
import org.apache.ibatis.annotations.Param;

public interface DeptMapper {

    Dept selectById(@Param("did") Integer did);

    Dept selectAllInfoById(@Param("did") Integer did);

    Dept selectAllInfoByStep(@Param("did") Integer did);

}
