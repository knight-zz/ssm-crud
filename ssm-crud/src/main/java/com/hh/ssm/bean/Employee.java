package com.hh.ssm.bean;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer empId;

    // 添加校验规则
    @Pattern(regexp = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})"
            ,message = "用户名必须是2-5位中文或者6-16位英文和数字的组合")
    private String empName;

    private String gender;

    @Pattern(regexp = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$"
            ,message = "邮箱格式不正确")
    private String email;

    private Integer dId;

    // 查询员工的同时也查询到部门
    private Department department;

    //为了给表插入数据，需要提供一个不带部门的构造器
    public Employee(Integer empId, String empName, String gender, String email, Integer dId) {
        this.empId = empId;
        this.empName = empName;
        this.gender = gender;
        this.email = email;
        this.dId = dId;
    }
}