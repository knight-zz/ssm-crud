import com.hh.ssm.bean.Department;
import com.hh.ssm.bean.Employee;
import com.hh.ssm.dao.DepartmentMapper;
import com.hh.ssm.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Random;
import java.util.UUID;

/**
 * @author zh
 * @date 2022/01/11 21:03
 * spring项目可以使用spring整合Junit5的单元测试，可以自动注入组件
 * 1、导入spring-test模块和Junit5
 * 2、使用@Autowired修饰要使用的组件即可
 **/
@SpringJUnitConfig(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;

    @Test
    public void testCRUD(){
//        System.out.println("这里输出："+departmentMapper);
        // 1.插入几个部门
//        departmentMapper.insertSelective(new Department(null,"行政部"));
//        departmentMapper.insertSelective(new Department(null,"财务部"));
//        departmentMapper.insertSelective(new Department(null,"保安部"));
//        departmentMapper.insertSelective(new Department(null,"销售部"));
        // 2.员工插入
//        employeeMapper.insertSelective(new Employee(null,"jj","男","jj@qq.com",1));

        // 3.批量插入员工，
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Random random = new Random();
        String[] genders = {"男","女"};
        for (int i=0;i<1000;++i){
            String s = UUID.randomUUID().toString().substring(0, 5) + (i % 10);
            employeeMapper.insertSelective(new Employee(
                    null,
                    s,
                    genders[random.nextInt(2)],
                    s+"@qq.com",
                    random.nextInt(6)+1));
        }
        System.out.println("批量插入成功");
    }
}
