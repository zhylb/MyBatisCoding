package com.lihd.mybatis.mapper;

import com.lihd.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/7/26 21:03
 */
public interface DynamicSQLMapper {

    List<Emp> selectByEqCondition(Emp emp);

    String getEmpAgeJudge(Emp emp);

    int insertBatchByList(@Param("emps") List<Emp> emps);

    int deleteBatchByArray(@Param("ids") Integer[] ids);

}
