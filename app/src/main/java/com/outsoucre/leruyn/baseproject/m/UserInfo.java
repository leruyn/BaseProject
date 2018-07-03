package com.outsoucre.leruyn.baseproject.m;



/**
 * Copyright Innotech Vietnam
 * Created by Huynh Thanh Long on 4/11/16.
 */
public class UserInfo {

    private String userId;
    private String name;
    private String email;
    private String token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
