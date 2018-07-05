package com.outsoucre.leruyn.baseproject.presenter.presenters;

import android.content.Context;


import com.outsoucre.leruyn.baseproject.presenter.services.ApiTask;
import com.outsoucre.leruyn.baseproject.presenter.services.ApiTaskType;
import com.outsoucre.leruyn.baseproject.view.interfaces.OnResponseListener;

import retrofit2.Call;

/**
 * Created by LeRuyn on 7/3/2018.
 */
public class SignPresenter extends BasePresenter {

    private OnResponseListener mListener;

    /**
     * Default Constructor
     *
     * @param context Context
     */
    public SignPresenter(Context context, OnResponseListener mListener) {
        super(context);
         this.mListener= mListener;
    }

    /**
     * Login
     * @param email
     * @param password
     */
    public void login(final String email, final String password) {
        ApiTask.execute(new ApiTask.OnCreateCallCallback() {
            @Override
            public Call onCreateCall() {
                return createLoginRequest(email, password);
            }
        }, ApiTaskType.LOGIN, this);
    }

    @Override
    public boolean onPostResponse(ApiTask task, int status) {
        return mListener.onResponse(task, status);
    }
}

