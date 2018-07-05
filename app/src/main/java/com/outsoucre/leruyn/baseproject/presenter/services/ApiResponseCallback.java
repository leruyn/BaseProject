package com.outsoucre.leruyn.baseproject.presenter.services;


/**
 * Created by LeRuyn on 7/3/2018.
 */
public interface ApiResponseCallback {
    /**
     * Process Response
     * @param task ApiTask
     * @return True if Task is finished and do next task
     */
    boolean onResponse(ApiTask task);
}
