package com.example.cjb.myapplication.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cjb.myapplication.activity.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class DBManager {
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    //用户注册，往用户表中插入数据
    public boolean dbUserRegister(String username, String password, String nickname) {
        Cursor cursor = db.rawQuery("select * from account where username='" + username + "'", null);
        if (cursor.getCount() == 0) {
            db.execSQL("insert into account(username,password,nickname,all_integration,today_integration,timing,face_image)values('" + username + "','" + password + "','" + nickname + "',0,0,'0','0')");
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }

    }

    //登录判断
    public boolean dbLoginCheck(String username, String password) {
        Cursor cursor = db.rawQuery("select * from account where username='" + username + "' and password='" + password + "'", null);
        if (cursor.getCount() == 0) {
            cursor.close();
            return false;
        } else {
            cursor.close();
            return true;
        }
    }

    //获取用户昵称
    public String dbGetNickName(String username) {
        String nickname = "";
        Cursor cursor = db.rawQuery("select * from account where username='" + username + "'", null);
        while (cursor.moveToNext()) {
            nickname = cursor.getString(cursor.getColumnIndex("nickname"));
        }
        cursor.close();
        return nickname;
    }

    //获取用户信息
    public String dbGetUserInfo(String username) {
        Cursor cursor = db.rawQuery("select * from account where username='" + username + "'", null);
        JSONObject userObject = new JSONObject();
        while (cursor.moveToNext()) {
            try {
                userObject.put("username", cursor.getString(cursor.getColumnIndex("username")));
                userObject.put("password", cursor.getString(cursor.getColumnIndex("password")));
                userObject.put("nickname", cursor.getString(cursor.getColumnIndex("nickname")));
                userObject.put("all_integration", cursor.getInt(cursor.getColumnIndex("all_integration")));
                userObject.put("today_integration", cursor.getInt(cursor.getColumnIndex("today_integration")));
                userObject.put("timing", cursor.getString(cursor.getColumnIndex("timing")));
                userObject.put("face_image", cursor.getString(cursor.getColumnIndex("face_image")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return userObject.toString();
    }
}
