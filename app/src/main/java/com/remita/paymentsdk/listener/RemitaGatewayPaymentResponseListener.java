package com.remita.paymentsdk.listener;

import com.remita.paymentsdk.data.PaymentResponse;

public interface RemitaGatewayPaymentResponseListener {
    void onPaymentCompleted(PaymentResponse paymentResponse);
}
