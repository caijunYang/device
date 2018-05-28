package com.itplayer.manage.system.filter;

import com.itplayer.core.system.entity.Manager;
import com.itplayer.manage.system.SessionUtils;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SystemLoginFilter implements Filter {
    public static Set<String> annoUrl = new HashSet<String>();
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String urls = filterConfig.getInitParameter("annoUrls");
        String[] split = urls.split(",");
        if (null != split && split.length > 0) {
            for (String url : split) {
                annoUrl.add(url);
            }
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String remoteAddr = request.getRequestURI();
        boolean match = false;
        for (String url : annoUrl) {
            match = antPathMatcher.match(url, remoteAddr);
            if (match) {
                break;
            }
        }
        if (match) {
            filterChain.doFilter(request, response);
        } else {
            Manager loginManager = (Manager) request.getSession().getAttribute(SessionUtils.LOGIN_MANAGER);
            if (null == loginManager) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
