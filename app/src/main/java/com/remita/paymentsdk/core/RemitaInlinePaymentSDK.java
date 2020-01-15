package com.remita.paymentsdk.core;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.remita.paymentsdk.data.MerchantData;
import com.remita.paymentsdk.module.webview.InlinePaymentActivity;
import com.remita.paymentsdk.util.RIPGateway;
import com.remita.paymentsdk.util.StringUtils;

public class RemitaInlinePaymentSDK {

    private static RemitaInlinePaymentSDK remitaInlinePaymentSDK;

    public static RemitaInlinePaymentSDK getInstance() {
        if (remitaInlinePaymentSDK == null) {
            synchronized (RemitaInlinePaymentSDK.class) {
                remitaInlinePaymentSDK = new RemitaInlinePaymentSDK();
            }
        }
        return remitaInlinePaymentSDK;
    }

    public void initiatePayment(Activity activity, String url, String api_key, String email, String amount,
                                String currencyCode, String firstName, String lastName, String customerId, String phoneNumber, String transactionId, String returnUrl, String narration) {

        if (StringUtils.isEmpty(amount)) {
            Log.e(RemitaInlinePaymentSDK.class.getName(), "Please enter an amount");
            return;
        }
        if (Integer.parseInt(amount) <= 0) {
            Log.e(RemitaInlinePaymentSDK.class.getName(), "Invalid Amount <= 0");
            return;
        }
        MerchantData merchantData = new MerchantData.Builder()
                .email(email)
                .amount(amount)
                .currency(currencyCode)
                .firstName(firstName)
                .lastName(lastName)
                .customerId(customerId)
                .phoneNumber(phoneNumber)
                .transactionId(transactionId)
                .returnUrl(returnUrl)
                .narration(narration)
                .key(api_key)
                .url(url)
                .build();

        Intent intent = new Intent(activity, InlinePaymentActivity.class);
        intent.putExtra(RIPGateway.Keys.MERCHANT_DETAILS, merchantData);
        activity.startActivity(intent);
    }
}
