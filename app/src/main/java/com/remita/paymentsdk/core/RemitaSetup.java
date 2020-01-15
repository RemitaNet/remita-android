package com.remita.paymentsdk.core;

import com.remita.paymentsdk.data.MerchantData;

public class RemitaSetup {

    private static MerchantData passedIntentData;

    public static MerchantData getMerchantData() {
        return passedIntentData;
    }

    public static MerchantData getPassedIntentData() {
        return passedIntentData;
    }

    public static void setPassedIntentData(MerchantData merchantData) {
        passedIntentData = merchantData;
    }

}
