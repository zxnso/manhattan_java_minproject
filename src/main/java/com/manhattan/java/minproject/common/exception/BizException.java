package com.manhattan.java.minproject.common.exception;


/**
 * <p>
 *
 * </p>
 *
 * @author <a href="mailto:zhuangxuenan.com">庄学南</a>
 * @version V0.0.1
 * @date 2020/3/18
 */

public class BizException extends CommonException {
    public BizException(Integer code, String message) {
        super(code, message);
    }

    public BizException(Integer code, String message, Exception e) {
        super(code, message, e);
    }
}
