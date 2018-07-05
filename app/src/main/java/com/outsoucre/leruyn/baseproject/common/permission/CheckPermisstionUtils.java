package com.outsoucre.leruyn.baseproject.common.permission;

import android.Manifest;
import android.app.Activity;

/**
 * Copyright (C) 2018 EdgeWorks Software.
 * All rights reserved.
 *
 * @author ruyn.
 * @since 04/07/2018
 */
public class CheckPermisstionUtils {
    private static final int LOCATION_PERMISSION_REQ_CODE = 200;
    private static final int WRITE_SD_REQ_CODE = 201;

    public static void checkLocation(Activity activity,
                                     PermissionUtil.ReqPermissionCallback callback) {
        PermissionUtil.checkPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION,
                LOCATION_PERMISSION_REQ_CODE,
                "We need location permission to locate your position",
                "We can't get your location without location permission",
                callback);
    }
}
