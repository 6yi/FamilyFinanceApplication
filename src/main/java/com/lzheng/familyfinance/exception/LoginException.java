package com.lzheng.familyfinance.exception;

/**
 * @ClassName LoginExcption
 * @Author 6yi
 * @Date 2020/5/23 17:12
 * @Version 1.0
 * @Description:
 */


public class LoginException extends RuntimeException{
    private String code;

    public LoginException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
