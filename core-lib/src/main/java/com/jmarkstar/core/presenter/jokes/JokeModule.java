package com.jmarkstar.core.presenter.jokes;

import android.content.Context;

import com.jmarkstar.core.domain.interactor.GetJokesInteractor;
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
    GetJokesInteractor provideGetJokesInteractor(JokeDao jokeDao, Context mContext, IcndbService mIcndbService){
        return new GetJokesInteractor(jokeDao, mContext, mIcndbService);
    }

    @Provides
    JokeContract.JokePresenter providePresenter(JokePresenter presenter){
        presenter.attachView(view);
        return presenter;
    }
}
