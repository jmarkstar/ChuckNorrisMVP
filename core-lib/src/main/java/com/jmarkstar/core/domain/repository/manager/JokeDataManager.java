package com.jmarkstar.core.domain.repository.manager;

import com.jmarkstar.core.domain.interactor.Interactor;
import com.jmarkstar.core.domain.model.JokeModel;
import java.util.ArrayList;

/**
 * Created by jmarkstar on 2/06/2017.
 */
public interface JokeDataManager {

    void getJokes(final boolean refresh, final int count, final Interactor.Callback<ArrayList<JokeModel>> callback);
    void getJokesWithCustomName(Integer count, String firstName, String lastName, Interactor.Callback callback);
    void getJoke(Integer idJoke, Interactor.Callback<JokeModel> callback);
}
