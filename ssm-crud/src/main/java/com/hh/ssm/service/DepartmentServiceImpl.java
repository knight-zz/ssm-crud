package com.hh.ssm.service;

import com.hh.ssm.bean.Department;
import com.hh.ssm.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zh
 * @date 2022/01/12 19:07
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getDepts() {
        // 返回所有部门信息
        return departmentMapper.selectByExample(null);
    }
}
