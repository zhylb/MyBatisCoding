package com.lihd.mybatis.pojo;

import java.util.Date;

public class Employee {

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", hireDate=" + hireDate +
                ", jobId='" + jobId + '\'' +
                ", salary=" + salary +
                ", commissionPct=" + commissionPct +
                ", managerId=" + managerId +
                ", departmentId=" + departmentId +
                '}';
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.employee_id
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    private Integer employeeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.first_name
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    private String firstName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.last_name
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    private String lastName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.email
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.phone_number
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    private String phoneNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.hire_date
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    private Date hireDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.job_id
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    private String jobId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.salary
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    private Double salary;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.commission_pct
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    private Double commissionPct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.manager_id
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    private Integer managerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employees.department_id
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    private Integer departmentId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.employee_id
     *
     * @return the value of employees.employee_id
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.employee_id
     *
     * @param employeeId the value for employees.employee_id
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.first_name
     *
     * @return the value of employees.first_name
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.first_name
     *
     * @param firstName the value for employees.first_name
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.last_name
     *
     * @return the value of employees.last_name
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.last_name
     *
     * @param lastName the value for employees.last_name
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.email
     *
     * @return the value of employees.email
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.email
     *
     * @param email the value for employees.email
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.phone_number
     *
     * @return the value of employees.phone_number
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.phone_number
     *
     * @param phoneNumber the value for employees.phone_number
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.hire_date
     *
     * @return the value of employees.hire_date
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public Date getHireDate() {
        return hireDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.hire_date
     *
     * @param hireDate the value for employees.hire_date
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.job_id
     *
     * @return the value of employees.job_id
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.job_id
     *
     * @param jobId the value for employees.job_id
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.salary
     *
     * @return the value of employees.salary
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public Double getSalary() {
        return salary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.salary
     *
     * @param salary the value for employees.salary
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.commission_pct
     *
     * @return the value of employees.commission_pct
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public Double getCommissionPct() {
        return commissionPct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.commission_pct
     *
     * @param commissionPct the value for employees.commission_pct
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public void setCommissionPct(Double commissionPct) {
        this.commissionPct = commissionPct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.manager_id
     *
     * @return the value of employees.manager_id
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public Integer getManagerId() {
        return managerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.manager_id
     *
     * @param managerId the value for employees.manager_id
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column employees.department_id
     *
     * @return the value of employees.department_id
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column employees.department_id
     *
     * @param departmentId the value for employees.department_id
     *
     * @mbggenerated Wed Jul 27 10:57:01 CST 2022
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}