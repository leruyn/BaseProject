package com.outsoucre.leruyn.baseproject.p.presenters;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import com.outsoucre.leruyn.baseproject.m.UserInfo;
import com.outsoucre.leruyn.baseproject.m.response.BaseResponse;
import com.outsoucre.leruyn.baseproject.m.response.CheckResponse;
import com.outsoucre.leruyn.baseproject.m.response.LoginResponse;
import com.outsoucre.leruyn.baseproject.p.services.ApiResponseCallback;
import com.outsoucre.leruyn.baseproject.p.services.ApiResponseCode;
import com.outsoucre.leruyn.baseproject.p.services.ApiService;
import com.outsoucre.leruyn.baseproject.p.services.ApiTask;
import com.outsoucre.leruyn.baseproject.v.application.BaseApplication;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by LeRuyn on 7/3/2018.
 */
@SuppressWarnings({"EmptyMethod", "UnusedParameters", "FieldCanBeLocal", "unused"})
abstract class BasePresenter implements ApiResponseCallback {

    /**
     * Request Headers
     */
    static Map<String, String> mHeaders;
    /**
     * User Info (Shared Between All of Presenters)
     */
    static UserInfo mUserInfo;
    /**
     * The Context
     */
    final Context mContext;
    /**
     * z
     * The Api Service
     */
    final ApiService mService;
    /**
     * Realm Instance
     */
//    final Realm mRealm;

    /**
     * Default Constructor
     *
     * @param context Context
     */
    public BasePresenter(Context context) {
        mContext = context;
        mService = BaseApplication.getInstance().getService();

        if (mHeaders == null) {
            loadDeviceInfo();
        }
    }

    /**
     * Get User Info
     *
     * @return Info
     */
    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    /**
     * Clear Invalid Token
     */
    public void clearInvalidToken() {
        mUserInfo.setToken(null);

        Log.d("DEBUG", "[BasePresenter] Clear Token");
    }


    /**
     * Process Response
     *
     * @param task ApiTask
     * @return True if Task is finished and do next task
     */
    @Override
    public final boolean onResponse(ApiTask task) {
        int status = ApiResponseCode.CANNOT_CONNECT_TO_SERVER;
        Response response = task.getResponse();

        if (response != null &&
                response.code() == 200) {
            status = ((BaseResponse) response.body()).getCode();

            if (status == ApiResponseCode.SUCCESS) {
                parseResponse((BaseResponse) response.body());
            }
        }

        return onPostResponse(task, status);
    }

    /**
     * Process Response
     *
     * @param task   ApiTask
     * @param status int
     * @return True if Task is finished and do next task
     */
    public abstract boolean onPostResponse(ApiTask task, int status);

    /**
     * Load Device Info
     */
    private void loadDeviceInfo() {
        try {

            mHeaders = new HashMap<>();
            mHeaders.put("X-DEVICE-ID", Settings.Secure.getString(mContext.getContentResolver(),
                    Settings.Secure.ANDROID_ID));
            mHeaders.put("X-OS-TYPE", "Android");
            mHeaders.put("X-OS-VERSION", Build.VERSION.RELEASE);
            mHeaders.put("X-API-ID", "kranse");
            mHeaders.put("X-API-KEY", "1qazxcde32ws");
            mHeaders.put("X-APP-VERSION", mContext.getPackageManager()
                    .getPackageInfo(mContext.getPackageName(), 0).versionName);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    // region Login

    /**
     * Create Login Request
     *
     * @param email String
     * @param pass  String
     * @return Call
     */
    protected Call createLoginRequest(String email, String pass) {
        Map<String, RequestBody> data = new HashMap<>();
        data.put("order_number", RequestBody.create(MediaType.parse("text/plain"), email));
        data.put("last_name", RequestBody.create(MediaType.parse("text/plain"), pass));

        return mService.login(mHeaders, data);
    }

    /**
     * Process Response
     *
     * @param response LoginResponse
     */
    private void processResponse(LoginResponse response) {
        Log.d("RLV Login ", response.toString());


        Log.d("DEBUG", "[BasePresenter] Login Response: Name = " + response.getName() +
                " | Email = " + response.getEmail() + " | Token = " + response.getToken() +
                " | User Id = " + response.getUserId());

    }


    /**
     * Process Response
     *
     * @param response LoginResponse
     */
    private void processResponse(CheckResponse response) {
        Log.d("RLV check ", response.toString());


//        mRealm.commitTransaction();
    }

    // endregion

    /**
     * Parse Response
     *
     * @param response BaseResponse
     */
    protected void parseResponse(BaseResponse response) {

        Object data = response.getData();

        if (data instanceof LoginResponse) {
            processResponse((LoginResponse) data);
        }
    }


    // endregion
}