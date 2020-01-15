package com.remita.paymentsdk.factory;

import com.remita.paymentsdk.RemitaApplication;
import com.remita.paymentsdk.util.SharedPreferencesCache;

public class SharedPreferenceFactory {

    public static SharedPreferencesCache createCache() {
        return new SharedPreferencesCache(RemitaApplication.getAppContext());
    }
}
