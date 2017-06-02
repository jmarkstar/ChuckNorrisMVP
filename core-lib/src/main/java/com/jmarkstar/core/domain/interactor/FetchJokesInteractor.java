package com.jmarkstar.core.domain.interactor;

import com.jmarkstar.core.domain.model.JokeModel;
import java.util.ArrayList;

/**
 * Created by jmarkstar on 2/06/2017.
 */

public interface FetchJokesInteractor {
    interface Callback {
        void onFetchJokesSuccess(ArrayList<JokeModel> jokes);
        void onFetchJokesError(Throwable ex);
    }

    void execute(boolean refresh, int count, Callback callback);
}
