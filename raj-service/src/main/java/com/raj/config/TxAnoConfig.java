package com.raj.config;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.*;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/10/28 10:50
 * FileName: TxAnoConfig
 * Description: bootAop事务配置类
 */
@Configuration
@Aspect
@Slf4j
public class TxAnoConfig {

    @Autowired
    private DataSource dataSource;

    /**
     * 定义切点表达式路径
     */
    private static final String AOP_POINTCUT_BACKEND_EXPRESSION = "execution(* com.raj.backend.*.*(..))";
    private static final String AOP_POINTCUT_FRONT_EXPRESSION = "execution(* com.raj.front.*.*(..))";
    /**
     * 定义需要进行事务更新的方法前缀
     */
    private static final String[] REQUIRED_RULE_TRANSACTION = {"insert*", "create*", "add*", "save*", "modify*", "update*", "del*", "delete*"};
    /**
     * 定义需要进行事务回滚的方法前缀
     */
    private static final String[] READ_RULE_TRANSACTION = {"select*", "get*", "query*", "search*", "count*", "detail*", "find*"};


    /**
     * 事务管理器
     */
    @Bean("txManger")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }


    /**
     * 事务拦截器
     *
     * @param transactionManager
     * @return
     */
    @Bean("txAdvice")
    public TransactionInterceptor txAdvice(DataSourceTransactionManager transactionManager) {
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        /*只读事务，不做更新操作*/
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        /*设置只读*/
        readOnlyTx.setReadOnly(true);
        /*设置事务隔离级别 级别:已提交的数据*/
        readOnlyTx.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
        /*当前存在事务就使用当前事务,当期那不存在事务，则创建一个新的事务*/
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredTx.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        /*设置事务回滚规则*/
        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        /* 设置超时时间，超时则抛出异常回滚 */
        requiredTx.setTimeout(5);
/*        Map<String, TransactionAttribute> txMap = new HashMap<>();
        txMap.put("add*", requiredTx);
        txMap.put("save*", requiredTx);
        txMap.put("insert*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("edit*", requiredTx);
        txMap.put("remove*", requiredTx);
        txMap.put("delete*", requiredTx);
        txMap.put("get*", readOnlyTx);
        txMap.put("select*", readOnlyTx);
        txMap.put("query*", readOnlyTx);
        txMap.put("*", readOnlyTx);
        source.setNameMap(txMap);*/
        // 设置方法
        for (String s : READ_RULE_TRANSACTION) {
            source.addTransactionalMethod(s, requiredTx);
        }
        /*设置方法*/
        for (String s : REQUIRED_RULE_TRANSACTION) {
            source.addTransactionalMethod(s, readOnlyTx);
        }
        log.info("加载事务拦截器");
        return new TransactionInterceptor(transactionManager, source);
    }

//    /**
//     * 切面拦截规则
//     */
//    @Bean
//    public DefaultPointcutAdvisor defaultPointcutAdvisor(TransactionInterceptor txAdvice) {
//        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
//        defaultPointcutAdvisor.setAdvice(txAdvice);
//        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//        pointcut.setExpression("execution (* com.raj.backend.*.*(..))");
//        defaultPointcutAdvisor.setPointcut(pointcut);
//        return defaultPointcutAdvisor;
//    }

    /**
     * 定义切面拦截规则
     */
    @Bean
    public Advisor defaultPointcutAdvisor(TransactionInterceptor txAdvice) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        //引入切点表达式
        pointcut.setExpression(AOP_POINTCUT_BACKEND_EXPRESSION);
        pointcut.setExpression(AOP_POINTCUT_FRONT_EXPRESSION);
        log.info("加载切面拦截规则");
        return new DefaultPointcutAdvisor(pointcut, txAdvice);
    }


    @Pointcut(value = AOP_POINTCUT_BACKEND_EXPRESSION + "," + AOP_POINTCUT_FRONT_EXPRESSION)
    public void pointcut() {

    }


}
