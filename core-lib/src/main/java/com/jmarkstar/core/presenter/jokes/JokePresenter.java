package com.jmarkstar.core.presenter.jokes;

import com.jmarkstar.core.domain.interactor.Action;
import com.jmarkstar.core.domain.interactor.JokeDispatcher;
import com.jmarkstar.core.domain.model.JokeModel;
import com.jmarkstar.core.presenter.BaseContractor;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Created by jmarkstar on 1/06/2017.
 */
public class JokePresenter extends BaseContractor.BasePresenter<JokeContract.JokeView>
        implements JokeContract.JokePresenter {

    @Inject JokeDispatcher mJokeDispatcher;

    @Inject public JokePresenter(){}

    @Override public void onGetRandomJokes(Integer count) {
        mJokeDispatcher.fetchJokesInteractor(true, count, new Action.Callback<ArrayList<JokeModel>>() {

            @Override public void onSuccess(ArrayList<JokeModel> response) {
                mView.showJokes(response);
            }

            @Override public void onError(Throwable ex) {

            }
        });
    }

    @Override public void onGetRandomJokesWithCustomName(Integer count, String name, String lastName) {

    }

    @Override public void onGetSpecificJoke(Integer idJoke) {

    }
}
