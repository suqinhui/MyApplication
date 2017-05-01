package com.example.cjb.myapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;

import com.example.cjb.myapplication.activity.LoginActivity;
import com.example.cjb.myapplication.util.SharedPreferencesUtils;
import com.example.cjb.myapplication.view.DesktopLayout;

import java.io.IOException;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    //获取系统默认的铃声
    Uri ringUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

    //桌面悬浮窗相关
    private static final String TAG = "AlarmBroadcastReceiver";
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayout;
    private DesktopLayout mDesktopLayout;

    @Override
    public void onReceive(Context context, Intent intent) {
        final MediaPlayer mp = new MediaPlayer();
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

        //设置桌面悬浮窗提示
        createWindowManager(context);
        showDesk(context);

        //注册广播接收器，当桌面闹钟点击开始读书的时候关闭并跳转到app里面
        IntentFilter intentFilter = new IntentFilter("stop_alarm");
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                closeDesk(context);//关闭桌面悬浮窗
                mp.stop();  //关闭铃声
                String isInAPP = SharedPreferencesUtils.getParam(context, "isInAPP", "0").toString();
                //程序在后台才执行跳转到主页面
                if (isInAPP.equals("0")) {
                    Intent intentToMain = new Intent(context, LoginActivity.class);
                    intentToMain.addCategory(Intent.CATEGORY_LAUNCHER);
                    intentToMain.setAction(Intent.ACTION_MAIN);
                    intentToMain.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intentToMain);
                }
            }
        };
        context.getApplicationContext().registerReceiver(receiver, intentFilter);

    }

    /**
     * 设置WindowManager
     */
    private void createWindowManager(Context context) {
        // 取得系统窗体
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //创建桌面布局
        mDesktopLayout = new DesktopLayout(context);
        // 窗体的布局样式
        mLayout = new WindowManager.LayoutParams();
        // 设置窗体显示类型——TYPE_SYSTEM_ALERT(系统提示)
        mLayout.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        // 设置窗体焦点及触摸：
        // FLAG_NOT_FOCUSABLE(不能获得按键输入焦点)
        mLayout.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 设置显示的模式
        mLayout.format = PixelFormat.RGBA_8888;
        // 设置对齐的方法
        mLayout.gravity = Gravity.CENTER | Gravity.CENTER;
        // 设置窗体宽度和高度
        mLayout.width = WindowManager.LayoutParams.MATCH_PARENT;
        mLayout.height = WindowManager.LayoutParams.MATCH_PARENT;
    }

    /**
     * 显示DesktopLayout
     */
    private void showDesk(Context context) {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        try {
            mWindowManager.addView(mDesktopLayout, mLayout);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "开启悬浮窗失败");
        }
    }

    /**
     * 关闭DesktopLayout
     */
    private void closeDesk(Context context) {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        try {
            mWindowManager.removeView(mDesktopLayout);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "关闭悬浮窗失败");
        }
    }
}