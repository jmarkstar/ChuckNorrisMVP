package com.jmarkstar.chucknorris;

import android.app.Application;

import com.jmarkstar.chucknorris.di.ApplicationComponent;
import com.jmarkstar.chucknorris.di.DaggerApplicationComponent;
import com.jmarkstar.core.di.ApplicationModule;
import com.jmarkstar.core.domain.repository.database.DatabaseModule;
import com.jmarkstar.core.domain.repository.network.NetworkModule;
import com.jmarkstar.core.util.Constants;

/**
 * Created by jmarkstar on 31/05/2017.
 */
public class ChuckNorrisApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();

         applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule( new ApplicationModule(this))
                .databaseModule(new DatabaseModule())
                .networkModule(new NetworkModule(Constants.BASE_URL))
                .build();
    }


    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
