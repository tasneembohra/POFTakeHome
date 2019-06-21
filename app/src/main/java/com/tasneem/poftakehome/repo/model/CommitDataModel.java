package com.tasneem.poftakehome.repo.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class CommitDataModel {
    @NonNull
    @SerializedName("author")
    public CommitterDataModel author;
    @NonNull
    @SerializedName("committer")
    public CommitterDataModel committer;
    @NonNull
    @SerializedName("message")
    public String message;
    @SerializedName("verification")
    public VerificationDataModel verification;
}
