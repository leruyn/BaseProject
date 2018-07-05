package com.outsoucre.leruyn.baseproject.common.eventbus;


public class Event {

    public static class CheckInternetConnect {
        public boolean status;

        public CheckInternetConnect(boolean status) {
            this.status = status;
        }

        public boolean getStatusNetwork() {
            return status;
        }
    }

    public static class CheckGPSEnable {
        public boolean status;

        public CheckGPSEnable(boolean status) {
            this.status = status;
        }

        public boolean getStatusGPS() {
            return status;
        }
    }

}
