package com.jmarkstar.core.domain.repository.manager;

import android.util.Log;
import com.jmarkstar.core.R;
import com.jmarkstar.core.domain.interactor.JokeDispatcher;
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

    @Inject IcndbService mIcndbService;
    @Inject JokeDao mJokeDao;

    @Inject public JokeDataManagerImpl() {}

    @Override public void getJokes(final boolean refresh, final int count, final JokeDispatcher.Callback<ArrayList<JokeModel>> callback) {
        if(refresh){
            Log.v(TAG, "It´s from the server");
            Call<JokeResponse<ArrayList<JokeModel>>> responseCall = mIcndbService.getJokes(count);
            responseCall.enqueue(new RemoteCallback<JokeResponse<ArrayList<JokeModel>>>() {
                @Override public void onSuccess(JokeResponse<ArrayList<JokeModel>> response) {
                    if(response.getType().equals(Constants.API_SUCCESS)){
                        callback.onJokeActionSuccess(response.getValue());
                    }else{
                        callback.onJokeActionError(new ApiException(R.string.exception_get_jokes));
                    }
                }

                @Override public void onFailed(Throwable throwable) {
                    callback.onJokeActionError(throwable);
                }
            });
        }else{
            try{
                Log.v(TAG, "It´s from the database");
                callback.onJokeActionSuccess(mJokeDao.getJokes(count));
            }catch (Exception ex){
                ex.printStackTrace();
                callback.onJokeActionError(new LocalDatabaseException(R.string.exception_get_jokes));
            }
        }
    }

    @Override public void getJokesWithCustomName(Integer count, String firstName,
                                                 String lastName, JokeDispatcher.Callback callback) {

    }

    @Override public void getJoke(Integer idJoke, final JokeDispatcher.Callback<JokeModel> callback) {
        Call<JokeResponse<JokeModel>> responseCall = mIcndbService.getJoke(idJoke);
        responseCall.enqueue(new RemoteCallback<JokeResponse<JokeModel>>() {
            @Override public void onSuccess(JokeResponse<JokeModel> response) {
                if(response.getType().equals(Constants.API_SUCCESS)){
                    callback.onJokeActionSuccess(response.getValue());
                }else{
                    callback.onJokeActionError(new ApiException(R.string.exception_get_jokes));
                }
            }

            @Override public void onFailed(Throwable throwable) {
                callback.onJokeActionError(throwable);
            }
        });
    }

}
