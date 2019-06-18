package com.tasneem.poftakehome.repo.model;

import com.google.gson.annotations.SerializedName;

public class VerificationDataModel {
    @SerializedName("verified")
    public boolean isVerified;
    @SerializedName("reason")
    public String reason;
    @SerializedName("signature")
    public String signature;
    @SerializedName("payload")
    public String payload;
}
