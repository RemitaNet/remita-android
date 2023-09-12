# Remita Inline Payment SDK

---
- [Overview](#Overview)
- [Installation](#Installation)
- [Usage](#Usage)
- [Contributing](#Contributing)
- [License](License)

---

## Overview
The following pages outlines the steps to integrating Remita Inline Payment SDK to your app.

---

## Installation
**Note:** This setup is done on Android Studio 3.5


![](images/inline_snapshot.JPG)

1. Go to **File** in Menu bar, and click on **Project Structure**

![](images/project_structure.JPG)

2. Select **Modules** and click the &quot; **+**&quot; under Modules section to add the &quot;rip-sdk.aar&quot; to your project.
**Note:** You can find the rip-sdk.aar at: https://github.com/RemitaNet/remita-android/tree/master/aar

![](images/modules.JPG)

3. Select Import .JAR/.AAR Package

![](images/aar_location.JPG)

4. Locate rip-sdk.aar and click ok

![](images/import_module.JPG)

5. Select **Dependencies** and then select **app** then click on &quot; **+&quot;** under **Declared Dependencies**

![](images/dependencies.JPG)
 
6. Select **Module Dependency**

![](images/module_dependency.JPG)
 
7. Select the **rip-sdk** module and click &#39;OK&#39;.

![](images/select_rip-sdk.JPG)
 
8. Click on **Apply/Ok.**

![](images/select_rip-sdk_2.JPG)

9. Rebuild project, you should see **implementation project(path: &#39;:rip-sdk&#39;)** in your dependencies block.

![](images/rebuild.JPG)

10. Add **implementation 'com.google.code.gson:gson:2.8.2'** to your applications dependencies (If you haven't).
 
---

## Usage

You should invoke the RemitaInlinePaymentSDK.getInstance() at any point when making payment:

**Sample Code:**
```java
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
 ```


Where url can be:

**RIPGateway.Endpoint.DEMO** (for testing) and **RIPGateway.Endpoint.PRODUCTION** (for live).

### Test cards
```json
              CARD: 5178 6810 0000 0002,  
              Expire Date : 05/30,  
              CCV: 000, 
              OTP: 123456
```

### Useful links
Join our Slack Developer/Support channel on [Slack.](http://bit.ly/RemitaDevSlack)
    
### Support
For all other support needs, support@remita.net

---

## Contributing
To contribute to this repo, follow these guidelines for creating issues, proposing new features, and submitting pull requests:

1. Fork the repository.
2. Create a new branch: `git checkout -b "feature-name"`
3. Make your changes and commit: `git commit -m "added some new features"`
4. Push your changes: `git push origin feature-name`
5. Submit a Pull Request (PR).

Thank you!

---

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
