package com.itplayer.client.system.filter;

import cn.apiclub.captcha.servlet.SimpleCaptchaServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by caijun.yang on 2016/7/5.
 */
public class CustomCaptchaServlet extends SimpleCaptchaServlet {
    /**
     *
     */
    private static final long serialVersionUID = 6472865774783029994L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("captchaTimeOut", System.currentTimeMillis());
        super.doGet(req, resp);
    }
}
