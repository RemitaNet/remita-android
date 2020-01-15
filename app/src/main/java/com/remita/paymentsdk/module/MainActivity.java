package com.remita.paymentsdk.module;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.remita.paymentsdk.R;
import com.remita.paymentsdk.core.RemitaInlinePaymentSDK;
import com.remita.paymentsdk.util.RIPGateway;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

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
                String api_key = "QzAwMDAxMTU0MDF8MTUwOTM3NzUwMjMzNXw2MGFmMDZjYTk4ZWYwNzgyMjIzMDQ5MTY4MmZhMWYwODFlMTAwODg3NDczMzRkYjFjNWQ5MGMzZmM5ZDQwNDEyMmQ1ZThhZjAwM2YyMmU5ZDA1ZjZkM2QyNTg3OWYyZDFhMDRlYjE4NDM3MjVhODYwOGYxMjdhYmJmNzRkYmQwMA==";
                String email = "diagboya@systemspecs.com.ng";
                String currencyCode = "NGN";
                String firstName = "Iyare";
                String lastName = "Diagboya";
                String customerId = "diagboya@systemspecs.com.ng";
                String phoneNumber = "07031731478";
                String transactionId = String.valueOf(new Date().getTime());
                String returnUrl = "https://www.instagram.com/?hl=en";
                String narration = "Bugatti Chiron 2020";

                RemitaInlinePaymentSDK remitaInlinePaymentSDK = RemitaInlinePaymentSDK.getInstance();
                remitaInlinePaymentSDK.initiatePayment(MainActivity.this, url, api_key, email,
                        amount, currencyCode, firstName, lastName, customerId, phoneNumber, transactionId, returnUrl, narration);
            }
        });
    }
}
