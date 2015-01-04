package com.tw.core.Exceptions;

/**
 * Created by Wenjie Chen on 12/26/14.
 */
public class P2pException extends RuntimeException {
    public final String code;
    public final Object data;

    public P2pException(String code, String message,Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }
    public P2pException(String code,String message) {
        super(message);
        this.code = code;
        this.data = null;
    }
    public P2pException(String code, Throwable cause) {
        super(cause);
        this.code = code;
        this.data = null;
    }
}
