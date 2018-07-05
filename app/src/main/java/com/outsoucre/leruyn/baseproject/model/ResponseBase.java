package com.outsoucre.leruyn.baseproject.model;

import com.google.gson.annotations.SerializedName;
import com.outsoucre.leruyn.baseproject.model.response.Response;

/**
 * Copyright (C) 2018 EdgeWorks Software.
 * All rights reserved.
 *
 * @author ruyn.
 * @since 04/07/2018
 */
public class ResponseBase<T> extends Response {
    @SerializedName("data")
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseBase{" +
                "data=" + data +
                '}';
    }
}
