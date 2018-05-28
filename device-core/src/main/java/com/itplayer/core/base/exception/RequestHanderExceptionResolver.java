package com.itplayer.core.base.exception;


import com.itplayer.core.base.utils.JsonUtils;
import com.itplayer.core.base.web.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by caijun.yang on 2018/3/15. 统一的异常处理 如需使用 请交给spring管理
 */
public class RequestHanderExceptionResolver implements HandlerExceptionResolver {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        String requestURI = request.getRequestURI();
        e.printStackTrace();
        String message = e.getMessage();
        ResponseData responseData = new ResponseData(ResponseData.ERROR);
        logger.error(e.getMessage());
        if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
            if (e instanceof SystemException) {
                logger.error(e.getMessage());
                responseData.setMsg(e.getMessage());
            } else {
                responseData.setMsg("系统错误！");
            }
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
        } else {// 针对返回错误页面
            try {
                request.setAttribute("message", message);
                request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
