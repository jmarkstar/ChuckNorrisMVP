package com.jmarkstar.core.domain.interactor;

import com.jmarkstar.core.domain.model.JokeModel;
import java.util.ArrayList;

/**
 * Created by jmarkstar on 2/06/2017.
 */
public interface JokeDispatcher {
    interface Callback<R> {
        void onJokeActionSuccess(R response);
        void onJokeActionError(Throwable ex);
    }

    void fetchJokesInteractor(boolean refresh, int count, Callback<ArrayList<JokeModel>> callback);
}
