package com.jmarkstar.chucknorris.domain.repository.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jmarkstar.chucknorris.ChuckNorrisApplication;
import com.jmarkstar.chucknorris.R;
import com.jmarkstar.chucknorris.base.InteractorCallBack;
import com.jmarkstar.chucknorris.domain.model.JokeModel;
import com.jmarkstar.chucknorris.domain.repository.database.ChuckNorriesDataBaseHelper;
import com.jmarkstar.chucknorris.exception.LocalDatabaseException;
import java.util.ArrayList;

/**
 * Created by jmarkstar on 31/05/2017.
 */
public class JokeDao {

    private String [] ALL_COLUMNS = {JokeModel.ID_FIELD, JokeModel.JOKE_FIELD};

    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    public JokeDao(){
        this.mContext = ChuckNorrisApplication.getContext();
        this.mSQLiteDatabase = new ChuckNorriesDataBaseHelper(mContext).getWritableDatabase();
    }

    public long insertJoke(JokeModel newJoke){
        ContentValues contentValues = new ContentValues();
        contentValues.put(JokeModel.ID_FIELD, newJoke.getId());
        contentValues.put(JokeModel.JOKE_FIELD, newJoke.getJoke());
        return mSQLiteDatabase.insert(JokeModel.TABLE_NAME, null, contentValues);
    }

    public void getJokes(Integer count, InteractorCallBack interactorCallBack){
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
