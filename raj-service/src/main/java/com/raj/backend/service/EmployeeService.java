package com.raj.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raj.Vo.Result;
import com.raj.entity.backend.Employee;

/**
* @author lenovo
* @description 针对表【t_employee(员工信息)】的数据库操作Service
* @createDate 2022-10-26 09:31:42
*/
public interface EmployeeService {



    /**
     * 管理员登录方法
     * @param userName
     * @param password
     * @return java.lang.String
     */
    public Result login(String userName, String password);

    /**
     * 退出登录方法
     * @param token
     */
    Result loginOut(String token);

    /**
     * 增加员工的方法
     * @param employee
     * @return
     */
    int saveEmployee(Employee employee);

    Page<Employee> queryEmployeeListForPage(int pageSize, int page, String name);

    int modifyEmployee(Employee employee);

    Employee queryEmployeeById(Long id);
}
