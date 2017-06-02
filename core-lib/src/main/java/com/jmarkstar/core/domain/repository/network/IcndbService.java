package com.jmarkstar.core.domain.repository.network;

import com.jmarkstar.core.domain.model.JokeModel;
import com.jmarkstar.core.domain.repository.network.response.JokeResponse;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jmarkstar on 31/05/2017.
 */
public interface IcndbService {

    @GET("jokes/random/{count}") Call<JokeResponse<ArrayList<JokeModel>>> getJokes(@Path("count") Integer count);

    @GET("jokes/random/{count}") Call<JokeResponse<ArrayList<JokeModel>>> getJokesWithCustomName(
            @Path("count") Integer count,
            @Query("firstName") String firstName,
            @Query("lastName") String lastName);

    @GET("jokes/{idJoke}") Call<JokeResponse<JokeModel>> getJoke(@Path("idJoke") Integer idJoke);
}
