package com.manhattan.java.minproject.common.result;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href="mailto:zhuangxuenan.com">庄学南</a>
 * @version V0.0.1
 * @date 2020/3/30
 */
public enum ErrorCode {

    SYS_EXCEPTION(9000, "系统异常,操作失败"),
    ILLEGAL_ARGUMENT(9001, "参数非法"),
    ARGUMENT_NOT_VALID(9002, "参数校验不通过"),
    FEIGN_CONNECT_TIME_OUT(9003, "服务连接超时，操作失败"),
    UNCATCH_EXCEPTION(9999, "系统异常");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsgByCode(String code) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (code.equals(errorCode.getCode())) {
                return errorCode.getMsg();
            }
        }
        return null;
    }
}
