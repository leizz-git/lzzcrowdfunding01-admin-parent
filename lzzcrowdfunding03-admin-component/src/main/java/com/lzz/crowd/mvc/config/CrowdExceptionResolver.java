package com.lzz.crowd.mvc.config;

import com.google.gson.Gson;
import com.lzz.crowd.constant.CrowdConstant;
import com.lzz.crowd.constant.CrowdConstantEnum;
import com.lzz.crowd.exception.AccessForbiddenException;
import com.lzz.crowd.exception.LoginAcctAlreadyInUseException;
import com.lzz.crowd.exception.LoginAcctAlreadyInUseForUpdateException;
import com.lzz.crowd.exception.LoginFailedException;
import com.lzz.crowd.util.CrowdUtil;
import com.lzz.crowd.util.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// ControllerAdvice表示当前类是一个基于注解的异常处理器类
@ControllerAdvice
public class CrowdExceptionResolver {

    @ExceptionHandler(value = LoginAcctAlreadyInUseForUpdateException.class)
    public ModelAndView resolveLoginAcctAlreadyInUseForUpdateException(
            LoginAcctAlreadyInUseForUpdateException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String viewName = "system-error";

        //返回
        return commonResolve(viewName,exception,request,response);
    }

    @ExceptionHandler(value = LoginAcctAlreadyInUseException.class)
    public ModelAndView resolveLoginAcctAlreadyInUseException(
            LoginAcctAlreadyInUseException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String viewName = "admin-add";

        //返回
        return commonResolve(viewName,exception,request,response);
    }

    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolveLoginFailedException(
            LoginFailedException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String viewName = "admin-login";

        //返回
        return commonResolve(viewName,exception,request,response);
    }

    @ExceptionHandler(value = AccessForbiddenException.class)
    public ModelAndView resolveAccessForbiddenException(
            AccessForbiddenException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        String viewName = "admin-login";

        return commonResolve(viewName, exception, request, response);
    }

    // ExceptionHandler将一个具体的异常类型和一个方法关联起来
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolveNullPointerException(
            // 实际捕获到的异常对象
            NullPointerException exception,
            // 当前请求对象
            HttpServletRequest request,

            // 当前响应对象
            HttpServletResponse response) throws IOException {

        String viewName = "system-error";

        //返回
        return commonResolve(viewName,exception,request,response);
    }

    private ModelAndView commonResolve(
            String viewName,
            Exception exception,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        //1.判断当前请求类型
        boolean judgeResult = CrowdUtil.judgeRequestType(request);

        //2.如果是Ajax对象
        if (judgeResult){
            //3.创建ResultEntity对象
            ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());

            //4.创建Gson对象
            Gson gson = new Gson();

            //5.将ResultEntity对象转换为JSON字符串
            String json = gson.toJson(resultEntity);

            //6.将JSON字符串作为响应体返回给浏览器
            response.getWriter().write(json);

            //7.由于上面已经通过原生的response对象返回了响应，所以不提供ModelAndView对象
            return null;
        }

        //8.如果不是Ajax请求则创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();

        //9. 将Exception对象存入模型
        modelAndView.addObject(CrowdConstantEnum.ATTR_NAME_EXCEPTION.value(),exception);
        //10. 设置对应的视图名称
        modelAndView.setViewName(viewName);

        //返回
        return modelAndView;
    }

}