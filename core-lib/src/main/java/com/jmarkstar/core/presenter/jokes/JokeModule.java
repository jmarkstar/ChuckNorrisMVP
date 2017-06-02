package com.jmarkstar.core.presenter.jokes;

import com.jmarkstar.core.domain.interactor.JokeDispatcher;
import com.jmarkstar.core.domain.interactor.JokeDispatcherImpl;
import com.jmarkstar.core.domain.repository.manager.JokeDataManager;
import com.jmarkstar.core.domain.repository.manager.JokeDataManagerImpl;
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
    JokeDataManager provideJokeDataManager(JokeDataManagerImpl jokeDataManager){
        return jokeDataManager;
    }

    @Provides
    JokeDispatcher provideJokeDispatcher(JokeDispatcherImpl fetchJokesInteractor){
        return fetchJokesInteractor;
    }

    @Provides
    JokeContract.JokePresenter providePresenter(JokePresenter presenter){
        presenter.attachView(view);
        return presenter;
    }
}
