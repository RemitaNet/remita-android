package com.remita.paymentsdk.core;

public enum ResponseCode {
    SUCCESSFUL("00", "SUCCESS"), FAILED("01", "FAILED");
    private String code;

    private String description;


    ResponseCode(String code, String description) {
        setCode(code);
        setDescription(description);
    }


    public String getCode() {
        return code;
    }


    public String getDescription() {
        return description;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public void setDescription(String description) {
        this.description = description;
    }
}
