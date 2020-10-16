package com.xiao.blog.exception.handler;


import com.xiao.blog.exception.BaseException;
import com.xiao.blog.exception.BusinessException;
import com.xiao.blog.pojo.response.ErrorResponse;
import com.xiao.blog.shiro.exception.VerCodeEmptyException;
import com.xiao.blog.shiro.exception.VerCodeErrorException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * <p>全局异常处理器</p>
 *
 * @author wangmx
 * @date 2019/5/2
 */

@RestControllerAdvice
@Slf4j
public class UnifiedExceptionHandler {

    /**
     * 业务异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ErrorResponse handleBusinessException(BaseException e, HttpServletResponse response) throws IOException {

        log.error(e.getMessage());

        return new ErrorResponse(1,e.getMessage());
    }

    /**
     * 业务异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    @ResponseBody
    public ErrorResponse handleSQLIntegrityConstraintViolationException(BaseException e, HttpServletResponse response) throws IOException {

        log.error(e.getMessage());

        return new ErrorResponse(1,e.getMessage());
    }


    /**
     * 登录验证异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = {IncorrectCredentialsException.class, AuthenticationException.class})
    public ModelAndView handleIncorrectCredentialsException(AuthenticationException e, Model model) throws IOException {

        ModelAndView modelAndView = new ModelAndView();
        if(e instanceof VerCodeEmptyException){
            model.addAttribute("msg",e.getMessage());
        }else if(e instanceof VerCodeErrorException){
            model.addAttribute("msg",e.getMessage());
        }else{
            model.addAttribute("msg","账号或者密码错误");
        }

        modelAndView.setViewName("admin/login.html");

        return modelAndView;
    }


}
