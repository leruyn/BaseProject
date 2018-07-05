package com.outsoucre.leruyn.baseproject.presenter.presenters;

import android.content.Context;
import android.util.Log;

import com.outsoucre.leruyn.baseproject.model.ResponseBase;
import com.outsoucre.leruyn.baseproject.model.SignInData;
import com.outsoucre.leruyn.baseproject.model.SignInRequestBody;
import com.outsoucre.leruyn.baseproject.model.UserInfo;
import com.outsoucre.leruyn.baseproject.model.response.CheckResponse;
import com.outsoucre.leruyn.baseproject.presenter.services.ApiResponseCallback;
import com.outsoucre.leruyn.baseproject.presenter.services.ApiResponseCode;
import com.outsoucre.leruyn.baseproject.presenter.services.ApiService;
import com.outsoucre.leruyn.baseproject.presenter.services.ApiTask;
import com.outsoucre.leruyn.baseproject.view.application.BaseApplication;

import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by LeRuyn on 7/3/2018.
 */
@SuppressWarnings({"EmptyMethod", "UnusedParameters", "FieldCanBeLocal", "unused"})
abstract class BasePresenter implements ApiResponseCallback {

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
            status = ((ResponseBase) response.body()).getErrorCode();

            if (status == ApiResponseCode.SUCCESS) {
                parseResponse((ResponseBase) response.body());
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


    // region Login

    /**
     * Create Login Request
     *
     * @param email String
     * @param pass  String
     * @return Call
     */
    protected Call createLoginRequest(String email, String pass) {
        SignInRequestBody signInData = new SignInRequestBody();
        signInData.setUsername(email);
        signInData.setPassword(pass);

        return mService.login(signInData);
    }

    /**
     * Process Response
     *
     * @param response LoginResponse
     */
    private void processResponse(SignInData response) {


        Log.d("DEBUG", "[BasePresenter] Login Response: Name = " + response.getProfile().getLastName() + " " + response.getProfile().getFirstName() +
                " | Token = " + response.getAccessToken() +
                " | User Id = " + response.getVerified());

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
    protected void parseResponse(ResponseBase response) {

        Object data = response.getData();

        if (data instanceof SignInData) {
            processResponse((SignInData) data);
        }
    }


    // endregion
}