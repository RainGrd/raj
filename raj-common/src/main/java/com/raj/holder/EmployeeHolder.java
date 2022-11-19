package com.raj.holder;

import com.raj.entity.backend.Employee;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/10/26 13:09
 * FileName: EmployeeHolder
 * Description: 员工持有者
 */
public class EmployeeHolder {
    /**
     * 注册本地线程
     */
    private static final ThreadLocal<Employee> threadLocal = new ThreadLocal<>();

    public static void setEmployee(Employee employee) {
        threadLocal.set(employee);
    }

    public static Employee getEmployee() {
        return threadLocal.get();
    }

    public static void removeEmployee() {
        threadLocal.remove();
    }

}
