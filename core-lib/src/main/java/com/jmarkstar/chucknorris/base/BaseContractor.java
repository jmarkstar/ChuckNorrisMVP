package com.jmarkstar.chucknorris.base;

import android.support.annotation.NonNull;

/**
 * Created by jmarkstar on 31/05/2017.
 */

public abstract class BaseContractor {

    public interface RemoteView {
        void showProgress();
        void hideProgress();
        void showUnauthorizedError();
        void showEmpty();
        void showError(String errorMessage);
        void showMessageLayout(boolean show);
    }

    public static abstract class BasePresenter<V>{

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
    }
}
