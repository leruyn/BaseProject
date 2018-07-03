package com.outsoucre.leruyn.baseproject.v.ui;

import android.app.Dialog;
import android.os.Bundle;

import com.outsoucre.leruyn.baseproject.R;
import com.outsoucre.leruyn.baseproject.m.response.LoginResponse;
import com.outsoucre.leruyn.baseproject.p.presenters.SignPresenter;
import com.outsoucre.leruyn.baseproject.p.services.ApiResponseCode;
import com.outsoucre.leruyn.baseproject.p.services.ApiTask;
import com.outsoucre.leruyn.baseproject.p.services.ApiTaskType;
import com.outsoucre.leruyn.baseproject.v.interfaces.OnPostResponseListener;

/**
 * Created by LeRuyn on 7/3/2018.
 */
public class LoginActivity extends BaseActivity implements OnPostResponseListener {


    private SignPresenter mPresenter;
    private LoginResponse mLoginResponse;

    /**
     * The Dialog
     */
    private Dialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPresenter = new SignPresenter(this, this);


    }

    @Override
    public boolean onPostResponse(ApiTask task, int status) {

        if (task.getType() == ApiTaskType.LOGIN) {
            hideLoading();
            if (status == ApiResponseCode.SUCCESS) {
                //
            } else {
            }
        }

        return true;
    }

    @Override
    public boolean willProcess(ApiTask task, int status) {
        return super.willProcess(task, status);
    }


    /**
     * Hide Current Loading Dialog
     */
    protected void hideLoading() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

}

