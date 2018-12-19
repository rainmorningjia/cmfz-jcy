package com.baizhi.cmfz.exception;

/**
 * @author Miles
 * @Title: ManagerException
 * @ProjectName cmfz
 * @Date 2018/12/19--19:04
 */
public class ManagerException extends RuntimeException {
    private String message;

    public ManagerException(String message) {
        super(message);
    }
}
