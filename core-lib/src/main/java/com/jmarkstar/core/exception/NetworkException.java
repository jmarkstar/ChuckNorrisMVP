package com.jmarkstar.core.exception;

/**
 * Created by jmarkstar on 7/06/2017.
 */

public class NetworkException extends Exception {

    private int httpCode;

    public NetworkException(int httpCode){
        this.httpCode = httpCode;
    }

    public int getHttpCode() {
        return httpCode;
    }
}
