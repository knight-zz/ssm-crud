package com.hh.ssm.service;

import com.hh.ssm.bean.Employee;
import com.hh.ssm.bean.EmployeeExample;
import com.hh.ssm.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zh
 * @date 2022/01/12 15:26
 **/
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }

    @Override
    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    @Override
    public Boolean checkUser(String empName) {
        EmployeeExample example = new EmployeeExample();
        // 定义sql语句where后的查询条件
        EmployeeExample.Criteria criteria = example.createCriteria();
        // 传入查询条件的参数empName
        criteria.andEmpNameEqualTo(empName);
        // 执行查询，查询与当前empName相等的记录数
        long count = employeeMapper.countByExample(example);
        return count == 0;
    }

    @Override
    public Employee getEmp(Integer empId) {
        return employeeMapper.selectByPrimaryKey(empId);
    }

    @Override
    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    @Override
    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatch(List<Integer> delIds) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        // 可以构造出批量删除的sql语句：delete from xxx where id in ( List<>(ids) )
        criteria.andEmpIdIn(delIds);
        employeeMapper.deleteByExample(example);
    }
}
