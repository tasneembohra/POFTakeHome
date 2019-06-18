package com.tasneem.poftakehome.repo.network;

import android.support.annotation.NonNull;

import com.tasneem.poftakehome.repo.model.CommitDataModel;
import com.tasneem.poftakehome.repo.model.ErrorModel;

import java.util.ArrayList;

public class ApiManager {
    private ApiInterface api;

    public ApiManager(ApiInterface api) {
        this.api = api;
    }

    public void getCommits(final ApiListener<ArrayList<CommitDataModel>> callback) {
        api.getCommits().enqueue(new POFCallback<ArrayList<CommitDataModel>>() {
            @Override
            public void onFailure(@NonNull ErrorModel error) {
                callback.onError(error);
            }

            @Override
            public void onResponse(ArrayList<CommitDataModel> data) {
                if (data == null || data.size() == 0) {
                    callback.onEmptyData();
                } else {
                    callback.onSuccess(data);
                }

            }
        });
    }
}
