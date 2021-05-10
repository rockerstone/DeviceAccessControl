package com.rocketgenius.deviceaccesscontrol.Response;

public class ResponseToken {
    private String token;

    public ResponseToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
