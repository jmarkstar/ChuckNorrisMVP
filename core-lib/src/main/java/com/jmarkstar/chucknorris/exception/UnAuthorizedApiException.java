package com.jmarkstar.chucknorris.exception;

import com.jmarkstar.chucknorris.util.Constants;

/**
 * Created by jmarkstar on 31/05/2017.
 */
public class UnAuthorizedApiException extends Exception {

    public UnAuthorizedApiException(){
        super(Constants.EMPTY);
    }
}
