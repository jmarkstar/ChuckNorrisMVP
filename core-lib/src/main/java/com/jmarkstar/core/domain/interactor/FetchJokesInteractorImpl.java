package com.jmarkstar.core.domain.interactor;

import android.content.Context;
import android.util.Log;
import com.jmarkstar.core.R;
import com.jmarkstar.core.domain.interactor.executor.Executor;
import com.jmarkstar.core.domain.interactor.executor.MainThread;
import com.jmarkstar.core.domain.model.JokeModel;
import com.jmarkstar.core.domain.repository.database.dao.JokeDao;
import com.jmarkstar.core.domain.repository.network.IcndbService;
import com.jmarkstar.core.domain.repository.network.RemoteCallback;
import com.jmarkstar.core.domain.repository.network.response.JokeResponse;
import com.jmarkstar.core.exception.ApiException;
import com.jmarkstar.core.exception.LocalDatabaseException;
import com.jmarkstar.core.util.Constants;
import java.util.ArrayList;
import javax.inject.Inject;
import retrofit2.Call;

/**
 * Created by jmarkstar on 2/06/2017.
 */
public class FetchJokesInteractorImpl implements Interactor, FetchJokesInteractor {

    private static final String TAG = "FetchJokesInt";

    @Inject Context mContext;
    @Inject Executor mExecutor;
    @Inject MainThread mMainThread;
    @Inject IcndbService mIcndbService;
    @Inject JokeDao mJokeDao;

    private Callback mCallback;
    private int mCount;
    private boolean mRefresh;

    @Inject public FetchJokesInteractorImpl() {}

    @Override public void execute(final boolean refresh, final int count, final Callback callback) {
        if (null == callback) {
            throw new IllegalArgumentException("Callback can't be null");
        }
        this.mCallback = callback;
        this.mCount = count;
        this.mRefresh = refresh;
        this.mExecutor.run(this);
    }

    @Override public void run() {
        if(mRefresh){
            Log.v(TAG, "It´s from the server");
            getJokesFromServer();
        }else{
            try{
                Log.v(TAG, "It´s from the database");
                notifyFetchAlbumPhotosSuccess(mJokeDao.getJokes(mCount));
            }catch (Exception ex){
                ex.printStackTrace();
                notifyError(new LocalDatabaseException(mContext.getString(R.string.exception_get_jokes)));
            }
        }
    }

    private void getJokesFromServer(){
        Call<JokeResponse<ArrayList<JokeModel>>> responseCall = mIcndbService.getJokes(mCount);
        responseCall.enqueue(new RemoteCallback<JokeResponse<ArrayList<JokeModel>>>() {
            @Override public void onSuccess(JokeResponse<ArrayList<JokeModel>> response) {
                if(response.getType().equals(Constants.API_SUCCESS)){
                    notifyFetchAlbumPhotosSuccess(response.getValue());
                }else{
                    notifyError(new ApiException(mContext.getString(R.string.exception_get_jokes)));
                }
            }

            @Override public void onFailed(Throwable throwable) {
                notifyError(throwable);
            }
        });
    }

    private void notifyFetchAlbumPhotosSuccess(final ArrayList<JokeModel> jokes) {
        mMainThread.post(new Runnable() {
            @Override public void run() {
                mCallback.onFetchJokesSuccess(jokes);
            }
        });
    }

    private void notifyError(final Throwable throwable) {
        mMainThread.post(new Runnable() {
            @Override public void run() {
                mCallback.onFetchJokesError(throwable);
            }
        });
    }
}
