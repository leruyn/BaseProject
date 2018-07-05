package com.outsoucre.leruyn.baseproject.presenter.services;

import android.support.annotation.NonNull;

import com.outsoucre.leruyn.baseproject.model.ResponseBase;
import com.outsoucre.leruyn.baseproject.model.SignInData;
import com.outsoucre.leruyn.baseproject.model.SignInRequestBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by LeRuyn on 7/3/2018.
 */
public interface ApiService {

    /**
     * L
     * @param body
     * @return
     */
    @POST("/api2/signin")
    Call<ResponseBase<SignInData>>
    login(@NonNull @Body SignInRequestBody body);


}
