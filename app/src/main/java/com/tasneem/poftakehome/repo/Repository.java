package com.tasneem.poftakehome.repo;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.tasneem.poftakehome.dependencies.component.DaggerRepositoryComponent;
import com.tasneem.poftakehome.dependencies.component.RepositoryComponent;
import com.tasneem.poftakehome.dependencies.modules.ApiModule;
import com.tasneem.poftakehome.repo.model.CommitRootDataModel;
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

    public MutableLiveData<Resource<ArrayList<CommitRootDataModel>>> commitLiveData = new MutableLiveData<>();

    public void fetchCommits() {
        if (commitLiveData.getValue() != null && commitLiveData.getValue().data != null && commitLiveData.getValue().data.size() > 0) return;

        api.getCommits(new ApiListener<ArrayList<CommitRootDataModel>>() {
            @Override
            public void onError(@NonNull ErrorModel error) {
                commitLiveData.setValue(new Resource<ArrayList<CommitRootDataModel>>(error));
            }

            @Override
            public void onSuccess(@NonNull ArrayList<CommitRootDataModel> data) {
                commitLiveData.setValue(new Resource<>(data));
            }

            @Override
            public void onEmptyData() {
                commitLiveData.setValue(new Resource<ArrayList<CommitRootDataModel>>());
            }
        });
    }
}
