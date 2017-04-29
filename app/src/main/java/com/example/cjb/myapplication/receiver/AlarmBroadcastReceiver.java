package com.example.cjb.myapplication.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cjb.myapplication.R;
import com.example.cjb.myapplication.activity.LoginActivity;
import com.example.cjb.myapplication.util.SharedPreferencesUtils;

import java.io.IOException;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    Uri ringUri;

    @Override
    public void onReceive(Context context, Intent intent) {
        String inApp = SharedPreferencesUtils.getParam(context, "inAPP", "0").toString();
        Log.i("info", "!!!!!!" + SharedPreferencesUtils.getParam(context, "inAPP", "0").toString());
        String msg = intent.getStringExtra("msg");
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        final MediaPlayer mp = new MediaPlayer();
        String uri = intent.getStringExtra("ringURI");
        if (uri != null) {
            ringUri = Uri.parse(uri);
            Log.d("AlarmActivity", ringUri.toString());
        }
        try {
            mp.setDataSource(context, ringUri);
            mp.prepare();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();
        mp.setLooping(true);//循环播放
    }
}