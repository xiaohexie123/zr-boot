package com.zr.exception;

import com.alibaba.fastjson.JSON;
import com.zr.util.AjaxResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;

/**
 * controller层全局异常处理器
 * controller不需要捕捉异常，有全局处理器捕捉处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理 Exception 异常
     *
     * @param  response
     * @param e                  异常
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(HttpServletResponse response, Exception e) {
        //控制台打印异常
        e.printStackTrace();
        //设置相应代码为500
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        //返回值显示异常信息
        return JSON.toJSONString(new AjaxResult(false,"操作失败:" + e.getMessage()));
    }
}
