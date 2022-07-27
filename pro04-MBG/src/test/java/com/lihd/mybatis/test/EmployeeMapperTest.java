package com.lihd.mybatis.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lihd.mybatis.mapper.EmployeeMapper;
import com.lihd.mybatis.pojo.Employee;
import com.lihd.mybatis.pojo.EmployeeExample;
import com.lihd.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeMapperTest {
    private final EmployeeMapper mapper;
    {
        SqlSession sqlSession = SqlSessionUtils.build();
        mapper = sqlSession.getMapper(EmployeeMapper.class);
    }

    /*
     * 重点理解一下 分页导航的概念
     * Page<Object> startPage = PageHelper.startPage(3, 5);
     * 查询语句执行之前获取 , 其中 返回值可以忽略，因为PageInfo中包含这些数据。
     * 其中pageNum 是页码数，从1开始，
     * Page{count=true, pageNum=3, pageSize=5, startRow=10, endRow=15, total=107, pages=22, reasonable=false, pageSizeZero=false}[Employee{employeeId=110, firstName='John', lastName='Chen', email='JCHEN', phoneNumber='515.124.4269', hireDate=Sun Sep 28 00:00:00 CST 1997, jobId='FI_ACCOUNT', salary=8200.0, commissionPct=null, managerId=108, departmentId=100}, Employee{employeeId=111, firstName='Ismael', lastName='Sciarra', email='ISCIARRA', phoneNumber='515.124.4369', hireDate=Tue Sep 30 00:00:00 CST 1997, jobId='FI_ACCOUNT', salary=7700.0, commissionPct=null, managerId=108, departmentId=100}, Employee{employeeId=112, firstName='Jose Manuel', lastName='Urman', email='JMURMAN', phoneNumber='515.124.4469', hireDate=Sat Mar 07 00:00:00 CST 1998, jobId='FI_ACCOUNT', salary=7800.0, commissionPct=null, managerId=108, departmentId=100}, Employee{employeeId=113, firstName='Luis', lastName='Popp', email='LPOPP', phoneNumber='515.124.4567', hireDate=Tue Dec 07 00:00:00 CST 1999, jobId='FI_ACCOUNT', salary=6900.0, commissionPct=null, managerId=108, departmentId=100},
     * Employee{employeeId=114, firstName='Den', lastName='Raphaely', email='DRAPHEAL', phoneNumber='515.127.4561', hireDate=Wed Dec 07 00:00:00 CST 1994, jobId='PU_MAN', salary=11000.0, commissionPct=null, managerId=100, departmentId=30}]
     *
     *
     * PageInfo<Employee> pageInfo = new PageInfo<>(list, 5);
     * PageInfo 在执行完sql语句后获取，其中 list是查询到的数据 5是指导航分页的个数
     * PageInfo{pageNum=3, pageSize=5, size=5, startRow=11, endRow=15, total=107, pages=22,
     * prePage=2, nextPage=4, isFirstPage=false, isLastPage=false, hasPreviousPage=true,
     * hasNextPage=true, navigatePages=5, navigateFirstPage=1, navigateLastPage=5,
     *  navigatepageNums=[1, 2, 3, 4, 5]}
     *
     */
    @Test
    public void testPage() {

        Page<Object> startPage = PageHelper.startPage(3, 5);
        List<Employee> list = mapper.selectByExample(null);
        PageInfo<Employee> pageInfo = new PageInfo<>(list, 5);
        System.out.println(startPage);
        System.out.println(pageInfo);

        list.forEach(System.out::println);
    }



    @Test
    public void countByExample() {
        int count = mapper.countByExample(null);
        System.out.println(count);
    }

    @Test
    public void deleteByExample() {
    }

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert(){

    }

    @Test
    public void insertSelective() throws ParseException {
        Employee employee = new Employee();
        employee.setEmployeeId(207);
        employee.setLastName("aaa");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2022-07-27");
        employee.setHireDate(date);
        employee.setJobId("MK_REP");
        employee.setEmail("bbb@a.com");
        //非必要设置字段
        employee.setFirstName("hahaha");
        employee.setSalary(11000d);
        mapper.insert(employee);
    }

    @Test
    public void selectByExample() {
        EmployeeExample example = new EmployeeExample();
        example.createCriteria().andCommissionPctIsNull();
//        example.or().andFirstNameLike("a").andSalaryBetween(10000d,20000d);
        List<Employee> list = mapper.selectByExample(example);
        list.forEach(System.out::println);
    }


    @Test
    public void selectByPrimaryKey() {

    }

    @Test
    public void updateByExampleSelective() {
    }

    @Test
    public void updateByExample() {
    }

    /*
     * update employees SET job_id = ? where employee_id = ?
     * 这种方式显然比较好
     */
    @Test
    public void updateByPrimaryKeySelective() {
        Employee employee = new Employee();
        employee.setEmployeeId(207);
        employee.setFirstName(null);
        employee.setJobId("MK_REP");
        mapper.updateByPrimaryKeySelective(employee);
    }

    /*
     * update employees set first_name = ?, last_name = ?, email = ?, phone_number = ?, hire_date = ?, job_id = ?, salary = ?, commission_pct = ?, manager_id = ?, department_id = ? where employee_id = ?
     */
    @Test
    public void updateByPrimaryKey() {
        Employee employee = new Employee();
        employee.setEmployeeId(207);
        employee.setFirstName(null);
        employee.setJobId("MK_REP");
        mapper.updateByPrimaryKey(employee);
    }
}