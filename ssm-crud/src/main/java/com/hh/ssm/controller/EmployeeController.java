package com.hh.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hh.ssm.bean.Employee;
import com.hh.ssm.bean.Msg;
import com.hh.ssm.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理员工CRUD请求
 * @author zh
 * @date 2022/01/12 14:50
 **/
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     * 多个要删除的id之间用‘-’分隔
     * @param ids 多个id组成的字符串
     */
    @DeleteMapping("/emp/{ids}")
    @ResponseBody
    public Msg deleteEmpById(@PathVariable("ids") String ids){
        if (ids.contains("-")) {
            String[] strIds = ids.split("-");
            //组装ids数组
            List<Integer> delIds = new ArrayList<>();
            for (String strId : strIds) {
                delIds.add(Integer.parseInt(strId));
            }
            employeeService.deleteBatch(delIds);
        } else {
            Integer empId = Integer.parseInt(ids);
            employeeService.deleteEmp(empId);
        }
        return Msg.success();
    }

    @PutMapping("/emp/{empId}")
    @ResponseBody
    public Msg updateEmp(Employee employee){
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    @GetMapping("/emp/{empId}")
    @ResponseBody
    public Msg getEmp(@PathVariable("empId") Integer empId){
        Employee employee = employeeService.getEmp(empId);
        return Msg.success().add("emp",employee);
    }

    @RequestMapping("/emps")
    /* 将返回结果转换成json字符串 */
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1") Integer pageNum) {
        // 引入pagehelper插件实现分页
        // 传入参数为页码和每页显示数据条数
        PageHelper.startPage(pageNum,10);
        // startpage后紧跟的方法就是一个分页查询
        List<Employee> emps = employeeService.getAll();
        // 使用pageInfo对查询结果封装，然后传给页面就行
        // 参数为（emps：查询的数据，5：连续显示的页数）
        PageInfo pageInfo = new PageInfo(emps, 5);
        return Msg.success().add("pageInfo",pageInfo);
    }

    /**
     * 检查用户名是否可用
     * @param empName
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkUser")
    public Msg checkUser(@RequestParam("empName") String empName){
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        // 1.用户名组合合法校验
        if (!empName.matches(regx)) {
            return Msg.fail().add("va_msg", "用户名必须是2-5位中文或者6-16位英文和数字的组合");
        }
        //2、用户名数据库校验
        if (employeeService.checkUser(empName)) {
            return Msg.success();
        }
        return Msg.fail().add("va_msg", "用户名不可用");
    }

    /**
     * 后端JSR303校验，保存用户新增信息
     * @param employee 在需要校验的Bean对象前添加 @Valid 开启校验功能
     * @param result BindingResult封装了前面Bean的校验结果
     */
    @PostMapping(value = "/emp")
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result){
        if(result.hasErrors()){
            //校验失败，在模态框中显示错误信息
            Map<String, Object> map = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                map.put(error.getField(), error.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        }else{
            employeeService.saveEmp(employee);
            return Msg.success();
        }
    }
}
