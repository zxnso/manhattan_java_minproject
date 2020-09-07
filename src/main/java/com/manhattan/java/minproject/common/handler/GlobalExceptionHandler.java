package com.manhattan.java.minproject.common.handler;


import com.manhattan.java.minproject.common.exception.BizException;
import com.manhattan.java.minproject.common.exception.SystemException;
import com.manhattan.java.minproject.common.result.ErrorCode;
import com.manhattan.java.minproject.common.result.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href="mailto:zhuangxuenan.com">庄学南</a>
 * @version V0.0.1
 * @date 2020/3/21
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler implements ExcetionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResult unhandledException(HttpServletRequest request, Exception e) {
        log.error("unhandledException:{}", e);
        JsonResult result = new JsonResult();
        if (e instanceof BindException) {
            BindException exception = (BindException) e;
            List<ObjectError> errors = exception.getAllErrors();
            String msg = errors.get(0).getDefaultMessage();
            log.error("msg====>" + msg);
            result.setCode(ErrorCode.SYS_EXCEPTION.getCode());
            result.setMessage(ErrorCode.SYS_EXCEPTION.getMsg());
            return result;
        }
        result.setCode(ErrorCode.UNCATCH_EXCEPTION.getCode());
        result.setMessage(ErrorCode.UNCATCH_EXCEPTION.getMsg());
        return result;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public JsonResult argumentValidHandler(HttpServletRequest req, Exception e) {
        log.error("argumentValidHandler:{}", e);
        JsonResult result = new JsonResult();
        List<String> errorList = new ArrayList<>();
        List<ObjectError> allErrors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
        for (ObjectError error : allErrors) {
            errorList.add(error.getDefaultMessage());
        }
        result.setCode(ErrorCode.ARGUMENT_NOT_VALID.getCode());
        result.setMessage(StringUtils.join(errorList, "<br/>"));
        return result;
    }


    @ExceptionHandler(value = {BizException.class, SystemException.class, IllegalArgumentException.class})
    @ResponseBody
    public JsonResult businessExceptionHandler(HttpServletRequest req, Exception e) {
        log.error("businessExceptionHandler:{}", e);
        JsonResult result = new JsonResult();
        if (e instanceof BizException) {
            BizException biz = (BizException) e;
            result.setMessage(biz.getMessage());
            result.setCode(biz.getCode());
            log.error("==>业务错误代码:{},错误消息:{}", biz.getCode(), biz.getMessage());
        }
        if (e instanceof SystemException) {
            SystemException systemException = (SystemException) e;
            result.setMessage(systemException.getMessage());
            result.setCode(systemException.getCode());
            log.error("==>程序错误代码:{},错误消息:{}", systemException.getCode(), systemException.getMessage());
        }
        if (e instanceof IllegalArgumentException) {
            IllegalArgumentException illegalArgumentException = (IllegalArgumentException) e;
            result.setMessage(illegalArgumentException.getMessage());
            result.setCode(ErrorCode.ILLEGAL_ARGUMENT.getCode());
            log.error("==>参数错误代码:{},错误消息:{}", ErrorCode.ILLEGAL_ARGUMENT.getCode(), illegalArgumentException.getMessage());
        }
        return result;
    }

}
