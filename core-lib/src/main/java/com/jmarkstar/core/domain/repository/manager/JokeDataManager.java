package com.jmarkstar.core.domain.repository.manager;

import com.jmarkstar.core.domain.interactor.JokeDispatcher;
import com.jmarkstar.core.domain.model.JokeModel;
import java.util.ArrayList;

/**
 * Created by jmarkstar on 2/06/2017.
 */
public interface JokeDataManager {

    void getJokes(final boolean refresh, final int count, final JokeDispatcher.Callback<ArrayList<JokeModel>> callback);
    void getJokesWithCustomName(Integer count, String firstName, String lastName, JokeDispatcher.Callback callback);
    void getJoke(Integer idJoke, JokeDispatcher.Callback<JokeModel> callback);
}
