package com.outsoucre.leruyn.baseproject.common.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.outsoucre.leruyn.baseproject.common.eventbus.Event;
import com.outsoucre.leruyn.baseproject.common.eventbus.GlobalBus;

/**
 * Copyright (C) 2018 EdgeWorks Software.
 * All rights reserved.
 *
 * @author ruyn.
 * @since 04/07/2018
 */
public class NetworkStateReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent.getExtras() != null) {
            NetworkInfo ni = (NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {

                Event.CheckInternetConnect checkInternetConnect =
                        new Event.CheckInternetConnect(true);
                GlobalBus.getBus().post(checkInternetConnect);

            } else if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {

                Event.CheckInternetConnect checkInternetConnect =
                        new Event.CheckInternetConnect(false);
                GlobalBus.getBus().post(checkInternetConnect);

            }
        }
    }
}
