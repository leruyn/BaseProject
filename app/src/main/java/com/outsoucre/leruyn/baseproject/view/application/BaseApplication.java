package com.outsoucre.leruyn.baseproject.view.application;

import android.app.Application;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.ConnectivityManager;

import com.outsoucre.leruyn.baseproject.common.Constant;
import com.outsoucre.leruyn.baseproject.common.receiver.GPSStateReceiver;
import com.outsoucre.leruyn.baseproject.common.receiver.NetworkStateReceiver;
import com.outsoucre.leruyn.baseproject.common.receiver.ReceiverManager;
import com.outsoucre.leruyn.baseproject.presenter.services.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by LeRuyn on 7/3/2018.
 */
public class BaseApplication extends Application {

    /**
     * Singleton Instance
     */
    private static BaseApplication mInstance;
    /**
     * API Service Instance
     */
    private ApiService mService;

    /**
     * Network broadcast
     */
    public NetworkStateReceiver networkStateReceiver;
    /**
     * GPS broadcast
     */
    public GPSStateReceiver gpsStateReceiver;
    /**
     * Receiver Manager
     */
    public ReceiverManager receiverManager;

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

        networkStateReceiver = new NetworkStateReceiver();
        gpsStateReceiver = new GPSStateReceiver();

        ReceiverManager.init(this).registerReceiver(networkStateReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        ReceiverManager.init(this).registerReceiver(gpsStateReceiver, new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION));

    }

    // Initialize the facebook sdk and then callback manager will handle the login responses.

    /**
     * Get Singleton Instance
     *
     * @return BaseApplication
     */
    public static BaseApplication getInstance() {
        return mInstance;
    }

    /**
     * Get API Service
     *
     * @return ApiService
     */
    public ApiService getService() {
        return mService;
    }

}
