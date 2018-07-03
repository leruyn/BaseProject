package com.outsoucre.leruyn.baseproject.p.presenters;

import android.content.Context;


import com.outsoucre.leruyn.baseproject.p.services.ApiTask;
import com.outsoucre.leruyn.baseproject.p.services.ApiTaskType;
import com.outsoucre.leruyn.baseproject.v.interfaces.OnResponseListener;

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
     *
     * @param order_number    String
     * @param last_name String
     */
    public void login(final String order_number, final String last_name) {
        ApiTask.execute(new ApiTask.OnCreateCallCallback() {
            @Override
            public Call onCreateCall() {
                return createLoginRequest(order_number, last_name);
            }
        }, ApiTaskType.LOGIN, this);
    }

    @Override
    public boolean onPostResponse(ApiTask task, int status) {
        return mListener.onResponse(task, status);
    }
}

