package com.tasneem.poftakehome.repo.network;

import android.support.annotation.NonNull;

import com.tasneem.poftakehome.repo.model.ErrorModel;

public interface ApiListener<T> {

    void onError(@NonNull ErrorModel error);
    void onSuccess(@NonNull T data);
    void onEmptyData();
}
