package com.sohu.spaces.version.ssh.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(GlobalHandlerExceptionResolver.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        logger.error(e.getMessage(),e);
        ModelAndView modelAndView = new ModelAndView("error");
        if (e instanceof DataAccessException) {
            modelAndView.setViewName("error-business");
        }else{
            //其他异常判断
        }
        //通知开发
        modelAndView.addObject("error",ExceptionUtils.getStackTrace(e));
        return modelAndView;
    }
}
