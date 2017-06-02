package com.jmarkstar.chucknorris.ui.joke;

import com.jmarkstar.chucknorris.di.ActivityScope;
import com.jmarkstar.chucknorris.di.ApplicationComponent;
import com.jmarkstar.core.presenter.jokes.JokeModule;

import dagger.Component;

/**
 * Created by jmarkstar on 1/06/2017.
 */
@ActivityScope
@Component(modules = {JokeModule.class}, dependencies = {ApplicationComponent.class})
public interface JokeComponent {
    void inject(JokesActivity activity);
}
