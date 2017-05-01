package com.example.cjb.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.cjb.myapplication.R;
import com.example.cjb.myapplication.db.DBManager;
import com.example.cjb.myapplication.util.SharedPreferencesUtils;

public class LoginActivity extends AppCompatActivity {
    private WebView webView;
    private WebSettings webSettings;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvent();
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.webView);
        webSettings = webView.getSettings();
        dbManager = new DBManager(this);
    }

    @SuppressLint("JavascriptInterface")
    private void initEvent() {
        webSettings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/base/login.html");
        ////设置本地调用对象及其接口
        webView.addJavascriptInterface(new JsInteraction(), "JsInteractionLoginEvent");
        //设置不用系统浏览器打开,直接显示在当前Webview
        webView.setWebViewClient(new WebViewClient() {
        });
    }

    //定义用来跟js交互的类
    private class JsInteraction {
        @JavascriptInterface
        //登录检验
        public void loginCheck(String username, String password) {
            if (username.equals("") || password.equals("")) {
                Toast.makeText(LoginActivity.this, "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
            } else {
                if (dbManager.dbLoginCheck(username, password)) {
                    SharedPreferencesUtils.setParam(LoginActivity.this, "username", username);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @JavascriptInterface
        //注册
        public boolean doRegister(String username, String nickname, String password, String password1) {
            boolean registerResult = false;
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
    }
}
