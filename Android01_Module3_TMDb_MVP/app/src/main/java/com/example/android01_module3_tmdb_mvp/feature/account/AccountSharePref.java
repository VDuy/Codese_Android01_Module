package com.example.android01_module3_tmdb_mvp.feature.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AccountSharePref {
    private static final String SESSION_ID_KEY = "session_id_key";
    SharedPreferences sharedPreferences;

    public AccountSharePref(Context context) {
        this.sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
    }

    public void saveSessionId(String sessionId) {
        sharedPreferences
                .edit()
                .putString(SESSION_ID_KEY, sessionId)
                .apply();

    }

    public String getSessionId() {

        return sharedPreferences.getString(SESSION_ID_KEY, null);

    }
}
