package com.remita.paymentsdk.module.webview;

public class InlinePayment {
      public static String initRequest(String url, String key, String email, String amount, String currency, String firstName, String lastName, String customerid, String phoneNumber, String transactionId, String returnUrl, String narration) {

        String script = "<html lang=\"en\">\n" +
                "<body onload=\"makePayment()\">\n" +
                "<script>\n" +
                "\t function makePayment() {\n" +
                "               \n" +
                "                fetch('" + url + "', {\n" +
                "                        method: 'post',\n" +
                "                        headers: {\n" +
                "                            \"Content-type\": \"application/json;charset=UTF-8\",\n" +
                "                            \"publicKey\": \"" + key + "\"\n" +
                "                        },\n" +
                "                        body: JSON.stringify({\n" +
                "                            \"transactionId\": " + transactionId + ",\n" +
                "                            \"email\": \"" + email + "\",\n" +
                "                            \"amount\": " + amount + ",\n" +
                "                            \"currency\": \"" + currency + "\",\n" +
                "                            \"firstName\": \"" + firstName + "\",\n" +
                "                            \"lastName\": \"" + lastName + "\",\n" +
                "                            \"phoneNumber\": \"" + phoneNumber + "\",\n" +
                "                            \"customerid\": \"" + customerid + "\",\n" +
                "                            \"narration\": \"" + narration + "\",\n" +
                "                            \"extendedData\": \"null\",\n" +
                "                             \"returnUrl\": \"" + returnUrl + "\"\n" +
                "                        })\n" +
                "                    })\n" +
                "                    .then(function json(response) {\n" +
                "                        return response.json()\n" +
                "                    })\n" +
                "                    .then(function (data) {\n" +
                "                        console.log('Request succeeded with JSON response', data);\n" +
                "                        window.location.href = data.responseData[\"0\"].authorizationUrl;\n" +
                "                    })\n" +
                "                    .catch(function (error) {\n" +
                "                        console.log('Request failed', error);\n" +
                "                    });\n" +
                "        }               \n" +
                "</script>\t\t\n" +
                "</body>\n" +
                "</html>";

        return script;
    }
}
