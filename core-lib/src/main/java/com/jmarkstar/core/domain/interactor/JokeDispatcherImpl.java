package com.jmarkstar.core.domain.interactor;

import com.jmarkstar.core.R;
import com.jmarkstar.core.domain.interactor.executor.Executor;
import com.jmarkstar.core.domain.model.JokeModel;
import com.jmarkstar.core.domain.repository.manager.JokeDataManager;
import com.jmarkstar.core.exception.CallbackNullPointerException;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Created by jmarkstar on 2/06/2017.
 */
public class JokeDispatcherImpl implements JokeDispatcher {

    @Inject Executor mExecutor;
    @Inject JokeDataManager mJokeDataManager;

    @Inject public JokeDispatcherImpl() {}

    @Override public void fetchJokesInteractor(final boolean refresh, final int count, final Action.Callback<ArrayList<JokeModel>> callback) {
        if (null == callback) {
            throw new CallbackNullPointerException(R.string.exception_callback_null);
        }
        this.mExecutor.execute(new Action() {
            @Override public void run() {
                mJokeDataManager.getJokes(refresh, count, callback);
            }
        });
    }

    @Override public void fetchNumberOfJokes(final Action.Callback<Integer> callback) {
        if (null == callback) {
            throw new CallbackNullPointerException(R.string.exception_callback_null);
        }
        this.mExecutor.execute(new Action() {
            @Override public void run() {
                mJokeDataManager.fetchNumberOfJokes(callback);
            }
        });
    }
}
