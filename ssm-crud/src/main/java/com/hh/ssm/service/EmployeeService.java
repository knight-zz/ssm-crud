package com.hh.ssm.service;

import com.hh.ssm.bean.Employee;

import java.util.List;

/**
 * @author KT
 */
public interface EmployeeService {
    List<Employee> getAll();

    void saveEmp(Employee employee);

    // 查询名字是否重复
    Boolean checkUser(String empName);

    Employee getEmp(Integer empId);

    void updateEmp(Employee employee);

    void deleteEmp(Integer id);

    void deleteBatch(List<Integer> delIds);
}
