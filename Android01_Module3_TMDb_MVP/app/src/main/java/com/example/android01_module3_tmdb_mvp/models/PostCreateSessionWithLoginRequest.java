package com.example.android01_module3_tmdb_mvp.models;

public class PostCreateSessionWithLoginRequest {

    /**
     * username : vuduy
     * password : kenichi1999
     * request_token : 1f2135f2f5893df012b8ccd43cbbd614c002b949
     */

    private String username;
    private String password;
    private String request_token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRequest_token() {
        return request_token;
    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }
}
