package com.jmarkstar.core.domain.interactor;

import com.jmarkstar.core.domain.model.JokeModel;
import java.util.ArrayList;

/**
 * Created by jmarkstar on 2/06/2017.
 */
public interface JokeDispatcher {

    void fetchJokesInteractor(boolean refresh, int count, Interactor.Callback<ArrayList<JokeModel>> callback);
}
