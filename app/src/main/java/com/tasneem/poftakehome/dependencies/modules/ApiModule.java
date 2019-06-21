package com.tasneem.poftakehome.dependencies.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tasneem.poftakehome.BuildConfig;
import com.tasneem.poftakehome.repo.model.POFDate;
import com.tasneem.poftakehome.repo.network.ApiInterface;
import com.tasneem.poftakehome.repo.network.ApiManager;
import com.tasneem.poftakehome.repo.network.DateDeserializer;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Singleton
    @Provides
    public ApiManager providesApiManager(Retrofit retrofit) {
        return new ApiManager(retrofit.create(ApiInterface.class));
    }

    @Singleton
    @Provides
    public Retrofit providesReyrofit(OkHttpClient okHttpClient,
                                     GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    @Singleton
    public GsonConverterFactory providesGsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    public DateDeserializer providesImageDeserializer() {
        return new DateDeserializer();
    }

    @Provides
    @Singleton
     public Gson providesGson(DateDeserializer dateDeserializer) {
        return new GsonBuilder()
                .registerTypeAdapter(POFDate.class, dateDeserializer)
                .create();
    }

    @Provides
    @Singleton
     public OkHttpClient providesOkHttpClient(
            HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient().newBuilder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        return BuildConfig.DEBUG
                ? new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                : new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE);
    }
}
