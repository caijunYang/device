package com.itplayer.client.system;

import com.itplayer.core.system.entity.Manager;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
    public static final String LOGIN_MANAGER = "login_manager";


    public static HttpServletRequest getRemoteRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpSession getSession() {
        HttpServletRequest remoteRequest = getRemoteRequest();
        HttpSession session = remoteRequest.getSession();
        return session;
    }

    public static void putLoginManager(Manager manager) {
        HttpSession session = getSession();
        session.setAttribute(LOGIN_MANAGER, manager);
    }

    public static Manager getLoginManager() {
        HttpSession session = getSession();
        Manager manager = (Manager) session.getAttribute(LOGIN_MANAGER);
        return manager;
    }

    public static void clearLoginManager() {
        getSession().removeAttribute(LOGIN_MANAGER);
    }
}
