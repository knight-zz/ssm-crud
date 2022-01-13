package com.hh.ssm.service;

import com.hh.ssm.bean.Department;

import java.util.List;

/**
 * @author KT
 */
public interface DepartmentService {
    //获取部门信息
    List<Department> getDepts();

}
