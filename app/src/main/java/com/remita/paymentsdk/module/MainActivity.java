package com.remita.paymentsdk.module;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.remita.paymentsdk.R;
import com.remita.paymentsdk.core.RemitaInlinePaymentSDK;
import com.remita.paymentsdk.data.PaymentResponse;
import com.remita.paymentsdk.listener.RemitaGatewayPaymentResponseListener;
import com.remita.paymentsdk.util.JsonUtil;
import com.remita.paymentsdk.util.RIPGateway;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements RemitaGatewayPaymentResponseListener {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remita_activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText et_amount = findViewById(R.id.et_amount);
                String amount = et_amount.getText().toString();

                String url = RIPGateway.Endpoint.DEMO;
                String api_key = "QzAwMDAxOTUwNjl8NDMyNTkxNjl8ZTg0MjI2MDg4MjU0NzA2NTY2MTYwNGU1NjNiMjUzYjk4ZDQwZjljZGFiMTVmYTljMDUwMGQ0MDg2MjIyYjEyNTA1ZTE2MTMxNmE3ZjM1OTZmYmJkOTE2MTRiY2NmZTY5NTM4MGQ2MDBlZGJlZmM2ODc2YTc2M2M4MjgyZmFjODc=";
                String email = "diagboya@systemspecs.com.ng";
                String currencyCode = "NGN";
                String firstName = "Iyare";
                String lastName = "Diagboya";
                String customerId = "diagboya@systemspecs.com.ng";
                String phoneNumber = "07031731478";
                String transactionId = String.valueOf(new Date().getTime());
                String narration = "Bugatti Chiron 2020";

                RemitaInlinePaymentSDK remitaInlinePaymentSDK = RemitaInlinePaymentSDK.getInstance();
                remitaInlinePaymentSDK.setRemitaGatewayPaymentResponseListener(MainActivity.this);

                remitaInlinePaymentSDK.initiatePayment(MainActivity.this, url, api_key, email,
                        amount, currencyCode, firstName, lastName, customerId, phoneNumber, transactionId, narration);
            }
        });
    }

    @Override
    public void onPaymentCompleted(PaymentResponse paymentResponse) {

        Log.v("+++ Response: ", JsonUtil.toJson(paymentResponse));
        Toast.makeText(this, JsonUtil.toJson(paymentResponse), Toast.LENGTH_LONG);
    }
}
