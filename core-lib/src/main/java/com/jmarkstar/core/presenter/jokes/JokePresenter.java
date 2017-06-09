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
        mView.showProgress();
        mJokeDispatcher.fetchJokesInteractor(true, count, new Action.Callback<ArrayList<JokeModel>>() {

            @Override public void onSuccess(ArrayList<JokeModel> response) {
                mView.hideProgress();
                mView.showJokes(response);
            }

            @Override public void onError(Throwable ex) {
                errorHandler(ex);
            }
        });
    }

    @Override public void onGetRandomJokesWithCustomName(Integer count, String name, String lastName) {
        mView.showProgress();
        mJokeDispatcher.fetchJokesInteractor(count, name, lastName, new Action.Callback<ArrayList<JokeModel>>() {
            @Override public void onSuccess(ArrayList<JokeModel> response) {
                mView.hideProgress();
                mView.showJokes(response);
            }

            @Override public void onError(Throwable ex) {
                errorHandler(ex);
            }
        });
    }

    @Override public void onGetSpecificJoke(Integer idJoke) {

    }

    @Override public void onGetNumberOfJokes() {
        mView.showProgress();
        mJokeDispatcher.fetchNumberOfJokes(new Action.Callback<Integer>() {
            @Override public void onSuccess(Integer response) {
                mView.getNumberOfJokes(response);
            }

            @Override public void onError(Throwable ex) {
                errorHandler(ex);
            }
        });
    }
}
