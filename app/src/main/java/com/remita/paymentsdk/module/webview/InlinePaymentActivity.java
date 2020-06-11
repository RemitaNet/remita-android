package com.remita.paymentsdk.module.webview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.remita.paymentsdk.R;
import com.remita.paymentsdk.core.BaseActivity;
import com.remita.paymentsdk.core.RemitaSetup;
import com.remita.paymentsdk.data.MerchantData;
import com.remita.paymentsdk.util.ActivityUtils;
import com.remita.paymentsdk.util.RIPGateway;

public class InlinePaymentActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remita_default_activity);
        setWidthSize(1.03, 0.85);

        Intent intent = getIntent();

        if (intent.hasExtra(RIPGateway.Keys.MERCHANT_DETAILS)) {
            MerchantData merchantData = (MerchantData) intent.getSerializableExtra(RIPGateway.Keys.MERCHANT_DETAILS);
            if (merchantData != null) {
                RemitaSetup.setPassedIntentData(merchantData);
            }
        }

        InlinePaymentFragment inlinePaymentFragment =
                (InlinePaymentFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (inlinePaymentFragment == null) {
            inlinePaymentFragment = new InlinePaymentFragment();
            Intent i = getIntent();
        }
        ActivityUtils.addFragmentToActivity(
                getSupportFragmentManager(), inlinePaymentFragment, R.id.contentFrame
        );
    }
}

