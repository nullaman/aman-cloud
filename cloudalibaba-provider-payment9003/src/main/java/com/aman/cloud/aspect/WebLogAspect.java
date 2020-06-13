package com.aman.cloud.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aman.cloud.entities.Payment;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class WebLogAspect {

    /**
     * 进入方法时间戳
     */
    private Long startTime;
    /**
     * 方法结束时间戳(计时)
     */
    private Long endTime;

    public WebLogAspect() {
    }

    /**
     * 定义请求日志切入点，其切入点表达式有多种匹配方式,这里是指定路径
     * execution 表达式的主体
     * 第一个* 代表任意的返回值
     * com.aman.cloud.controller所横切的包名
     * 包后面.. 表示当前包及其子包
     * 第二个* 表示类名，代表所有类
     * .*(..) 表示任何方法,括号代表参数 .. 表示任意参数
     */
    @Pointcut("execution(public * com.aman.cloud.controller..*.*(..))")
    public void webLogPointcut() {
    }

    /**
     * 前置通知：
     * 1. 在执行目标方法之前执行，比如请求接口之前的登录验证;
     * 2. 在前置通知中设置请求日志信息，如开始时间，请求参数，注解内容等
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLogPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取请求头中的User-Agent
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        //打印请求的内容
        startTime = System.currentTimeMillis();
        log.info("***********************【Before】 begin******************************");
        log.info("请求开始时间：{}", LocalDateTime.now());
        log.info("请求Url : {}", request.getRequestURL().toString());
        log.info("请求方式 : {}", request.getMethod());
        log.info("请求ip : {}", request.getRemoteAddr());
        log.info("请求方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());


        Signature signature = joinPoint.getSignature();
        log.info("******joinPoint.getSignature()******");
        log.info("请求方法:signature.getDeclaringTypeName():" + signature.getDeclaringTypeName());
        log.info("class 请求方法:signature.getDeclaringType():" + signature.getDeclaringType());
        log.info("GET/POST:signature.getName():" + signature.getName());
        log.info("signature.getModifiers():" + signature.getModifiers());
        log.info("******joinPoint.getSignature()******");
        if (signature.getDeclaringType().toString().toUpperCase().equals("POST")) {
            Object[] args = joinPoint.getArgs();
            for (int i = 0; i < args.length; i++) {
                System.out.println(args[i]);
                Object o = args[i];
                String s = JSON.toJSONString(o);
                JSONObject jsonObject = JSON.parseObject(s);
                Payment payment = JSON.toJavaObject(jsonObject, Payment.class);
                System.out.println("json:" + JSON.toJSONString(payment));
            }
        }
        log.info("请求参数 : {}", Arrays.toString(joinPoint.getArgs()));
        // 系统信息
        log.info("浏览器：{}", userAgent.getBrowser().toString());
        log.info("浏览器版本：{}", userAgent.getBrowserVersion());
        log.info("操作系统: {}", userAgent.getOperatingSystem().toString());
        log.info("***********************【Before】 end******************************");
    }

    /**
     * 返回通知：
     * 1. 在目标方法正常结束之后执行
     * 1. 在返回通知中补充请求日志信息，如返回时间，方法耗时，返回值，并且保存日志信息
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "webLogPointcut()")
    public void doAfterReturning(Object ret) throws Throwable {
        endTime = System.currentTimeMillis();
        log.info("***********************【After】 begin******************************");
        log.info("请求结束时间：{}", LocalDateTime.now());
        log.info("请求耗时：{}", (endTime - startTime));
        // 处理完请求，返回内容
        log.info("请求返回 : {}", ret);
        log.info("***********************【After】 end******************************");
    }

    /**
     * 异常通知：
     * 1. 在目标方法非正常结束，发生异常或者抛出异常时执行
     * 1. 在异常通知中设置异常信息，并将其保存
     *
     * @param throwable
     */
    @AfterThrowing(value = "webLogPointcut()", throwing = "throwable")
    public void doAfterThrowing(Throwable throwable) {
        // 保存异常日志记录
        log.info("***********************【Throwing Exception】 begin******************************");
        log.error("发生异常时间：{}", LocalDateTime.now());
        log.error("抛出异常：{}" + throwable.getMessage());
        log.info("***********************【Throwing Exception】 begin******************************");
    }
}