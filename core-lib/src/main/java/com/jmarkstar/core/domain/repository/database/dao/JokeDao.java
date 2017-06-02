package com.jmarkstar.core.domain.repository.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jmarkstar.core.R;
import com.jmarkstar.core.domain.interactor.InteractorCallBack;
import com.jmarkstar.core.domain.model.JokeModel;
import com.jmarkstar.core.exception.LocalDatabaseException;
import java.util.ArrayList;

/**
 * Created by jmarkstar on 31/05/2017.
 */
public class JokeDao {

    private String [] ALL_COLUMNS = {JokeModel.ID_FIELD, JokeModel.JOKE_FIELD};

    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    public JokeDao(Context context, SQLiteDatabase sqLiteDatabase){
        this.mContext = context;
        this.mSQLiteDatabase = sqLiteDatabase;
    }

    public long insertJoke(JokeModel newJoke){
        ContentValues contentValues = new ContentValues();
        contentValues.put(JokeModel.ID_FIELD, newJoke.getId());
        contentValues.put(JokeModel.JOKE_FIELD, newJoke.getJoke());
        return mSQLiteDatabase.insert(JokeModel.TABLE_NAME, null, contentValues);
    }

    public void getJokes(Integer count, InteractorCallBack<ArrayList<JokeModel>> interactorCallBack){
        try{
            ArrayList<JokeModel> jokes = new ArrayList<>();
            Cursor mCursor = mSQLiteDatabase.query(true, JokeModel.TABLE_NAME, ALL_COLUMNS, null, null, null, null, null, String.valueOf(count));
            while (mCursor.moveToNext()){
                JokeModel joke = new JokeModel();
                joke.setId(mCursor.getInt(mCursor.getColumnIndex(JokeModel.ID_FIELD)));
                joke.setJoke(mCursor.getString(mCursor.getColumnIndex(JokeModel.JOKE_FIELD)));
                jokes.add(joke);
            }
            mCursor.close();
            interactorCallBack.onSuccess(jokes);
        }catch (Exception ex){
            ex.printStackTrace();
            interactorCallBack.onError(new LocalDatabaseException(mContext.getString(R.string.exception_get_jokes)));
        }
    }

    public void closeDb(){
        mSQLiteDatabase.close();
    }
}
