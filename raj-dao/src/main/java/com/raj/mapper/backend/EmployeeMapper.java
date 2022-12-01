package com.raj.mapper.backend;

import com.raj.entity.backend.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author lenovo
* @description 针对表【t_employee(员工信息)】的数据库操作Mapper
* @createDate 2022-10-26 09:31:42
* @Entity com.raj.employee.entity.Employee
*/
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {


}




