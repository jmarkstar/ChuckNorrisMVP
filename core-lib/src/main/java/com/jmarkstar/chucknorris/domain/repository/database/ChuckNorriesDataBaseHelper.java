package com.jmarkstar.chucknorris.domain.repository.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jmarkstar.chucknorris.domain.model.JokeModel;

/**
 * Created by jmarkstar on 31/05/2017.
 */
public class ChuckNorriesDataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "chucknorriesjokes.db";
    private static final Integer DATABASE_VERSION = 1;

    private StringBuilder SQL_CREATE_TABLE_JOKE = new StringBuilder()
            .append("CREATE TABLE IF NOT EXISTS "+ JokeModel.TABLE_NAME+" ( ")
            .append(JokeModel.ID_FIELD+" INTEGER PRIMARY KEY AUTOINCREMENT, ")
            .append(JokeModel.JOKE_FIELD+" TEXT )");

    private StringBuilder SQL_DROP_TABLE_JOKE = new StringBuilder()
            .append("DROP TABLE IF EXISTS "+JokeModel.TABLE_NAME);

    public ChuckNorriesDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_JOKE.toString());
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TABLE_JOKE.toString());
        onCreate(db);
    }
}
