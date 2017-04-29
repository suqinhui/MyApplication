package com.example.cjb.myapplication.activity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.cjb.myapplication.R;
import com.example.cjb.myapplication.db.DBManager;
import com.example.cjb.myapplication.receiver.AlarmBroadcastReceiver;
import com.example.cjb.myapplication.util.SharedPreferencesUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    private WebView webView;
    private WebSettings webSettings;
    private DBManager dbManager;
    private Calendar calendar = Calendar.getInstance(Locale.CHINESE);
    private AlarmManager alarmManager;
    private Uri ringUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvent();
        initTimeTask();//初始化定时任务，判断是否需要清空今日积分
    }

    @Override
    protected void onStop() {
        //app返回后台调用的方法
        super.onStop();
        SharedPreferencesUtils.setParam(this, "inAPP", "0");
        Log.i("info", "!!!!out");
    }

    @Override
    protected void onResume() {
        //app返回前台调用的方法
        super.onResume();
        SharedPreferencesUtils.setParam(this, "inAPP", "1");
        Log.i("info", "!!!!in");
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.webView);
        webSettings = webView.getSettings();
        dbManager = new DBManager(this);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        String username = SharedPreferencesUtils.getParam(LoginActivity.this, "username", "").toString();
        Log.i("info","!!!!!!!!!!"+dbManager.dbGetUserCollectBook(username));
    }

    //初始化定时任务，判断是否需要清空今日积分
    private void initTimeTask() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //要做的事情
                String newTime = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                String oldTime = SharedPreferencesUtils.getParam(LoginActivity.this, "oldTime", "").toString();
                if (!newTime.equals(oldTime)) {
                    //清空今日积分
                    dbManager.dbResetUserTodayIntegration();
                }
                handler.postDelayed(this, 1000);
                SharedPreferencesUtils.setParam(LoginActivity.this, "oldTime", String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
            }
        };
        handler.postDelayed(runnable, 1000);//每两秒执行一次runnable
    }


    @SuppressLint("JavascriptInterface")
    private void initEvent() {
        webSettings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/base/main.html");
        ////设置本地调用对象及其接口
        webView.addJavascriptInterface(new JsInteraction(), "JsInteractionEvent");
        //设置不用系统浏览器打开,直接显示在当前Webview
        webView.setWebViewClient(new WebViewClient() {
        });
    }

    //定义用来跟js交互的类
    public class JsInteraction {
        @JavascriptInterface
        //登录检验
        public boolean loginCheck(String username, String password) {
            boolean loginResult = false;
            if (username.equals("") || password.equals("")) {
                Toast.makeText(LoginActivity.this, "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
            } else {
                if (dbManager.dbLoginCheck(username, password)) {
                    loginResult = true;
                    SharedPreferencesUtils.setParam(LoginActivity.this, "username", username);
                } else {
                    Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                    loginResult = false;
                }
            }
            return loginResult;
        }


        @JavascriptInterface
        //注册
        public boolean doRegister(String username, String nickname, String password, String password1) {
            boolean registerResult = false;
            Log.i("info", "!!!!" + username + " " + nickname + " " + password + " " + password1);
            if (username.equals("")) {
                Toast.makeText(LoginActivity.this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
            } else if (nickname.equals("")) {
                Toast.makeText(LoginActivity.this, "昵称不能为空！", Toast.LENGTH_SHORT).show();
            } else if (password.equals("")) {
                Toast.makeText(LoginActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
            } else if (password1.equals("")) {
                Toast.makeText(LoginActivity.this, "请确认密码！", Toast.LENGTH_SHORT).show();
            } else if (password.length() < 6) {
                Toast.makeText(LoginActivity.this, "密码过短，请重新输入！", Toast.LENGTH_SHORT).show();
            } else if (password.length() > 16) {
                Toast.makeText(LoginActivity.this, "密码过长，请重新输入！", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(password1)) {
                Toast.makeText(LoginActivity.this, "两次输入的密码不一致，请重新输入！", Toast.LENGTH_SHORT).show();
            } else {
                boolean dbRegister = dbManager.dbUserRegister(username, password, nickname);
                if (!dbRegister) {
                    Toast.makeText(LoginActivity.this, "该账号已存在，请重新输入！", Toast.LENGTH_SHORT).show();
                } else {
                    registerResult = true;
                }
            }
            return registerResult;
        }

        //获取用户信息
        @JavascriptInterface
        public String getUserInfo() {
            String username = SharedPreferencesUtils.getParam(LoginActivity.this, "username", "").toString();
            return dbManager.dbGetUserInfo(username);
        }

        @JavascriptInterface
        //将设置的定时读书时间更新到数据库
        public void updateTiming(String time) {
            String username = SharedPreferencesUtils.getParam(LoginActivity.this, "username", "").toString();
            dbManager.dbUpdateTiming(username, time);
            Toast.makeText(LoginActivity.this, "设置成功！", Toast.LENGTH_SHORT).show();

            //做闹钟功能
            Log.i("info", "!!!!time" + time.substring(0, 2) + " " + time.substring(3, 5));
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(3, 5));
            calendar.set(Calendar.HOUR, hour);
            calendar.set(Calendar.MINUTE, minute);
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, AlarmBroadcastReceiver.class);
            intent.putExtra("msg", "Time For Read!");
            intent.putExtra("ringURI", ringUri.toString());
            PendingIntent pendingIntent = PendingIntent.getBroadcast(LoginActivity.this, 0, intent, 0);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }

        @JavascriptInterface
        //修改个人信息
        public boolean updateUserInfo(String nickname, String sex, String age, String email) {
            boolean result = false;
            Log.i("info", "!!!!" + nickname + " " + sex + " " + age + " " + email);
            String username = SharedPreferencesUtils.getParam(LoginActivity.this, "username", "").toString();
            if (nickname.equals("")) {
                Toast.makeText(LoginActivity.this, "昵称不能为空!", Toast.LENGTH_SHORT).show();
            } else if (sex.equals("请选择")) {
                Toast.makeText(LoginActivity.this, "请选择性别!", Toast.LENGTH_SHORT).show();
            } else if (age.equals("")) {
                Toast.makeText(LoginActivity.this, "年龄不能为空!", Toast.LENGTH_SHORT).show();
            } else if (email.equals("")) {
                Toast.makeText(LoginActivity.this, "邮箱不能为空!", Toast.LENGTH_SHORT).show();
            } else {
                result = true;
                dbManager.dbUpdateUserInfo(username, nickname, sex, age, email);
            }
            return result;
        }

        @JavascriptInterface
        //获取书籍列表
        public String getBookList() {
            return dbManager.dbGetBookList();
        }

        @JavascriptInterface
        //根据搜索关键词获取书籍列表
        public String getSearchBookList(String searchKey) {
            return dbManager.dbGetSearchBookList(searchKey);
        }

        @JavascriptInterface
        //获取指定书籍信息
        public String getBookInfo(String bookEnglishName) {
            return dbManager.dbGetBookInfo(bookEnglishName);
        }

        @JavascriptInterface
        //判断用户是否收藏过某本书
        public boolean isCollected(String bookEnglishName) {
            String username = SharedPreferencesUtils.getParam(LoginActivity.this, "username", "").toString();
            return dbManager.dbIsCollected(username, bookEnglishName);
        }

        @JavascriptInterface
        //删除用户收藏书籍记录
        public void deleteCollect(String bookEnglishName) {
            String username = SharedPreferencesUtils.getParam(LoginActivity.this, "username", "").toString();
            dbManager.dbDeleteCollect(username, bookEnglishName);
            Toast.makeText(LoginActivity.this, "取消收藏成功！", Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        //向用户收藏书籍表插入信息
        public void insertIntoUserCollect(String bookName, String bookEnglishName) {
            String username = SharedPreferencesUtils.getParam(LoginActivity.this, "username", "").toString();
            dbManager.dbInsertIntoUserCollect(username, bookName, bookEnglishName);
            Toast.makeText(LoginActivity.this, "收藏成功！", Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        //获取指定书籍的章节
        public String getBookChapter(String bookEnglishName) {
            return dbManager.dbGetBookChapter(bookEnglishName);
        }

        @JavascriptInterface
        //根据书籍英文名获取书籍的中文名
        public String getBookName(String bookEnglishName) {
            return dbManager.dbGetBookName(bookEnglishName);
        }

        @JavascriptInterface
        //根据书籍英文名，章节id获取书籍的章节名，章节内容
        public String getChapterInfo(String bookEnglishName, String chapterId) {
            return dbManager.dbGetChapterInfo(bookEnglishName, chapterId);
        }

        @JavascriptInterface
        //根据书籍英文名，章节id获取问题列表
        public String getChapterQuestion(String bookEnglishName, String chapterId) {
            return dbManager.dbGetChapterQuestion(bookEnglishName, chapterId);
        }

        @JavascriptInterface
        //判断用户是否回答过该章节的问题
        public boolean isAnsweredChapter(String bookEnglishName, String chapterId) {
            String username = SharedPreferencesUtils.getParam(LoginActivity.this, "username", "").toString();
            return dbManager.dbIsAnsweredChapter(username, bookEnglishName, chapterId);
        }

        @JavascriptInterface
        //根据书籍英文名，章节id插入回答问题信息
        public void insertIntoAnswerTable(String bookEnglishName, String chapterId) {
            String username = SharedPreferencesUtils.getParam(LoginActivity.this, "username", "").toString();
            dbManager.dbInsertIntoAnswerTable(username, bookEnglishName, chapterId);
        }

        @JavascriptInterface
        //获取用户收藏书籍列表
        public String getUserCollectBook() {
            String username = SharedPreferencesUtils.getParam(LoginActivity.this, "username", "").toString();
            return dbManager.dbGetUserCollectBook(username);
        }

        @JavascriptInterface
        //更新用户的总积分跟今日积分
        public void updateUserIntegration(String allIntegration, String todayIntegration) {
            String username = SharedPreferencesUtils.getParam(LoginActivity.this, "username", "").toString();
            dbManager.dbUpdateUserIntegration(username, allIntegration, todayIntegration);
        }

    }

    //按返回建不退出，保持后台运行
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keyCode, event);
    }
}
