package com.leeiidesu.lib.router;

/**
 * 自定义路由异常
 * Created by liyi on 2018/2/24.
 */

public class RouterServiceException extends RuntimeException {

    public RouterServiceException(String message) {
        super(message);
    }

    public RouterServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RouterServiceException(Throwable cause) {
        super(cause);
    }
}
