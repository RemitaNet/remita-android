package com.remita.paymentsdk.module.webview;

public class InlinePayment {
    public static String initRequest(String url, String key, String email, String amount, String currency, String firstName, String lastName, String customerid, String phoneNumber, String transactionId, String narration) {

        String script = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n"
                + "<header><meta name=\"viewport\" content=\"initial-scale=1.0\"/></header>" +
                "<body  onload=\"makePayment()\">\n" +
                "    <script>\n" +
                "       function makePayment() {\n" +
                "       var paymentEngine = RmPaymentEngine.init({\n" +
                "\t\t\t\tkey:'" + key + "',\n" +
                "                \"customerid\": \"" + customerid + "\",\n" +
                "\t\t\t\t\ttransactionId:" + transactionId + ",\n" +
                "                 \"firstName\": \"" + firstName + "\",\n" +
                "                 \"lastName\": \"" + lastName + "\",\n" +
                "                 \"email\": \"" + email + "\",\n" +
                "                  \"amount\": " + amount + ",\n" +
                "                  \"phoneNumber\": \"" + phoneNumber + "\",\n" +
                "                  \"narration\": \"" + narration + "\",\n" +
                "                  \"currency\": \"" + currency + "\",\n" +
                "                  \n" +
                "                    onSuccess: function (response) {\n" +
                "                        console.log(JSON.stringify(response));\n" +
                "                    },\n" +
                "                    onError: function (response) {\n" +
                "                        console.log(JSON.stringify(response));\n" +
                "                    },\n" +
                "                    onClose: function () {\n" +
                "                        console.log(\"closed\");\n" +
                "                    },                 \n" +
                "                });\n" +
                "                paymentEngine.openIframe();\n" +
                "        }\n" +
                "    </script>\n" +
                "   \n" +
                "     <script type=\"text/javascript\" src=\"" + url + "/payment/v1/remita-pay-inline.bundle.js\"></script>\n" +
                "</body>\n" +
                "\n" +
                "</html>\n";

        return script;
    }
}
