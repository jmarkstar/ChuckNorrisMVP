package com.jmarkstar.chucknorris;

import android.app.Application;
import android.content.Context;

/**
 * Created by jmarkstar on 31/05/2017.
 */
public class ChuckNorrisApplication extends Application {

    private static Context context;

    @Override public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
