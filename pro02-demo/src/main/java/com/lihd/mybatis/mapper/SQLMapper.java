package com.lihd.mybatis.mapper;

import com.lihd.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**  dynamic
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/7/25 22:39
 */
public interface SQLMapper {

    List<User> selectFuzzyUsername(@Param("username") String username);


    int deleteBatch(@Param("ids") String ids);


    List<User> selectFromDynamicTable(@Param("tableName") String tableName);



    void insertUser(User user);


}
