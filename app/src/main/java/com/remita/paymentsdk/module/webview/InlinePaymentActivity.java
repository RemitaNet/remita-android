package com.remita.paymentsdk.module.webview;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.remita.paymentsdk.R;
import com.remita.paymentsdk.core.RemitaInlinePaymentSDK;
import com.remita.paymentsdk.core.ResponseCode;
import com.remita.paymentsdk.data.MerchantData;
import com.remita.paymentsdk.data.PaymentResponse;
import com.remita.paymentsdk.data.PaymentResponseData;
import com.remita.paymentsdk.util.JsonUtil;
import com.remita.paymentsdk.util.RIPGateway;
import com.remita.paymentsdk.util.StringUtils;

public class InlinePaymentActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inline_webview_activity);
        progressBar = findViewById(R.id.progressBar);

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                String message = consoleMessage.message();

                PaymentResponse paymentResponse = new PaymentResponse();
                PaymentResponseData paymentResponseData = null;
                try {

                    paymentResponseData = JsonUtil.fromJson(message, PaymentResponseData.class);

                    if (!StringUtils.isEmpty(paymentResponseData.getPaymentReference())) {
                        paymentResponse.setResponseCode(ResponseCode.SUCCESSFUL.getCode());
                        paymentResponse.setResponseMessage(ResponseCode.SUCCESSFUL.getDescription());
                        paymentResponse.setPaymentResponseData(paymentResponseData);
                        RemitaInlinePaymentSDK.getInstance().getRemitaGatewayPaymentResponseListener().onPaymentCompleted(paymentResponse);
                    } else {
                        paymentResponse.setResponseCode(ResponseCode.FAILED.getCode());
                        paymentResponse.setResponseMessage(ResponseCode.FAILED.getDescription());
                        paymentResponse.setPaymentResponseData(paymentResponseData);
                        RemitaInlinePaymentSDK.getInstance().getRemitaGatewayPaymentResponseListener().onPaymentCompleted(paymentResponse);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (message.contains("closed")) {
                    finish();
                }

                return super.onConsoleMessage(consoleMessage);
            }

            @Override
            public void onCloseWindow(WebView window) {
                super.onCloseWindow(window);
            }
        });
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                progressBar.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError er) {
                handler.proceed();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }
        });

        MerchantData merchantData = (MerchantData) getIntent().getSerializableExtra(RIPGateway.Keys.MERCHANT_DETAILS);// RemitaSetup.getMerchantData();
        String inlineHtml = InlinePayment.initRequest(merchantData.getUrl(), merchantData.getKey(), merchantData.getEmail(), merchantData.getAmount(), merchantData.getCurrency(), merchantData.getFirstName(), merchantData.getLastName(), merchantData.getCustomerId(), merchantData.getPhoneNumber(), merchantData.getTransactionId(), merchantData.getNarration());

        webView.loadDataWithBaseURL(merchantData.getUrl(), inlineHtml, "text/HTML", "UTF-8", null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

