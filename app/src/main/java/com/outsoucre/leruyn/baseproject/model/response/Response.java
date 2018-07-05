package com.outsoucre.leruyn.baseproject.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Copyright (C) 2018 EdgeWorks Software.
 * All rights reserved.
 *
 * @author ruyn.
 * @since 04/07/2018
 */
public class Response {
    public static final int SUCCESS = 0;
    public static final int UNKNOWN_ERR = 1;
    public static final int INVALID_TOKEN = 109;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int ACC_VERIFIED = 402;
    public static final int NOT_FOUND = 404;
    public static final int PROFILE_NOT_FOUND = 405;
    public static final int ACC_NOT_FOUND = 406;
    public static final int ACC_CLOSED = 407;
    public static final int ACC_EXISTED = 409;
    public static final int PERMISSION_DENIED = 550;
    public static final int WRONG_VERIFICATION = 701;
    public static final int EMAIL_NOT_FOUND = 702;


    @SerializedName("err_code")
    private int mErrorCode;

    @SerializedName("err_msg")
    private String mErrorMessage;

    public Response() {
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(int errorCode) {
        mErrorCode = errorCode;
    }

    public String getErrorMessage() {
        switch (mErrorCode) {
            case UNKNOWN_ERR:
                mErrorMessage = "";
                break;
            case BAD_REQUEST:
                mErrorMessage = "";
                break;
            case UNAUTHORIZED:
                mErrorMessage = "";
                break;
            case ACC_VERIFIED:
                mErrorMessage = "";
                break;
            case PROFILE_NOT_FOUND:
                mErrorMessage = "";
                break;
            case ACC_NOT_FOUND:
                mErrorMessage = "";
                break;
            case ACC_CLOSED:
                mErrorMessage = "";
                break;
            case ACC_EXISTED:
                mErrorMessage = "";
                break;
            case PERMISSION_DENIED:
                mErrorMessage = "";
                break;
            case WRONG_VERIFICATION:
                mErrorMessage = "";
                break;
            case EMAIL_NOT_FOUND:
                mErrorMessage = "";
                break;

        }
        return mErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        mErrorMessage = errorMessage;
    }

    public boolean isSuccessFull() {
        return mErrorCode == 0;
    }

    @Override
    public String toString() {
        return "Response{" +
                "mErrorCode=" + mErrorCode +
                ", mErrorMessage='" + mErrorMessage + '\'' +
                '}';
    }

}
