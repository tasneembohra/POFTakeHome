package com.tasneem.poftakehome.repo;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.tasneem.poftakehome.dependencies.component.DaggerRepositoryComponent;
import com.tasneem.poftakehome.dependencies.component.RepositoryComponent;
import com.tasneem.poftakehome.dependencies.modules.ApiModule;
import com.tasneem.poftakehome.repo.model.CommitDataModel;
import com.tasneem.poftakehome.repo.model.ErrorModel;
import com.tasneem.poftakehome.repo.model.Resource;
import com.tasneem.poftakehome.repo.network.ApiListener;
import com.tasneem.poftakehome.repo.network.ApiManager;

import java.util.ArrayList;

import javax.inject.Inject;

public class Repository {

    @Inject
    public ApiManager api;

    public Repository() {
       RepositoryComponent repositoryComponent = DaggerRepositoryComponent.builder()
                .apiModule(new ApiModule())
                .build();
        repositoryComponent.inject(this);
    }

    public MutableLiveData<Resource<ArrayList<CommitDataModel>>> commitLiveData = new MutableLiveData<>();

    public void fetchCommits() {
        api.getCommits(new ApiListener<ArrayList<CommitDataModel>>() {
            @Override
            public void onError(@NonNull ErrorModel error) {
                commitLiveData.setValue(new Resource<ArrayList<CommitDataModel>>(error));
            }

            @Override
            public void onSuccess(@NonNull ArrayList<CommitDataModel> data) {
                commitLiveData.setValue(new Resource<>(data));
            }

            @Override
            public void onEmptyData() {
                commitLiveData.setValue(new Resource<ArrayList<CommitDataModel>>());
            }
        });
    }
}
