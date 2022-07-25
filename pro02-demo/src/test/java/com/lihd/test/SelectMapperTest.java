package com.lihd.test;
import com.lihd.mybatis.mapper.SelectMapper;
import com.lihd.mybatis.pojo.User;
import com.lihd.mybatis.util.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import java.util.Map;

/**
 * @author : 葬花吟留别1851053336@qq.com
 * &#064;description : TODO
 * &#064;date : 2022/7/25 21:45
 */
public class SelectMapperTest {
    private final SelectMapper mapper;
    {
        SqlSession sqlSession = SqlSessionUtils.build();
        mapper = sqlSession.getMapper(SelectMapper.class);
    }


    /* 
     * 使用单个对象接收返回值 
     * 注意返回值只能有一个 否则会报错。TooManyResultsException
     */
    @Test
    public void selectByUsername() {
//        User poker = mapper.selectByUsername("poker");
        User user = mapper.selectByUsername("update");
        System.out.println(user);
    }
    /* 
     * 使用List接收返回值
     */
    @Test
    public void selectALl() {
        for (User user : mapper.selectALl()) {
            System.out.println(user);
        }
    }
    /*
     * 使用单个值接收返回值
     * 这里的单个值配置了别名
     * int -> _int , _integer
     * Integer -> int , Integer
     * Map -> map
     * String -> string
     */
    @Test
    public void selectCount() {
        Integer a = mapper.selectCount();
        System.out.println(a);
    }
    /*
     * 使用map接收对象
     * 以属性值为 key ,以属性值作为 value
     * 比较常用 : 没有对应表时转换为map, 需要发送json数据时转换为map
     * 注意返回值只能有一个 否则会报错。TooManyResultsException
     * 这里的返回值是 如果要查询的是对象返回的个数。
     */
    @Test
    public void selectMapByUsername() {
        Map<String, Object> update = mapper.selectMapByUsername("update");
        System.out.println(update);
    }
    /*
     * 使用List组成的map接收对象
     * 解决上面的问题，每个对象对应一个map,于是多个对象组成map构成的list
     * 注意 resultType = map 不要写成list
     */
    @Test
    public void selectListMap() {
        for (Map<String, Object> poker : mapper.selectListMap("poker")) {
            System.out.println(poker);
        }
    }
    /*
     * 使用map组成的map接收对象
     * 使用@MapKey("id") 注解的值最好保证 是一个主键或者unique
     * 外层map的键是 注解的值
     * 外层map的值是 每个对象对应的一个map
     */
    @Test
    public void selectMapMap() {
        Map<String, Object> map = mapper.selectMapMap("poker");
        System.out.println(map);
    }
}
