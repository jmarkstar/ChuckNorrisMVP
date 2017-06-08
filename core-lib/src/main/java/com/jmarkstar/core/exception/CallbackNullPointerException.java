package com.jmarkstar.core.exception;

import android.support.annotation.StringRes;
import com.jmarkstar.core.util.Constants;

/**
 * Created by jmarkstar on 6/06/2017.
 */
public class CallbackNullPointerException extends IllegalArgumentException {

    private int idRsMessage;

    public CallbackNullPointerException(@StringRes int idRsMessage){
        super(Constants.EMPTY);
        this.idRsMessage = idRsMessage;
    }

    public int getIdRsMessage() {
        return idRsMessage;
    }
}
