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
        //当app在后台的时候才响起闹钟
        if (inApp.equals("0")) {
//            mp.start();
//            mp.setLooping(true);//循环播放
        }


       /* NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.ibook);   //设置通知图标
        builder.setTicker("您有新的通知");   //手机状态栏的提示
        builder.setWhen(System.currentTimeMillis());   //设置通知的时间
        builder.setContentTitle("通知栏通知");      //设置通知的标题
        builder.setContentText("我来自Notification");  //设置通知的内容
        Intent intent1 = new Intent(context, LoginActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivities(context, 0, new Intent[]{intent1}, 0);
        builder.setContentIntent(pendingIntent);     //设置点击通知之后的跳转
//                builder.setDefaults(Notification.DEFAULT_VIBRATE);   //设置提示震动效果
//                builder.setDefaults(Notification.DEFAULT_LIGHTS);   //设置提示指示灯
//                builder.setDefaults(Notification.DEFAULT_SOUND);   //设置提示声音
        builder.setDefaults(Notification.DEFAULT_ALL);   //设置以上三种提示
        Notification notification = builder.getNotification();   //4.1以下用builder.getNotification()
        manager.notify(0, notification);   //第一个参数为该通知的序号*/
    }
}
