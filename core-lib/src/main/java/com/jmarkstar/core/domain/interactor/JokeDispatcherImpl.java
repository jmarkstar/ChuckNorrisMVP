package com.jmarkstar.core.domain.interactor;

import com.jmarkstar.core.domain.interactor.executor.Executor;
import com.jmarkstar.core.domain.model.JokeModel;
import com.jmarkstar.core.domain.repository.manager.JokeDataManager;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Created by jmarkstar on 2/06/2017.
 */
public class JokeDispatcherImpl implements JokeDispatcher {

    @Inject Executor mExecutor;
    @Inject JokeDataManager mJokeDataManager;

    @Inject public JokeDispatcherImpl() {}

    @Override public void fetchJokesInteractor(final boolean refresh, final int count, final Interactor.Callback<ArrayList<JokeModel>> callback) {
        if (null == callback) {
            throw new IllegalArgumentException("Callback can't be null");
        }
        this.mExecutor.execute(new Interactor() {
            @Override public void run() {
                mJokeDataManager.getJokes(refresh, count, callback);
            }
        });
    }
}
