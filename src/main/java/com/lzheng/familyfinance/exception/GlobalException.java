package com.lzheng.familyfinance.exception;

/**
 * @ClassName LoginExcption
 * @Author 6yi
 * @Date 2020/5/23 17:12
 * @Version 1.0
 * @Description:
 */


public class GlobalException extends RuntimeException{
    private Integer code;
    private static final long serialVersionUID = 1L;
    public GlobalException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
