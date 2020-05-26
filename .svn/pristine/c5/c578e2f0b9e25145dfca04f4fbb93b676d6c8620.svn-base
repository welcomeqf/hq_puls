package com.gpdi.hqplus.common.advice;

import com.gpdi.hqplus.common.constants.ErrorCode;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: lianghb
 * @create: 2019-06-03 16:13
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 自定义异常
     *
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(ApplicationException.class)
    public ApplicationException applicationExceptionHandler(final ApplicationException e, HttpServletResponse response) {
        String msg = ExceptionUtil.getMassage(e);
        logger.error(msg);
        response.setStatus(e.getHttpStatus());
        return e;
    }

    /**
     * spring security 权限不足
     *
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ApplicationException accessDeniedExceptionHandler(final Exception e, HttpServletResponse response) {
        String msg = ExceptionUtil.getMassage(e);
        logger.error(msg);
        response.setStatus(ErrorCode.AUTHENTICATION_ERROR.getHttpStatus());
        e.printStackTrace();
        return new ApplicationException(ErrorCode.AUTHENTICATION_ERROR, "权限不足");
    }

    /**
     * 其他异常
     *
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ApplicationException exceptionHandler(final Exception e, HttpServletResponse response) {
        String msg = ExceptionUtil.getMassage(e);
        logger.error(msg);
        response.setStatus(ErrorCode.UNKNOWN_ERROR.getHttpStatus());
        e.printStackTrace();
        return new ApplicationException(ErrorCode.UNKNOWN_ERROR);
    }
}
