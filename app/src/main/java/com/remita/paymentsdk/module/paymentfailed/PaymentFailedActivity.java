package com.remita.paymentsdk.module.paymentfailed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.remita.paymentsdk.R;
import com.remita.paymentsdk.core.BaseActivity;
import com.remita.paymentsdk.util.ActivityUtils;

public class PaymentFailedActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remita_default_activity);
        setWidthSize(0.95, 0.80);
        findViewById(R.id.progressBar).setVisibility(View.GONE);

        PaymentFailedFragment paymentFailedFragment =
                (PaymentFailedFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (paymentFailedFragment == null) {
            paymentFailedFragment = new PaymentFailedFragment();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), paymentFailedFragment, R.id.contentFrame
            );
        }
    }
}
