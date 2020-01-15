package com.remita.paymentsdk.data;

import java.io.Serializable;

public class MerchantData implements Serializable {
    private final String key;
    private final String url;
    private final String id;
    private final String ref;
    private final String email;
    private final String amount;
    private final String currency;
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final String returnUrl;
    private final String customerId;
    private final String metaData;
    private final String transactionId;
    private final String hasTLSFallback;
    private final String narration;

    public static class Builder {
        private String key;
        private String url;
        private String id;
        private String ref;
        private String email;
        private String amount;
        private String currency;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String returnUrl;
        private String customerId;
        private String metaData;
        private String transactionId;
        private String hasTLSFallback;
        private String narration;

        public Builder() {
        }

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public Builder url(String url) {
            this.url= url;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder ref(String ref) {
            this.ref = ref;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder amount(String amount) {
            this.amount = amount;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder firstName(String firsstName) {
            this.firstName = firsstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        public Builder returnUrl(String returnUrl) {
            this.returnUrl = returnUrl;
            return this;
        }

        public Builder customerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder metaData(String metaData) {
            this.metaData = metaData;
            return this;
        }

        public Builder transactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder hasTLSFabllback(String hasTLSFallback) {
            this.hasTLSFallback = hasTLSFallback;
            return this;
        }

        public Builder narration(String narration) {
            this.narration = narration;
            return this;
        }

        public MerchantData build() {
            return new MerchantData(this);
        }
    }

    private MerchantData(Builder builder) {
        key = builder.key;
        url = builder.url;
        id = builder.id;
        ref = builder.ref;
        email = builder.email;
        amount = builder.amount;
        currency = builder.currency;
        firstName = builder.firstName;
        lastName = builder.lastName;
        phoneNumber = builder.phoneNumber;
        returnUrl = builder.returnUrl;
        customerId = builder.customerId;
        metaData = builder.metaData;
        transactionId = builder.transactionId;
        hasTLSFallback = builder.hasTLSFallback;
        narration = builder.narration;
    }

    public String getKey() {
        return key;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }

    public String getRef() {
        return ref;
    }

    public String getEmail() {
        return email;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getMetaData() {
        return metaData;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getHasTLSFallback() {
        return hasTLSFallback;
    }

    public String getNarration() {
        return narration;
    }

    @Override
    public String toString() {
        return "MerchantData{" +
                "key='" + key + '\'' +
                "url='" + url + '\'' +
                ", id='" + id + '\'' +
                ", ref='" + ref + '\'' +
                ", email='" + email + '\'' +
                ", amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", returnUrl='" + returnUrl + '\'' +
                ", customerId='" + customerId + '\'' +
                ", metaData='" + metaData + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", hasTLSFallback='" + hasTLSFallback + '\'' +
                ", narration='" + narration + '\'' +
                '}';
    }
}
