package com.remita.paymentsdk.util;

public class RIPGateway {

    public interface Keys {
        String GLOBAL_CACHE_KEY = "global-cache-key";
        String MERCHANT_DETAILS = "merchant-details";
        int CLOSE_ALL_ACTIVITY = 101;
    }

    public interface Endpoint {
        String DEMO = "https://remitademo.net";
        String PRODUCTION = "https://login.remita.net";
    }
}
