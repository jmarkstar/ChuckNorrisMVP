package com.jmarkstar.core.presenter.jokes;

import com.jmarkstar.core.domain.interactor.FetchJokesInteractor;
import com.jmarkstar.core.domain.model.JokeModel;
import com.jmarkstar.core.presenter.BaseContractor;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Created by jmarkstar on 1/06/2017.
 */
public class JokePresenter extends BaseContractor.BasePresenter<JokeContract.JokeView>
        implements JokeContract.JokePresenter {

    @Inject FetchJokesInteractor fetchJokesInteractor;

    @Inject public JokePresenter(){}

    @Override public void onGetRandomJokes(Integer count) {
        fetchJokesInteractor.execute(true, 10, new FetchJokesInteractor.Callback() {
            @Override public void onFetchJokesSuccess(ArrayList<JokeModel> jokes) {
                mView.showJokes(jokes);
            }

            @Override public void onFetchJokesError(Throwable ex) {

            }
        });
    }

    @Override public void onGetRandomJokesWithCustomName(Integer count, String name, String lastName) {

    }

    @Override public void onGetSpecificJoke(Integer idJoke) {

    }
}
