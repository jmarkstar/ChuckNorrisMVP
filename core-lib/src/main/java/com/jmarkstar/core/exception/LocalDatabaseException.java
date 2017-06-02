package com.jmarkstar.core.exception;


import android.support.annotation.StringRes;

import com.jmarkstar.core.util.Constants;

/**
 * Created by jmarkstar on 31/05/2017.
 */
public class LocalDatabaseException extends Exception {

    private int idRsMessage;

    public LocalDatabaseException(@StringRes int idRsMessage){
        super(Constants.EMPTY);
        this.idRsMessage = idRsMessage;
    }

    public int getIdRsMessage() {
        return idRsMessage;
    }
}
