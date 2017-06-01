package com.jmarkstar.chucknorris.domain.interactor;

import android.content.Context;

import com.jmarkstar.chucknorris.ChuckNorrisApplication;
import com.jmarkstar.chucknorris.R;
import com.jmarkstar.chucknorris.base.InteractorCallBack;
import com.jmarkstar.chucknorris.domain.repository.database.dao.JokeDao;
import com.jmarkstar.chucknorris.domain.repository.network.IcndbServiceFactory;
import com.jmarkstar.chucknorris.domain.repository.network.RemoteCallback;
import com.jmarkstar.chucknorris.domain.repository.network.response.JokeResponse;
import com.jmarkstar.chucknorris.exception.ApiException;
import com.jmarkstar.chucknorris.util.Constants;
import retrofit2.Call;

/**
 * Created by jmarkstar on 31/05/2017.
 */
public class GetJokesInteractor {

    private static final String TAG = "GetJokesInteractor";

    private Context mContext;

    public GetJokesInteractor(){
        this.mContext = ChuckNorrisApplication.getContext();
    }

    public void getJokes(boolean refresh, Integer count, final InteractorCallBack interactorCallBack ){
        if(refresh){
            getJokesFromServer(count, interactorCallBack);
        }else{
            JokeDao jokeDao = new JokeDao();
            jokeDao.getJokes(count, interactorCallBack);
        }
    }

    private void getJokesFromServer(Integer count, final InteractorCallBack interactorCallBack){
        Call<JokeResponse> responseCall = IcndbServiceFactory.makeIcndbService().getJokes(count);
        responseCall.enqueue(new RemoteCallback<JokeResponse>() {
            @Override public void onSuccess(JokeResponse response) {
                if(response.getType().equals(Constants.API_SUCCESS)){
                    interactorCallBack.onSuccess(response.getValue());
                }else{
                    interactorCallBack.onError(new ApiException(mContext.getString(R.string.exception_get_jokes)));
                }
            }

            @Override public void onFailed(Throwable throwable) {
                interactorCallBack.onError(throwable);
            }
        });
    }
}
