package com.itplayer.client.system.filter;

import com.itplayer.core.base.utils.JsonUtils;
import com.itplayer.core.base.web.ResponseData;
import com.itplayer.core.system.entity.Manager;
import com.itplayer.client.system.SessionUtils;
import org.apache.http.HttpStatus;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
//        filterChain.doFilter(servletRequest,servletResponse);
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
//                response.sendRedirect(request.getContextPath() + "/login.jsp");
                ResponseData responseData = new ResponseData();
                responseData.setCode(HttpStatus.SC_FORBIDDEN);
                responseData.setMsg("认证失败，未登录");
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter out = null;
                try {
                    out = response.getWriter();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                String dataString = JsonUtils.obj2Str(responseData);
                if (null != out) {
                    out.write(dataString);
                    out.flush();
                    out.close();
                }
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
