package com.jmarkstar.core.domain.repository.manager;

import android.util.Log;
import com.jmarkstar.core.R;
import com.jmarkstar.core.domain.interactor.Interactor;
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
public class JokeDataManagerImpl implements JokeDataManager {

    private static final String TAG = "JokeDataManager";

    @Inject MainThread mMainThread;
    @Inject IcndbService mIcndbService;
    @Inject JokeDao mJokeDao;

    @Inject public JokeDataManagerImpl() {}

    @Override public void getJokes(final boolean refresh, final int count, final Interactor.Callback<ArrayList<JokeModel>> callback) {
        if(refresh){
            Log.v(TAG, "It´s from the server");
            Call<JokeResponse<ArrayList<JokeModel>>> responseCall = mIcndbService.getJokes(count);
            responseCall.enqueue(new RemoteCallback<JokeResponse<ArrayList<JokeModel>>>() {
                @Override public void onSuccess(JokeResponse<ArrayList<JokeModel>> response) {
                    if(response.getType().equals(Constants.API_SUCCESS)){
                        notifySuccess(response.getValue(), callback);
                    }else{
                        notifyError(new ApiException(R.string.exception_get_jokes), callback);
                    }
                }

                @Override public void onFailed(Throwable throwable) {
                    notifyError(throwable, callback);
                }
            });
        }else{
            try{
                Log.v(TAG, "It´s from the database");
                notifySuccess(mJokeDao.getJokes(count), callback);
            }catch (Exception ex){
                ex.printStackTrace();
                notifyError(new LocalDatabaseException(R.string.exception_get_jokes), callback);
            }
        }
    }

    @Override public void getJokesWithCustomName(Integer count, String firstName,
                                                 String lastName, Interactor.Callback callback) {

    }

    @Override public void getJoke(Integer idJoke, final Interactor.Callback<JokeModel> callback) {
        Call<JokeResponse<JokeModel>> responseCall = mIcndbService.getJoke(idJoke);
        responseCall.enqueue(new RemoteCallback<JokeResponse<JokeModel>>() {
            @Override public void onSuccess(JokeResponse<JokeModel> response) {
                if(response.getType().equals(Constants.API_SUCCESS)){
                    callback.onSuccess(response.getValue());
                }else{
                    callback.onError(new ApiException(R.string.exception_get_jokes));
                }
            }

            @Override public void onFailed(Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

    private void notifySuccess(final Object response, final Interactor.Callback callback) {
        mMainThread.post(new Runnable() {
            @Override public void run() {
                callback.onSuccess(response);
            }
        });
    }

    private void notifyError(final Throwable throwable, final Interactor.Callback callback) {
        mMainThread.post(new Runnable() {
            @Override public void run() {
                callback.onError(throwable);
            }
        });
    }

}
