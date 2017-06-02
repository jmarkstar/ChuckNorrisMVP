package com.jmarkstar.core.exception;

import com.jmarkstar.core.util.Constants;

/**
 * Created by jmarkstar on 31/05/2017.
 */
public class UnAuthorizedApiException extends Exception {

    public UnAuthorizedApiException(){
        super(Constants.EMPTY);
    }
}
