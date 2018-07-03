package com.outsoucre.leruyn.baseproject.p.services;

import com.outsoucre.leruyn.baseproject.m.response.BaseResponse;
import com.outsoucre.leruyn.baseproject.m.response.LoginResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;


/**
 * Created by LeRuyn on 7/3/2018.
 */
public interface ApiService {

    /**
     * Login
     * @param headers
     * @param data
     * @return
     */
    @POST("/api/login")
    @Multipart
    Call<BaseResponse<LoginResponse>>
    login(@HeaderMap Map<String, String> headers,
          @PartMap Map<String, RequestBody> data);


}
