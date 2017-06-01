package com.jmarkstar.chucknorris.domain.repository.network;

import com.jmarkstar.chucknorris.domain.repository.network.response.JokeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jmarkstar on 31/05/2017.
 */
public interface IcndbService {

    @GET("jokes/random/{count}") Call<JokeResponse> getJokes(@Path("count") Integer count);
}
