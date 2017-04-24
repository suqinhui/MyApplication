package com.example.cjb.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "kbook.db";// 数据库名称
    private static final int DATABASE_VERSION = 1;// 数据库版本

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override//当初次创建数据库的时候调用，一般把建库、建表的操作
    public void onCreate(SQLiteDatabase db) {
        //书籍表
        db.execSQL("create table book_table(book_name varchar(20) PRIMARY KEY not null,author varchar(20) not null,introduction text not null,years int not null,image varchar(255) not null)");
        //章节表
        db.execSQL("create table book_chapter(book_name varchar(20) PRIMARY KEY not null,chapter_name varchar(20) not null,chapter_id int not null,content text not null,question_id int not null)");
        //问题表
        db.execSQL("create table question(question_id int PRIMARY KEY not null,question_content text not null,answer_a varchar(100) not null,answer_b varchar(100) not null,answer_c varchar(100) not null,answer_d varchar(100) not null,right_answer varchar(100) not null)");
        //用户表
        db.execSQL("create table account(username varchar(20) PRIMARY KEY not null,password varchar(20) not null,nickname varchar(20) not null,all_integration int not null,today_integration int not null,timing varchar(20) not null,face_image varchar(255) not null)");
        //插入一个管理员用户
        db.execSQL("insert into account(username,password,nickname,all_integration,today_integration,timing,face_image)values('admin','admin','管理员',0,0,'0','0')");
    }

    @Override//当数据库版本发生变化时会自动执行
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
