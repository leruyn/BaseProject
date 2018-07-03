package com.outsoucre.leruyn.baseproject.v.application;

import android.app.Application;


import com.outsoucre.leruyn.baseproject.common.Constant;
import com.outsoucre.leruyn.baseproject.p.services.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by LeRuyn on 7/3/2018.
 */
public class BaseApplication extends Application {

    /** Singleton Instance */
    private static BaseApplication mInstance;
    /** API Service Instance */
    private ApiService mService;

    /**
     * Default Constructor
     */
    public BaseApplication() {
        super();

        mInstance = this;
    }
    @Override
    public void onCreate() {
        super.onCreate();


        //creat retrofit
        // Create Retrofit Builder
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(Constant.DOMAIN);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addConverterFactory(ScalarsConverterFactory.create());

        // Create Retrofit
        Retrofit retrofit = builder.build();
        // Set Log Enable for Retrofit
        if (Constant.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient
                    .Builder().addInterceptor(interceptor).build();
            builder.client(client);
        }
        // Create Service
        mService = retrofit.create(ApiService.class);

    }

    // Initialize the facebook sdk and then callback manager will handle the login responses.

    /**
     * Get Singleton Instance
     * @return BaseApplication
     */
    public static BaseApplication getInstance() {
        return mInstance;
    }

    /**
     * Get API Service
     * @return ApiService
     */
    public ApiService getService() {
        return mService;
    }
}
