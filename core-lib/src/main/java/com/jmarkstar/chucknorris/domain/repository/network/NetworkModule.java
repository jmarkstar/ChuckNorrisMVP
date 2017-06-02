package com.jmarkstar.core.domain.repository.network;

import android.app.Application;
import com.jmarkstar.core.BuildConfig;
import com.jmarkstar.core.util.Constants;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by jmarkstar on 1/06/2017.
 */
@Module
public class NetworkModule {

    private String mBaseUrl;

    public NetworkModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    @Singleton
    IcndbService provideIcndbService(Retrofit retrofit){
        return retrofit.create(IcndbService.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(MoshiConverterFactory moshiConverterFactory, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(mBaseUrl)
            .client(okHttpClient)
            .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache, HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.connectTimeout(Constants.HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(Constants.HTTP_READ_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(httpLoggingInterceptor);
        httpClientBuilder.cache(cache);
        return httpClientBuilder.build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        return logging;
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    MoshiConverterFactory provideMoshiConverterFactory() {
        return MoshiConverterFactory.create();
    }
}
