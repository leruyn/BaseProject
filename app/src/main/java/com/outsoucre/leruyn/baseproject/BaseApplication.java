package com.outsoucre.leruyn.baseproject;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by LeRuyn on 7/3/2018.
 */
public class BaseApplication extends Application {

    private static BaseApplication mInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    @Deprecated
    public static synchronized BaseApplication getApp() {
        return mInstance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public boolean hasNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null) {
            return false;
        }
        return activeNetwork.isConnectedOrConnecting();
    }
}
