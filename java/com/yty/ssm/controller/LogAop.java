package com.yty.ssm.controller;

import com.yty.ssm.domain.SysLog;
import com.yty.ssm.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;
    private Date startTime;
    private Class aClass;
    private Method method;
    //前置通知 获取执行的开始时间 执行的类是哪一个 执行的是哪一个方法
    @Before("execution(* com.yty.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws Exception {
        startTime = new Date();//获取当前时间为开始时间
        aClass = jp.getTarget().getClass();//获取访问的类
        String methodName = jp.getSignature().getName();//获取访问的方法的名称
        Object[] args = jp.getArgs();//获取访问的方法的参数数组
        if(args==null||args.length==0){//将该数组转为class数组形式
            method = aClass.getMethod(methodName);
        }else{
            Class[] classes = new Class[args.length];
            for (int i = 0; i < classes.length; i++) {
                classes[i] = args[i].getClass();
            }
            method = aClass.getMethod(methodName, classes);
        }

    }
    //后置通知
    @After("execution(* com.yty.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        Long time = new Date().getTime()-startTime.getTime();
        String url = "";
        if(aClass!=null&&method!=null&&aClass!=LogAop.class){
            RequestMapping classRequest = (RequestMapping) aClass.getAnnotation(RequestMapping.class);
            if(classRequest!=null){
                String[] classValue = classRequest.value();
                RequestMapping methodRequest = method.getAnnotation(RequestMapping.class);
                if(methodRequest!=null){
                    String[] methodValue = methodRequest.value();
                    url = classValue[0]+methodValue[0];
                }

            }
        }


        String ip = request.getRemoteAddr();


        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time);
        sysLog.setIp(ip);
        sysLog.setUrl(url);
        boolean equala = aClass.getName().equals("com.yty.ssm.controller.PhoneCodeController");
        if(!equala){
            SecurityContext context = SecurityContextHolder.getContext();//从上下文获取当前登录的用户
            User user =(User) context.getAuthentication().getPrincipal();
            String username = user.getUsername();
            sysLog.setUsername(username);
        }
        sysLog.setMethod("[类名] "+aClass.getName()+"[方法名] "+method.getName());
        sysLog.setVisitTime(startTime);

        sysLogService.saveSysLog(sysLog);
    }
}
