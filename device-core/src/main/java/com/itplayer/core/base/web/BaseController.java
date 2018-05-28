package com.itplayer.core.base.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by caijun.yang on 2017/7/27.
 */
public abstract class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected ResponseData success() {
        return new ResponseData();
    }

    protected ResponseData success(Object data) {
        return new ResponseData().putData(data);
    }

    protected ResponseData faild(String erroMsg) {
        return new ResponseData(ResponseData.ERROR, erroMsg);
    }

    protected ResponseData faild() {
        return new ResponseData(ResponseData.ERROR);
    }


}
