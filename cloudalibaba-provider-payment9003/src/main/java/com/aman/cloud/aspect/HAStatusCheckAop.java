package com.aman.cloud.aspect;

import com.aman.cloud.annotation.HAStatusCheck;

import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.aspectj.lang.reflect.MethodSignature;

//import com.common.enums.ha.ServerPort;
//import com.common.enums.ha.ServerType;
//import com.common.ha.HAStatusCheckService;

/**
 * 切点类
 *
 * @version 1.0
 * @Description AOP拦截判断 HA节点是否开启状态方法Service层
 * @Author Justin zeng
 * @Date 2019-04-19
 */
@Aspect
@Component
@Slf4j
public class HAStatusCheckAop {

//    //注入Service
//    @Resource
//    private HAStatusCheckService hAStatusCheckService;

    //Service层切点  自定义的注解类路径
    @Pointcut("@annotation(com.aman.cloud.annotation.HAStatusCheck)")
    public void servicePointCut() {
    }

    Object proceed = null;

    @Around("servicePointCut()")
    public void logAroundService(ProceedingJoinPoint jointPoint) throws Throwable {
//        log.info("------判断节点开始------");
//        ArrayList<String> list = new ArrayList<String>();
//        list.add("192.168.1.5");
//        if (!hAStatusCheckService.isActAsActiveNode(list, ServerPort.BILLING_HANDLER,  ServerType.BILLING_HANDLER)) {
//            LOG.info("------访问请求不合法！------");
//            return;
//            //强制退出
//            //System.exit(0);
//        }else {
        log.info("===============判断注解开始===============");
        //获得当前访问的class
        Class<?> className = jointPoint.getTarget().getClass();
        //获得访问的方法名
        String methodName = jointPoint.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature) jointPoint.getSignature()).getParameterTypes();

//        String serviceMthodDescription = getServiceMthodDescription(jointPoint);
//        log.info(">>>>>>>>>>>>>> serviceMthodDescription : {}", serviceMthodDescription);

        try {
            // 得到访问的方法对象
            Method method = className.getDeclaredMethod(methodName, argClass);
            // 判断是否存在@HAStatusCheck注解
            if (method.isAnnotationPresent(HAStatusCheck.class)) {
                HAStatusCheck annotation = method.getAnnotation(HAStatusCheck.class);
                annotation.description();
                log.info("当前目标执行方法：" + methodName + "执行开始");
                //执行当前目标中的方法
                proceed = jointPoint.proceed();
                log.info("当前目标class路径：" + className);
                log.info("当前目标执行方法名：" + methodName);
                log.info("当前目标执行方法的参数类型：" + argClass);
                log.info("当前目标方法执行的结果：" + proceed);
                log.info("当前目标执行方法：" + methodName + "执行结束");
            }
        } catch (Exception e) {
            log.error("不存在@HAStatusCheck注解", e);
        }
//        }
        log.info("===============判断注解结束===============");
    }

    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(HAStatusCheck.class).description();
                    log.info("======" + description + "======");
                    break;
                }
            }
        }
        return description;
    }

}