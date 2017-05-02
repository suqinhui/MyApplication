package com.example.cjb.myapplication.service;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

/**
 * 利用Android辅助工具类AccessibilityService可以获取手机当前页面的信息,用户的操作事件等等.
 */
public class MonitorAppsService extends AccessibilityService {

    private final static String TAG = "MonitorAppsService";
    private static MonitorAppsService mInstance = null;

    public MonitorAppsService() {

    }

    public static MonitorAppsService getInstance() {

        if (mInstance == null) {
            synchronized (MonitorAppsService.class) {
                if (mInstance == null) {
                    mInstance = new MonitorAppsService();
                }
            }
        }
        return mInstance;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        getInstance();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 监听窗口焦点,并且获取焦点窗口的包名
     *
     * @param event
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            Log.d(TAG, "你打开了：" + event.getPackageName());
            //非系统应用在积分不达到100的时候不能打开
            try {
                PackageInfo packageInfo = getPackageManager().getPackageInfo(event.getPackageName().toString(), 0);
                if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                    Log.d(TAG, "你打开了:" + "系统应用");
                }else {
                    Log.d(TAG, "你打开了：" + "非系统应用");
                    if (event.getPackageName().toString().equals("com.example.cjb.myapplication")) {
                        Log.d(TAG, "你打开了爱读app");
                    } else {
                        //发送广播到MainActivity去，根据今日积分去判断要不要关闭正要启动的app
                        Intent intent = new Intent();
                        intent.setAction("stop_other_app");
                        sendBroadcast(intent);
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    /**
     * 此方法用来判断当前应用的辅助功能服务是否开启
     *
     * @param context
     * @return
     */
    public static boolean isAccessibilitySettingsOn(Context context) {
        int accessibilityEnabled = 0;
        try {
            accessibilityEnabled = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        if (accessibilityEnabled == 1) {
            String services = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (services != null) {
                return services.toLowerCase().contains(context.getPackageName().toLowerCase());
            }
        }
        return false;
    }
}
