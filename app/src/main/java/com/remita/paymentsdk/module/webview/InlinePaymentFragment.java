package com.remita.paymentsdk.module.webview;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.remita.paymentsdk.core.RemitaSetup;
import com.remita.paymentsdk.core.ResponseCode;
import com.remita.paymentsdk.data.MerchantData;
import com.remita.paymentsdk.data.PaymentResponse;
import com.remita.paymentsdk.data.PaymentResponseData;
import com.remita.paymentsdk.util.JsonUtil;
import com.remita.paymentsdk.util.StringUtils;

public class InlinePaymentFragment extends Fragment {

    private WebView webView;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.remit_fragment_web_view, container, false);
        webView = root.findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);

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
                    getActivity().finish();
                }

                return super.onConsoleMessage(consoleMessage);
            }

            @Override
            public void onCloseWindow(WebView window) {
                super.onCloseWindow(window);
            }
        });

        MerchantData merchantData = RemitaSetup.getMerchantData();
        String url = InlinePayment.initRequest(merchantData.getUrl(), merchantData.getKey(), merchantData.getEmail(), merchantData.getAmount(), merchantData.getCurrency(), merchantData.getFirstName(), merchantData.getLastName(), merchantData.getCustomerId(), merchantData.getPhoneNumber(), merchantData.getTransactionId(), merchantData.getNarration());

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progressBar.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
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

        webView.loadData(url, "text/HTML", "UTF-8");

        return root;
    }
}



