package com.tasneem.poftakehome.repo.model;

import com.google.gson.annotations.SerializedName;

public class UserDataModel {
    @SerializedName("login")
    public String userName;
    @SerializedName("id")
    public Long id;
    @SerializedName("avatar_url")
    public String avatarUrl;

}
