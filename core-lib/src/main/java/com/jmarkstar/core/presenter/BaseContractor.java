package com.jmarkstar.core.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.util.Log;
import com.jmarkstar.core.R;
import com.jmarkstar.core.exception.LocalDatabaseException;
import com.jmarkstar.core.exception.NetworkException;
import com.jmarkstar.core.exception.UnAuthorizedApiException;

/**
 * Created by jmarkstar on 31/05/2017.
 */

public abstract class BaseContractor {

    public interface BaseView {
        void showProgress();
        void hideProgress();
        void showErrorMessage(@StringRes int errorMessage);
        void showErrorMessage(String errorMessage);
    }

    public static abstract class BasePresenter<V extends BaseView>{

        protected V mView;

        public final void attachView(@NonNull V view) {
            mView = view;
        }

        public final void detachView() {
            mView = null;
        }

        protected final boolean isViewAttached() {
            return mView != null;
        }

        protected final void errorHandler(Throwable ex){
            ex.printStackTrace();
            if(ex instanceof LocalDatabaseException){
                mView.showErrorMessage(((LocalDatabaseException)ex).getIdRsMessage());
            } else if(ex instanceof UnAuthorizedApiException){
                mView.showErrorMessage(R.string.exception_api_unauthorized);
            } else if(ex instanceof NetworkException){
                NetworkException nEx = (NetworkException)ex;
                Log.v("BaseContractor", "code = "+nEx.getHttpCode());
                if(nEx.getHttpCode() == 404){
                    mView.showErrorMessage(R.string.exception_404_not_found);
                }
            }
        }
    }
}
