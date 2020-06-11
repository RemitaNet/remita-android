package com.remita.paymentsdk.data;

public class PaymentResponse {
    private String responseCode;
    private String responseMessage;
    private PaymentResponseData paymentResponseData;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public PaymentResponseData getPaymentResponseData() {
        return paymentResponseData;
    }

    public void setPaymentResponseData(PaymentResponseData paymentResponseData) {
        this.paymentResponseData = paymentResponseData;
    }
}
