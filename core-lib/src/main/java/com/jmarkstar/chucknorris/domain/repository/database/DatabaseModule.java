package com.jmarkstar.core.domain.repository.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.jmarkstar.core.domain.repository.database.dao.JokeDao;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jmarkstar on 1/06/2017.
 */
@Module
public class DatabaseModule {

    private Context mContext;

    public DatabaseModule(Context context){
        this.mContext = context;
    }

    @Singleton
    @Provides
    ChuckNorrisDataBaseHelper provideChuckNorrisDataBaseHelper(){
        return new ChuckNorrisDataBaseHelper(mContext);
    }

    @Singleton
    @Provides
    SQLiteDatabase provideDatabase(ChuckNorrisDataBaseHelper chuckNorrisDataBaseHelper){
        return chuckNorrisDataBaseHelper.getWritableDatabase();
    }

    @Singleton
    @Provides
    JokeDao provideJokeDao(SQLiteDatabase sqLiteDatabase){
        return new JokeDao(mContext, sqLiteDatabase);
    }

}
