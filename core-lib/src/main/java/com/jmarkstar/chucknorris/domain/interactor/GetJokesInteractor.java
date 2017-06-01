package com.jmarkstar.chucknorris.domain.interactor;

import com.jmarkstar.chucknorris.base.InteractorCallBack;
import com.jmarkstar.chucknorris.domain.repository.network.IcndbServiceFactory;
import com.jmarkstar.chucknorris.domain.repository.network.response.JokeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jmarkstar on 31/05/2017.
 */

public class GetJokesInteractor {

    private static final String TAG = "GetJokesInteractor";

    public void getJokes(Integer count, final InteractorCallBack interactorCallBack ){
        Call<JokeResponse> responseCall = IcndbServiceFactory.makeIcndbService().getJokes(count);
        responseCall.enqueue(new Callback<JokeResponse>() {
            @Override public void onResponse(Call<JokeResponse> call, Response<JokeResponse> response) {
                JokeResponse jokeResponse = response.body();
                if(jokeResponse.getType().equals("success")){
                    interactorCallBack.onSuccess(jokeResponse);
                }else{
                    interactorCallBack.onError("Internal Error");
                }
            }

            @Override public void onFailure(Call<JokeResponse> call, Throwable t) {

            }
        });
    }
}
