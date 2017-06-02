package com.jmarkstar.core.di;

import android.app.Application;
import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jmarkstar on 1/06/2017.
 */
@Module
public class ApplicationModule {

    private Context mContext;

    public ApplicationModule(Application application){
        this.mContext = application.getApplicationContext();
    }

    @Provides
    @Singleton
    Context provideContext(){
        return mContext;
    }

}
