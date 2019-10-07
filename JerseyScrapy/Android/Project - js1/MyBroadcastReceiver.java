package com.example.js1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Context;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("Hello")){
            Log.v("brad", "tbroadcast");
        }
    }
}
