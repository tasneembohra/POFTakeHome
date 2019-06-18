package com.tasneem.poftakehome.repo.network;

import android.support.annotation.NonNull;

import com.tasneem.poftakehome.repo.model.ErrorModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

abstract public class POFCallback<T> implements Callback<T> {

    abstract void onFailure(@NonNull ErrorModel errorModel);
    abstract void onResponse(T data);

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.isSuccessful()) {
            onResponse(response.body());
        } else {
            onFailure(new ErrorModel(ErrorModel.STATE.SERVER));
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        onFailure(new ErrorModel(ErrorModel.STATE.NETWORK));
    }
}
