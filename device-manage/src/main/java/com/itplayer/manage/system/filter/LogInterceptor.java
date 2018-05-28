package com.itplayer.manage.system.filter;


import com.itplayer.core.base.exception.SystemException;
import com.itplayer.core.base.utils.JsonUtils;
import com.itplayer.core.system.entity.Manager;
import com.itplayer.core.system.entity.SystemLog;
import com.itplayer.core.system.log.PermissionValidate;
import com.itplayer.core.system.service.SystemLogService;
import com.itplayer.manage.cache.Constant;
import com.itplayer.manage.system.SessionUtils;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志记录
 *
 * @author caijun.yang
 */
public class LogInterceptor implements MethodInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SystemLogService systemLogService;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        String className = String.valueOf(invocation.getMethod().getDeclaringClass());
        Method method = invocation.getMethod();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        PermissionValidate permissionValidate = method.getAnnotation(PermissionValidate.class);

        if (permissionValidate == null) {
            return invocation.proceed();
        }
        logger.info("class： {} invoke method： {}, operation type: {}", className, method.getName(), permissionValidate.code());
        Object[] arguments = invocation.getArguments();
        List<Object> argumentsLis = new ArrayList<>();
        if (arguments != null && arguments.length > 0) {
            for (Object obj : arguments) {
                if (obj instanceof HttpServletRequest || obj instanceof HttpServletResponse || obj instanceof HttpSession)
                    continue;
                argumentsLis.add(obj);
            }
        }
        logger.info("access uri : {}, query string : {}, arguments: {}", request.getRequestURI(), request.getQueryString(), JsonUtils.obj2Str(argumentsLis));

        String accessIp = getRealIpAddr(request) == null ? "unkown" : getRealIpAddr(request);
        String username = "unkown";
        String accessUri = request.getRequestURI();
        String accessParams = JsonUtils.obj2Str(argumentsLis);
        Object result = null;
        SystemLog logRecord = null;
        try {
            Manager manager =  SessionUtils.getLoginManager();
            if (manager == null ) logger.info("Not logged in");
            username = manager.getUsername();
            logger.info("username : {}, access_ip :{}, access_uri: {}", username, accessIp, accessUri);
            result = invocation.proceed();
            logger.info("After: result: {}", JsonUtils.obj2Str(result));
            String content = Constant.PERMISSION_MAP.get(permissionValidate.code());
            logRecord = new SystemLog(username, accessIp, permissionValidate.code(), accessUri, accessParams, content);
            logRecord.setStatus(true);
            systemLogService.create(logRecord);
        } catch (SystemException e) {
            if (null != logRecord) {
                logRecord.setStatus(false);
                systemLogService.create(logRecord);
            }
            logger.error("class : {}, method :{} , opetaion type: {}, failure msg :{} ", className, method.getName(), permissionValidate.code(), e.getMessage());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("class : {}, method :{} , operation type:{}, failure msg :{} ", className, method.getName(), permissionValidate.code(), e.getMessage());
            if (null != logRecord) {
                logRecord.setStatus(false);
            }
            throw e;
        }
        return result;
    }

    private String getRealIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-real-ip");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
            if (ip != null && ip.length() != 0) {
                if (ip.indexOf(",") != -1) ip = ip.substring(0, ip.indexOf(","));
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
