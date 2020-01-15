package com.remita.paymentsdk.util;

public class RIPGateway {

    public interface Keys {
        String GLOBAL_CACHE_KEY = "global-cache-key";
        String MERCHANT_DETAILS = "merchant-details";
        int CLOSE_ALL_ACTIVITY = 101;
    }

    public interface Endpoint {
        String STAGING = "https://remitademo.net/payment/v1/payment/extended/initialize";
        String PRODUCTION = "https://remita.net/payment/v1/payment/extended/initialize";
        String DEMO = "http://192.9.200.209:6770/payment/v1/payment/extended/initialize";
    }
}
