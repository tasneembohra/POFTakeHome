package com.tasneem.poftakehome.repo.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class CommitterDataModel {
    @NonNull
    @SerializedName("name")
    public String name;
    @NonNull
    @SerializedName("email")
    public String email;
    @NonNull
    @SerializedName("date")
    public POFDate date;
}
