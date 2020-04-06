package com.example.module2_toeic.background;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


public class ReminderService extends Service {
    private static final String TAG = "ReminderService";

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return START_NOT_STICKY;
    }
}
