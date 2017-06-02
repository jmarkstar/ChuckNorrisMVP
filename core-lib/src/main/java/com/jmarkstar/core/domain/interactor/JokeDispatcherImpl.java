package com.jmarkstar.core.domain.interactor;

import com.jmarkstar.core.domain.interactor.executor.Executor;
import com.jmarkstar.core.domain.interactor.executor.MainThread;
import com.jmarkstar.core.domain.model.JokeModel;
import com.jmarkstar.core.domain.repository.manager.JokeDataManager;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by jmarkstar on 2/06/2017.
 */
public class JokeDispatcherImpl implements JokeDispatcher {

    @Inject Executor mExecutor;
    @Inject MainThread mMainThread;
    @Inject JokeDataManager mJokeDataManager;

    @Inject public JokeDispatcherImpl() {}

    @Override public void fetchJokesInteractor(final boolean refresh, final int count, final Callback<ArrayList<JokeModel>> callback) {
        if (null == callback) {
            throw new IllegalArgumentException("Callback can't be null");
        }
        this.mExecutor.execute(new Interactor() {
            @Override public void run() {
                mJokeDataManager.getJokes(refresh, count, callback);
            }
        });
    }

    /*
    private void notifyFetchAlbumPhotosSuccess(final ArrayList<JokeModel> jokes) {
        mMainThread.post(new Runnable() {
            @Override public void run() {
                mCallback.onFetchJokesSuccess(jokes);
            }
        });
    }

    private void notifyError(final Throwable throwable) {
        mMainThread.post(new Runnable() {
            @Override public void run() {
                mCallback.onFetchJokesError(throwable);
            }
        });
    }*/
}
