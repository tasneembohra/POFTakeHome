package com.tasneem.poftakehome.repo.network;

import android.support.annotation.NonNull;

import com.tasneem.poftakehome.repo.model.ErrorModel;

/**
 * Common api listener for all the apis, it can have error, success or the data is empty.
 * @param <T>
 */
public interface ApiListener<T> {
    void onError(@NonNull ErrorModel error);
    void onSuccess(@NonNull T data);
    void onEmptyData();
}
