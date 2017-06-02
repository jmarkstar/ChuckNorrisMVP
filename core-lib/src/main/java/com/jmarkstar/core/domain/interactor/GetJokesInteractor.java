package com.jmarkstar.core.domain.interactor;

import android.content.Context;
import com.jmarkstar.core.R;
import com.jmarkstar.core.domain.model.JokeModel;
import com.jmarkstar.core.domain.repository.database.dao.JokeDao;
import com.jmarkstar.core.domain.repository.network.IcndbService;
import com.jmarkstar.core.domain.repository.network.RemoteCallback;
import com.jmarkstar.core.domain.repository.network.response.JokeResponse;
import com.jmarkstar.core.exception.ApiException;
import com.jmarkstar.core.util.Constants;
import java.util.ArrayList;
import javax.inject.Inject;
import retrofit2.Call;

/**
 * Created by jmarkstar on 31/05/2017.
 */
public class GetJokesInteractor {

    private static final String TAG = "GetJokesInteractor";

    private JokeDao jokeDao;
    private Context mContext;
    private IcndbService mIcndbService;

     @Inject public GetJokesInteractor(JokeDao jokeDao, Context mContext, IcndbService mIcndbService) {
        this.jokeDao = jokeDao;
        this.mContext = mContext;
        this.mIcndbService = mIcndbService;
    }

    public void getJokes(boolean refresh, Integer count, final InteractorCallBack<ArrayList<JokeModel>> interactorCallBack ){
        if(refresh){
            getJokesFromServer(count, interactorCallBack);
        }else{
            jokeDao.getJokes(count, interactorCallBack);
        }
    }

    private void getJokesFromServer(Integer count, final InteractorCallBack<ArrayList<JokeModel>> interactorCallBack){
        Call<JokeResponse<ArrayList<JokeModel>>> responseCall = mIcndbService.getJokes(count);
        responseCall.enqueue(new RemoteCallback<JokeResponse<ArrayList<JokeModel>>>() {
            @Override
            public void onSuccess(JokeResponse<ArrayList<JokeModel>> response) {
                if(response.getType().equals(Constants.API_SUCCESS)){
                    interactorCallBack.onSuccess(response.getValue());
                }else{
                    interactorCallBack.onError(new ApiException(mContext.getString(R.string.exception_get_jokes)));
                }
            }

            @Override
            public void onFailed(Throwable throwable) {
                interactorCallBack.onError(new ApiException(mContext.getString(R.string.exception_get_jokes)));
            }
        });
    }
}
