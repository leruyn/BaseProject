package com.outsoucre.leruyn.baseproject.view.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;

import com.outsoucre.leruyn.baseproject.R;
import com.outsoucre.leruyn.baseproject.common.permission.CheckPermisstionUtils;
import com.outsoucre.leruyn.baseproject.common.permission.PermissionUtil;
import com.outsoucre.leruyn.baseproject.model.response.LoginResponse;
import com.outsoucre.leruyn.baseproject.presenter.presenters.SignPresenter;
import com.outsoucre.leruyn.baseproject.presenter.services.ApiResponseCode;
import com.outsoucre.leruyn.baseproject.presenter.services.ApiTask;
import com.outsoucre.leruyn.baseproject.presenter.services.ApiTaskType;
import com.outsoucre.leruyn.baseproject.view.interfaces.OnPostResponseListener;

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
        mPresenter.login("ruyn4@yopmail.com", "1");

        CheckPermisstionUtils.checkLocation(this,
                new PermissionUtil.ReqPermissionCallback() {
                    @Override
                    public void onResult(boolean success) {
                        if (success) {
                            Log.e("RLV", "checkLocation success");
                        } else {
                        }
                    }
                });

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

