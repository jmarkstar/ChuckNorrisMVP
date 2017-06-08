package com.jmarkstar.core.domain.repository.manager;

import com.jmarkstar.core.domain.interactor.Action;
import com.jmarkstar.core.domain.model.JokeModel;
import java.util.ArrayList;

/**
 * Created by jmarkstar on 2/06/2017.
 */
public interface JokeDataManager {

    void getJokes(final boolean refresh, final int count, final Action.Callback<ArrayList<JokeModel>> callback);
    void fetchNumberOfJokes(final Action.Callback<Integer> callback);
    void getJokesWithCustomName(Integer count, String firstName, String lastName, Action.Callback callback);
    void getJoke(Integer idJoke, Action.Callback<JokeModel> callback);
}
