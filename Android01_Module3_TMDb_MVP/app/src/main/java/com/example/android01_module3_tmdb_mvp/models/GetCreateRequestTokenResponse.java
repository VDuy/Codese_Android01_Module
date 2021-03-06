package com.example.android01_module3_tmdb_mvp.models;

public class GetCreateRequestTokenResponse {

    /**
     * success : true
     * expires_at : 2020-05-02 08:18:05 UTC
     * request_token : 1f2135f2f5893df012b8ccd43cbbd614c002b949
     */

    private boolean success;
    private String expires_at;
    private String request_token;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }

    public String getRequest_token() {
        return request_token;
    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }
}