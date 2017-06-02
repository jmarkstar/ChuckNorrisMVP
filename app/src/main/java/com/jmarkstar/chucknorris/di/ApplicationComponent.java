package com.jmarkstar.chucknorris.di;

import android.content.Context;
import com.jmarkstar.core.di.ApplicationModule;
import com.jmarkstar.core.domain.interactor.executor.Executor;
import com.jmarkstar.core.domain.interactor.executor.ExecutorModule;
import com.jmarkstar.core.domain.interactor.executor.MainThread;
import com.jmarkstar.core.domain.repository.database.DatabaseModule;
import com.jmarkstar.core.domain.repository.database.dao.JokeDao;
import com.jmarkstar.core.domain.repository.network.IcndbService;
import com.jmarkstar.core.domain.repository.network.NetworkModule;
import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by jmarkstar on 1/06/2017.
 */
@Singleton
@Component(modules = {ApplicationModule.class, DatabaseModule.class, NetworkModule.class, ExecutorModule.class})
public interface ApplicationComponent {

    Context getContext();
    Executor getExecutor();
    MainThread getMainThread();
    IcndbService getIcndbService();
    JokeDao getJokeDao();
}
