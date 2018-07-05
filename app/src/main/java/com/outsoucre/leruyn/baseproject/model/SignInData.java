package com.outsoucre.leruyn.baseproject.model;

import com.google.gson.annotations.SerializedName;

/**
 * Copyright (C) 2018 EdgeWorks Software.
 * All rights reserved.
 *
 * @author ruyn.
 * @since 04/07/2018
 */
public class SignInData {@SerializedName("access_token")
private String mAccessToken;

    @SerializedName("refresh_token")
    private String mRefreshToken;

    @SerializedName("verify_token")
    private String mVerifyToken;

    @SerializedName("verified")
    private int mVerified;

    @SerializedName("profile")
    private Profile mProfile;

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    public String getRefreshToken() {
        return mRefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        mRefreshToken = refreshToken;
    }

    public Profile getProfile() {
        return mProfile;
    }

    public void setProfile(Profile profile) {
        mProfile = profile;
    }

    public String getVerifyToken() {
        return mVerifyToken;
    }

    public void setVerifyToken(String verifyToken) {
        mVerifyToken = verifyToken;
    }

    public int getVerified() {
        return mVerified;
    }

    public void setVerified(int verified) {
        mVerified = verified;
    }

    @Override
    public String toString() {
        return "SignInData{" +
                "mAccessToken='" + mAccessToken + '\'' +
                ", mRefreshToken='" + mRefreshToken + '\'' +
                ", mVerifyToken=" + mVerifyToken +
                ", mVerified=" + mVerified +
                ", mProfile=" + mProfile +
                '}';
    }
}

