package com.example.android01_module3_tmdb_mvp.models;

public class PostCreateSessionResponse {


    /**
     * success : true
     * session_id : add7f8f18a17f06a4d0d5b965564e29dd28003e7
     */

    private boolean success;
    private String session_id;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
}
