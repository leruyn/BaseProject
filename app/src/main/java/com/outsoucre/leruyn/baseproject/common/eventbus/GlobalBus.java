package com.outsoucre.leruyn.baseproject.common.eventbus;

import org.greenrobot.eventbus.EventBus;

public class GlobalBus {
    public static EventBus sBus;

    public static EventBus getBus() {
        if (sBus == null)
            sBus = EventBus.getDefault();
        return sBus;
    }

}
