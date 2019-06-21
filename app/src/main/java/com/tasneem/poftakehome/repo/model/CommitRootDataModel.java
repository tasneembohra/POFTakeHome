package com.tasneem.poftakehome.repo.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class CommitRootDataModel {

    @SerializedName("sha")
    public String sha;
    @SerializedName("node_id")
    public String nodeId;
    @NonNull
    @SerializedName("url")
    public String url;
    @NonNull
    @SerializedName("html_url")
    public String htmlUrl;
    @NonNull
    @SerializedName("comments_url")
    public String commentsUrl;
    @NonNull
    @SerializedName("commit")
    public CommitDataModel commit;
    @NonNull
    @SerializedName("author")
    public UserDataModel author;
    @NonNull
    @SerializedName("committer")
    public UserDataModel committer;
}
