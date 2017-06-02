package com.jmarkstar.core.presenter.jokes;

import android.content.Context;

import com.jmarkstar.core.domain.interactor.FetchJokesInteractor;
import com.jmarkstar.core.domain.interactor.FetchJokesInteractorImpl;
import com.jmarkstar.core.domain.repository.database.dao.JokeDao;
import com.jmarkstar.core.domain.repository.network.IcndbService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jmarkstar on 1/06/2017.
 */
@Module
public class JokeModule {

    private JokeContract.JokeView view;

    public JokeModule(JokeContract.JokeView view){
        this.view = view;
    }

    @Provides
    JokeContract.JokeView provideView(){
        return view;
    }

    @Provides
    FetchJokesInteractor provideFetchJokesInteractor(FetchJokesInteractorImpl fetchJokesInteractor){
        return fetchJokesInteractor;
    }

    @Provides
    JokeContract.JokePresenter providePresenter(JokePresenter presenter){
        presenter.attachView(view);
        return presenter;
    }
}
