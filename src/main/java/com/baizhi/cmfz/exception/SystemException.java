package com.baizhi.cmfz.exception;

/**
 * @author Miles
 * @Title: SystemException
 * @ProjectName cmfz
 * @Date 2018/12/19--18:59
 */
public class SystemException extends RuntimeException {
    private String message;

    public SystemException(String message) {
        super(message);
    }
}
