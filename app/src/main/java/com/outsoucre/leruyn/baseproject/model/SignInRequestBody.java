package com.outsoucre.leruyn.baseproject.model;

import com.google.gson.annotations.SerializedName;

/**
 * Copyright (C) 2018 EdgeWorks Software.
 * All rights reserved.
 *
 * @author ruyn.
 * @since 04/07/2018
 */
public class SignInRequestBody {
    @SerializedName("username")
    private String mUsername;

    @SerializedName("password")
    private String mPassword;

    public SignInRequestBody() {
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}
