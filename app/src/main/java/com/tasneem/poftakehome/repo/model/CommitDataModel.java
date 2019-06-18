package com.tasneem.poftakehome.repo.model;

import com.google.gson.annotations.SerializedName;

public class CommitDataModel {

    @SerializedName("sha")
    public String sha;
    @SerializedName("node_id")
    public String nodeId;
    @SerializedName("url")
    public String url;
    @SerializedName("html_url")
    public String htmlUrl;
    @SerializedName("comments_url")
    public String commentsUrl;
    @SerializedName("message")
    public String message;
    @SerializedName("verification")
    public VerificationDataModel verification;
    @SerializedName("commit")
    public CommitDataModel commit;
    @SerializedName("author")
    public UserDataModel author;
    @SerializedName("committer")
    public UserDataModel committer;
}
