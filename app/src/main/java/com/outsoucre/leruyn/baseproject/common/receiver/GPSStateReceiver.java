package com.outsoucre.leruyn.baseproject.common.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.outsoucre.leruyn.baseproject.common.eventbus.Event;
import com.outsoucre.leruyn.baseproject.common.eventbus.GlobalBus;

/**
 * Copyright (C) 2018 EdgeWorks Software.
 * All rights reserved.
 *
 * @author ruyn.
 * @since 04/07/2018
 */
public class GPSStateReceiver extends BroadcastReceiver implements LocationListener {
    @Override
    public void onReceive(Context context, Intent intent) {
        LocationManager locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Event.CheckGPSEnable checkGPSEnable =
                    new Event.CheckGPSEnable(true);
            GlobalBus.getBus().post(checkGPSEnable);
        } else {
            Event.CheckGPSEnable checkGPSEnable =
                    new Event.CheckGPSEnable(false);
            GlobalBus.getBus().post(checkGPSEnable);
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
