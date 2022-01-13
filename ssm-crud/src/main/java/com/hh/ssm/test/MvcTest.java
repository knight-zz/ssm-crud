package com.hh.ssm.test;

import com.github.pagehelper.PageInfo;
import com.hh.ssm.bean.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * @author zh
 * @date 2022/01/12 15:23
 * spring5整合Junit5测试MVC
 **/
@WebAppConfiguration
@SpringJUnitConfig(locations = {"classpath:applicationContext.xml","classpath:springMVC.xml"})
public class MvcTest {

    // 传入springmvc的IOC
    @Autowired
    WebApplicationContext context;

    // 虚拟mvc请求，获取处理结果
    MockMvc mockMvc;

    @BeforeEach
    public void initMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPage() throws Exception {
        // 模拟get请求拿到返回值
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/emps").param("pn", "6")).andReturn();
        // 请求成功后，请求域中会有pageInfo，可以验证
        MockHttpServletRequest request = result.getRequest();
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
        System.out.println("当前页码："+pageInfo.getPageNum());
        System.out.println("总页码："+pageInfo.getPages());
        System.out.println("总记录数："+pageInfo.getTotal());
        System.out.println("连续显示的页码：");
        for (int i:pageInfo.getNavigatepageNums()){
            System.out.print(i+" ");
        }
        System.out.println();
        // 获取员工数据
        List<Employee> list = pageInfo.getList();
        for (Employee employee:list){
            System.out.println("ID："+employee.getEmpId()+"==>Name:"+employee.getEmpName());
        }
    }
}
