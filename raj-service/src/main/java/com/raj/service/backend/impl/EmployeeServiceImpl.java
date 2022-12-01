package com.raj.service.backend.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raj.Vo.Result;
import com.raj.constants.CommonEnum;
import com.raj.entity.backend.Employee;
import com.raj.service.backend.EmployeeService;
import com.raj.mapper.backend.EmployeeMapper;
import com.raj.holder.EmployeeHolder;
import com.raj.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author lenovo
 * @description 针对表【t_employee(员工信息)】的数据库操作Service实现
 * @createDate 2022-10-26 09:31:42
 */
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public Result login(String userName, String password) {
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 创建查询
        lambdaQueryWrapper.and(i -> i.eq(userName != null, Employee::getUsername, userName).eq(password != null, Employee::getPassword, password).eq(Employee::getDevStatus, CommonEnum.IS_DELETED_NO.getValue()));
        // 执行查询
        Employee employee = employeeMapper.selectOne(lambdaQueryWrapper);
        // 判断员工是否存在
        if (employee == null) {
            // 不存在返回错误
            return Result.error("用户名或密码错误");
        }
        // 判断员工状态
        if (employee.getStatus() == Integer.parseInt(CommonEnum.EMPLOYEE_AND_USER_ACCOUNT_STATUS_CLOSE.getValue())) {
            return Result.error("你的账号已被锁定，无法登录");
        }
        // 存在使用hutool的UUID创建随机令牌往Redis中创建
        String token = UUIDUtils.getUUID();
        String loginToken = CommonEnum.EMPLOYEE_LOGIN_TOKEN.getValue() + token;
        log.info("生成的token: {}", loginToken);
        log.info("生成的employee: {}", employee);
        stringRedisTemplate.opsForHash().putAll(loginToken, BeanUtil.beanToMap(employee, new HashMap<>(), CopyOptions.create().setIgnoreNullValue(true)
                .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString())));
//         设置登录令牌过期时间
        stringRedisTemplate.expire(loginToken, Long.parseLong(CommonEnum.EMPLOYEE_AND_USER_LOGIN_TIME.getValue()), TimeUnit.MILLISECONDS);
        return Result.success(employee, token);
    }

    @Override
    public Result loginOut(String token) {
        // 判断token是否存在
        StrUtil.isNotBlank(token);
        // 将redis里面的键删除
        Boolean delete = stringRedisTemplate.delete(CommonEnum.EMPLOYEE_LOGIN_TOKEN.getValue() + token);
        //将当前线程的员工进行删除
        EmployeeHolder.removeEmployee();
        if (Boolean.FALSE.equals(delete)) {
            return Result.success("退出失败");
        }
        return Result.success("退出成功");
    }

    @Override
    @Transactional
    public int saveEmployee(Employee employee) {
        //获取当前线程中的员工对象
        Employee loginEmployee = EmployeeHolder.getEmployee();
        log.info("当前线程中的员工对象: {}", employee);
        //封装数据
        //密码进行MD5的加密处理
        employee.setPassword(DigestUtils.md5DigestAsHex(employee.getPassword().getBytes(StandardCharsets.UTF_8)));
        /*employee.setCreateUser(loginEmployee.getId());
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateUser(loginEmployee.getId());
        employee.setUpdateTime(LocalDateTime.now());*/
        employee.setDevStatus(CommonEnum.IS_DELETED_NO.getValue());
        employee.setEmployeeClassify(1);
        employee.setStatus(1);
        return employeeMapper.insert(employee);
    }

    @Override
//    @Cacheable(cacheNames = "employee:page", key = "#pageSize+'-'+#page")
    public Page<Employee> queryEmployeeListForPage(int pageSize, int page, String name) {
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //判断name是否为空
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name).and(i -> i.eq(Employee::getEmployeeClassify, 1));
        //添加排序条件
        lambdaQueryWrapper.orderByDesc(Employee::getUpdateTime);
        Page<Employee> employeePage = new Page<>(page, pageSize);
        return employeeMapper.selectPage(employeePage, lambdaQueryWrapper);
    }

    @Override
    @Transactional
    public int modifyEmployee(Employee employee) {
        //去除当前线程中员工对象
        Employee employeeHolder = EmployeeHolder.getEmployee();
//        LambdaUpdateWrapper<Employee> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        //封装数据
/*        employee.setUpdateTime(LocalDateTime.now());
        log.info("修改时间：{}", employee.getUpdateTime());
        employee.setUpdateUser(employeeHolder.getId());
        log.info("修改者：{}", employee.getUpdateUser());*/
        //设置条件
//        lambdaUpdateWrapper.set(StringUtils.isNotEmpty(employee.getId().toString()), Employee::getId, employee.getId());
        return employeeMapper.updateById(employee);
    }

    @Override
    public Employee queryEmployeeById(Long id) {
        return employeeMapper.selectById(id);
    }
}





