package com.tasneem.poftakehome.repo.network;

import com.tasneem.poftakehome.repo.model.CommitDataModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("definitelytyped/definitelytyped/commits")
    public Call<ArrayList<CommitDataModel>> getCommits();
}
