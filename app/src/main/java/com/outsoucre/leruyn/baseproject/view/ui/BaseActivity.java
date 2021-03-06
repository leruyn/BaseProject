package com.outsoucre.leruyn.baseproject.view.ui;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.Toast;

import com.outsoucre.leruyn.baseproject.common.Constant;
import com.outsoucre.leruyn.baseproject.common.eventbus.Event;
import com.outsoucre.leruyn.baseproject.common.permission.PermissionUtil;
import com.outsoucre.leruyn.baseproject.common.receiver.ReceiverManager;
import com.outsoucre.leruyn.baseproject.presenter.services.ApiResponseCode;
import com.outsoucre.leruyn.baseproject.presenter.services.ApiTask;
import com.outsoucre.leruyn.baseproject.view.application.BaseApplication;
import com.outsoucre.leruyn.baseproject.view.interfaces.OnPostResponseListener;
import com.outsoucre.leruyn.baseproject.view.interfaces.OnResponseListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by LeRuyn on 7/3/2018.
 */
public abstract class BaseActivity extends AppCompatActivity implements OnResponseListener {

    /**
     * Application Info
     */
    ApplicationInfo mApplicationInfo;
    /**
     * Status Bar Height
     */
    int mStatusBarHeight;
    /**
     * Navigation Bar Height
     */
    int mNavigationBarHeight;
    /** Current Fragment */
    /**
     * Activity is enable?
     */
    private boolean mIsEnable = true;

    /**
     * The Dialog
     */
    private Dialog mDialog;

    /**
     * Activity is created
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Constant.DEBUG) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        //register event bus
        // Register Events
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

    }


    /**
     * Has Soft Navigation Bar
     *
     * @return True/False
     */
    public boolean hasSoftKeys() {
        boolean hasSoftwareKeys;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display d = getWindowManager().getDefaultDisplay();

            DisplayMetrics realDisplayMetrics = new DisplayMetrics();
            d.getRealMetrics(realDisplayMetrics);

            int realHeight = realDisplayMetrics.heightPixels;
            int realWidth = realDisplayMetrics.widthPixels;

            DisplayMetrics displayMetrics = new DisplayMetrics();
            d.getMetrics(displayMetrics);

            int displayHeight = displayMetrics.heightPixels;
            int displayWidth = displayMetrics.widthPixels;

            hasSoftwareKeys = (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
        } else {
            boolean hasMenuKey = ViewConfiguration.get(this).hasPermanentMenuKey();
            boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            hasSoftwareKeys = !hasMenuKey && !hasBackKey;
        }

        return hasSoftwareKeys;
    }

    /**
     * Show Status Bar
     */
    public void showStatusBar() {
        //if (Build.VERSION.SDK_INT < 16) {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /*} else {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }*/
    }

    /**
     * Hide Status Bar
     */
    public void hideStatusBar() {
        //if (Build.VERSION.SDK_INT < 16) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /*} else {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }*/
    }

    /**
     * Get Status Bar Height
     *
     * @return int
     */
    public int getStatusBarHeight() {
        return mStatusBarHeight;
    }

    /**
     * Get Navigation Bar Height
     *
     * @return int
     */
    public int getNavigationBarHeight() {
        return mNavigationBarHeight;
    }

    /**
     * Get Navigation Width
     *
     * @return width
     */
    int getNavigationWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return (Math.min(metrics.widthPixels, metrics.heightPixels) * 5) / 6;
    }


    @Override
    public final boolean onResponse(ApiTask task, int status) {
        if (this instanceof OnPostResponseListener) {
            OnPostResponseListener listener = (OnPostResponseListener) this;

            if (listener.willProcess(task, status)) {
                return listener.onPostResponse(task, status);
            }
        }

        return onProcessResponse(task, status);
    }

    /**
     * Will Process on Child Classes
     *
     * @param task   ApiTask
     * @param status int
     * @return True if Response is Process on Child Classes
     */
    @Override
    public boolean willProcess(ApiTask task, int status) {
        return status == ApiResponseCode.SUCCESS ||
                status != ApiResponseCode.CANNOT_CONNECT_TO_SERVER;
    }

    /**
     * Local Process Response
     *
     * @param task   ApiTask
     * @param status int
     * @return True if Task is Finished and Execute next Task
     */
    private boolean onProcessResponse(ApiTask task, int status) {
        // hideLoading();

        switch (status) {
            case ApiResponseCode.CANNOT_CONNECT_TO_SERVER:
                //   showRetryDialog(task);
                Log.d("sss", "not connect");
                break;

            default:
                break;
        }

        return true;
    }

    /**
     * Listen event connect/disconnect network
     *
     * @param event
     */
    @Subscribe
    public void getStatusNetwork(Event.CheckInternetConnect event) {
        if (event.getStatusNetwork()) {
            Toast.makeText(this, "Online", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Offline", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Listen event enable/disable GPS
     *
     * @param event
     */
    @Subscribe
    public void getStatusNetwork(Event.CheckGPSEnable event) {
        if (event.getStatusGPS()) {
            Toast.makeText(this, "GPS enable", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "GPS Disable", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * On destroy
     * unregister
     * stop service
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }

        ReceiverManager.init(this).unregisterReceiver(BaseApplication.getInstance().networkStateReceiver);
        ReceiverManager.init(this).unregisterReceiver(BaseApplication.getInstance().gpsStateReceiver);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionUtil.onRequestPermissionResult(this, requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        PermissionUtil.onActivityResult(this, requestCode);
    }
}
