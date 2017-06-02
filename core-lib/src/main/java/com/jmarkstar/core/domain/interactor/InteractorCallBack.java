package com.jmarkstar.core.domain.interactor;

/**
 * Created by jmarkstar on 31/05/2017.
 */
public interface InteractorCallBack<T> {
    void onSuccess(T result);
    void onError(Throwable ex);
}
