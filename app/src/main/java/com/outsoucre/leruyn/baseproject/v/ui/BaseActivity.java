package com.outsoucre.leruyn.baseproject.v.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.outsoucre.leruyn.baseproject.common.Constant;
import com.outsoucre.leruyn.baseproject.p.services.ApiResponseCode;
import com.outsoucre.leruyn.baseproject.p.services.ApiTask;
import com.outsoucre.leruyn.baseproject.v.interfaces.OnPostResponseListener;
import com.outsoucre.leruyn.baseproject.v.interfaces.OnResponseListener;

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
     * Called to process touch screen events.  You can override this to
     * intercept all touch screen events before they are dispatched to the
     * window.  Be sure to call this implementation for touch screen events
     * that should be handled normally.
     *
     * @param ev The touch screen event.
     * @return boolean Return true if this event was consumed.
     */

}
