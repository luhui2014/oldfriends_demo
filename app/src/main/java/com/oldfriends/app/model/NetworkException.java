package com.oldfriends.app.model;

/**
 * 自定义错误处理类
 */
public class NetworkException extends Exception {

    public NetworkException() {
        super();
    }

    public NetworkException(String detailMessage) {
        super(detailMessage);
    }



}
