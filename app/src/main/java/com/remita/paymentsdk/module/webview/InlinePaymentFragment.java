package com.remita.paymentsdk.module.webview;

import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.remita.paymentsdk.R;
import com.remita.paymentsdk.core.RemitaSetup;
import com.remita.paymentsdk.data.MerchantData;

import com.remita.paymentsdk.util.RIPGateway;

public class InlinePaymentFragment extends Fragment {

    private WebView webView;
    ProgressBar progressBar;
    private View closeButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.remit_fragment_web_view, container, false);
        webView = root.findViewById(R.id.webView);
        progressBar = root.findViewById(R.id.progressBar);

        closeButton = root.findViewById(R.id.close_btn);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().setResult(RIPGateway.Keys.CLOSE_ALL_ACTIVITY);
                getActivity().finish();
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        MerchantData merchantData = RemitaSetup.getMerchantData();

        String url = InlinePayment.initRequest(merchantData.getUrl(), merchantData.getKey(), merchantData.getEmail(), merchantData.getAmount(), merchantData.getCurrency(), merchantData.getFirstName(), merchantData.getLastName(), merchantData.getCustomerId(), merchantData.getPhoneNumber(), merchantData.getTransactionId(), merchantData.getReturnUrl(), merchantData.getNarration());

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
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (url.contains("/payment/v1/processing/standard/")) {
                            progressBar.setVisibility(View.INVISIBLE);
                        } else {
                            progressBar.setVisibility(View.VISIBLE);
                        }
                    }
                }, 3000);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError er) {
                handler.proceed();
                // Ignore SSL certificate errors
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



