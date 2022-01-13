package com.hh.ssm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author KT
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private Integer deptId;
    private String deptName;
}