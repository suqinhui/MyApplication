package com.example.cjb.myapplication.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.webkit.JavascriptInterface;

import com.example.cjb.myapplication.util.SharedPreferencesUtils;

import org.json.JSONArray;
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
            db.execSQL("insert into account(username,password,nickname,sex,age,email,all_integration,today_integration,timing,face_image)values('" + username + "','" + password + "','" + nickname + "','请选择','','','0','0','0','')");
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

    //获取用户信息
    public String dbGetUserInfo(String username) {
        Cursor cursor = db.rawQuery("select * from account where username='" + username + "'", null);
        JSONObject userObject = new JSONObject();
        while (cursor.moveToNext()) {
            try {
                userObject.put("username", cursor.getString(cursor.getColumnIndex("username")));
                userObject.put("password", cursor.getString(cursor.getColumnIndex("password")));
                userObject.put("nickname", cursor.getString(cursor.getColumnIndex("nickname")));
                userObject.put("sex", cursor.getString(cursor.getColumnIndex("sex")));
                userObject.put("age", cursor.getString(cursor.getColumnIndex("age")));
                userObject.put("email", cursor.getString(cursor.getColumnIndex("email")));
                userObject.put("all_integration", cursor.getString(cursor.getColumnIndex("all_integration")));
                userObject.put("today_integration", cursor.getString(cursor.getColumnIndex("today_integration")));
                userObject.put("timing", cursor.getString(cursor.getColumnIndex("timing")));
                userObject.put("face_image", cursor.getString(cursor.getColumnIndex("face_image")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        return userObject.toString();
    }

    //将设置的定时读书时间存到数据库
    public void dbUpdateTiming(String username, String time) {
        db.execSQL("update account set timing='" + time + "' where username='" + username + "'");
    }

    //修改个人信息
    public void dbUpdateUserInfo(String username, String nickname, String sex, String age, String email) {
        db.execSQL("update account set nickname='" + nickname + "',sex='" + sex + "',age='" + age + "',email='" + email + "' where username='" + username + "'");
    }

    //获取书籍列表
    public String dbGetBookList() {
        Cursor cursor = db.rawQuery("select * from book_table", null);
        JSONArray bookArray = new JSONArray();
        while (cursor.moveToNext()) {
            try {
                JSONObject bookObject = new JSONObject();
                bookObject.put("book_name", cursor.getString(cursor.getColumnIndex("book_name")));
                bookObject.put("book_english_name", cursor.getString(cursor.getColumnIndex("book_english_name")));
                bookObject.put("author", cursor.getString(cursor.getColumnIndex("author")));
                bookObject.put("introduction", cursor.getString(cursor.getColumnIndex("introduction")));
                bookObject.put("years", cursor.getString(cursor.getColumnIndex("years")));
                bookObject.put("image", cursor.getString(cursor.getColumnIndex("image")));
                bookArray.put(bookObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        return bookArray.toString();
    }

    //根据搜索关键词获取书籍列表
    public String dbGetSearchBookList(String searchKey) {
        Cursor cursor = db.rawQuery("select * from book_table where book_name like '%" + searchKey + "%'", null);
        JSONArray bookArray = new JSONArray();
        while (cursor.moveToNext()) {
            try {
                JSONObject bookObject = new JSONObject();
                bookObject.put("book_name", cursor.getString(cursor.getColumnIndex("book_name")));
                bookObject.put("book_english_name", cursor.getString(cursor.getColumnIndex("book_english_name")));
                bookObject.put("author", cursor.getString(cursor.getColumnIndex("author")));
                bookObject.put("introduction", cursor.getString(cursor.getColumnIndex("introduction")));
                bookObject.put("years", cursor.getString(cursor.getColumnIndex("years")));
                bookObject.put("image", cursor.getString(cursor.getColumnIndex("image")));
                bookArray.put(bookObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        return bookArray.toString();
    }

    @JavascriptInterface
    //判断用户是否收藏过某本书
    public boolean dbIsCollected(String username, String bookEnglishName) {
        Cursor cursor = db.rawQuery("select * from user_collect where username='" + username + "' and book_english_name='" + bookEnglishName + "'", null);
        if (cursor.getCount() == 0) {
            cursor.close();
            return false;
        } else {
            cursor.close();
            return true;
        }
    }

    //删除用户收藏书籍记录
    public void dbDeleteCollect(String username, String bookEnglishName) {
        db.execSQL("delete from user_collect where username='" + username + "' and book_english_name='" + bookEnglishName + "'");
    }

    //向用户收藏书籍表插入信息
    public void dbInsertIntoUserCollect(String username, String bookName, String bookEnglishName) {
        db.execSQL("insert into user_collect(username,book_name,book_english_name)values('" + username + "','" + bookName + "','" + bookEnglishName + "')");
    }

    //获取指定书籍信息
    public String dbGetBookInfo(String bookEnglishName) {
        Cursor cursor = db.rawQuery("select * from book_table where book_english_name='" + bookEnglishName + "'", null);
        JSONObject bookObject = new JSONObject();
        while (cursor.moveToNext()) {
            try {
                bookObject.put("book_name", cursor.getString(cursor.getColumnIndex("book_name")));
                bookObject.put("book_english_name", cursor.getString(cursor.getColumnIndex("book_english_name")));
                bookObject.put("author", cursor.getString(cursor.getColumnIndex("author")));
                bookObject.put("introduction", cursor.getString(cursor.getColumnIndex("introduction")));
                bookObject.put("years", cursor.getString(cursor.getColumnIndex("years")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        return bookObject.toString();
    }

    //获取指定书籍的章节
    public String dbGetBookChapter(String bookEnglishName) {
        Cursor cursor = db.rawQuery("select * from book_chapter where book_english_name='" + bookEnglishName + "'", null);
        JSONArray bookChapterArray = new JSONArray();
        while (cursor.moveToNext()) {
            try {
                JSONObject bookChapterObject = new JSONObject();
                bookChapterObject.put("book_english_name", cursor.getString(cursor.getColumnIndex("book_english_name")));
                bookChapterObject.put("chapter_name", cursor.getString(cursor.getColumnIndex("chapter_name")));
                bookChapterObject.put("chapter_id", cursor.getString(cursor.getColumnIndex("chapter_id")));
                bookChapterObject.put("content", cursor.getString(cursor.getColumnIndex("content")));
                bookChapterArray.put(bookChapterObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        return bookChapterArray.toString();
    }

    //根据书籍英文名获取书籍的中文名
    public String dbGetBookName(String bookEnglishName) {
        String bookName = "";
        Cursor cursor = db.rawQuery("select * from book_table where book_english_name='" + bookEnglishName + "'", null);
        while (cursor.moveToNext()) {
            bookName = cursor.getString(cursor.getColumnIndex("book_name"));
        }
        cursor.close();
        return bookName;
    }

    //根据书籍英文名获取书籍的章节名，章节内容
    public String dbGetChapterInfo(String bookEnglishName, String chapterId) {
        JSONObject chapterInfo = new JSONObject();
        Cursor cursor = db.rawQuery("select * from book_chapter where book_english_name='" + bookEnglishName + "' and chapter_id='" + chapterId + "'", null);
        while (cursor.moveToNext()) {
            try {
                chapterInfo.put("chapter_name", cursor.getString(cursor.getColumnIndex("chapter_name")));
                chapterInfo.put("content", cursor.getString(cursor.getColumnIndex("content")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        return chapterInfo.toString();
    }

    //根据书籍英文名，章节id获取问题列表
    public String dbGetChapterQuestion(String bookEnglishName, String chapterId) {
        Cursor cursor = db.rawQuery("select * from question where book_english_name='" + bookEnglishName + "' and chapter_id='" + chapterId + "'", null);
        JSONArray questionArray = new JSONArray();
        while (cursor.moveToNext()) {
            try {
                JSONObject questionObject = new JSONObject();
                questionObject.put("book_english_name", cursor.getString(cursor.getColumnIndex("book_english_name")));
                questionObject.put("chapter_id", cursor.getString(cursor.getColumnIndex("chapter_id")));
                questionObject.put("question_content", cursor.getString(cursor.getColumnIndex("question_content")));
                questionObject.put("answer_a", cursor.getString(cursor.getColumnIndex("answer_a")));
                questionObject.put("answer_b", cursor.getString(cursor.getColumnIndex("answer_b")));
                questionObject.put("answer_c", cursor.getString(cursor.getColumnIndex("answer_c")));
                questionObject.put("answer_d", cursor.getString(cursor.getColumnIndex("answer_d")));
                questionObject.put("right_answer", cursor.getString(cursor.getColumnIndex("right_answer")));
                questionArray.put(questionObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        return questionArray.toString();
    }

    //判断用户是否回答过该章节的问题
    public boolean dbIsAnsweredChapter(String username, String bookEnglishName, String chapterId) {
        Cursor cursor = db.rawQuery("select * from answer_table where username='" + username + "' and book_english_name='" + bookEnglishName + "' and chapter_id='" + chapterId + "'", null);
        if (cursor.getCount() == 0) {
            cursor.close();
            return false;
        } else {
            cursor.close();
            return true;
        }
    }

    //根据书籍英文名，章节id插入回答问题信息
    public void dbInsertIntoAnswerTable(String username, String bookEnglishName, String chapterId) {
        db.execSQL("insert into answer_table(username,book_english_name,chapter_id)values('" + username + "','" + bookEnglishName + "','" + chapterId + "')");
    }

    //获取用户收藏书籍列表
    public String dbGetUserCollectBook(String username) {
        Cursor cursor = db.rawQuery("select * from user_collect where username='" + username + "'", null);
        JSONArray bookCollectArray = new JSONArray();
        while (cursor.moveToNext()) {
            try {
                JSONObject bookCollectObject = new JSONObject();
                bookCollectObject.put("username", cursor.getString(cursor.getColumnIndex("username")));
                bookCollectObject.put("book_name", cursor.getString(cursor.getColumnIndex("book_name")));
                bookCollectObject.put("book_english_name", cursor.getString(cursor.getColumnIndex("book_english_name")));
                bookCollectArray.put(bookCollectObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        return bookCollectArray.toString();
    }


    //更新用户的总积分跟今日积分
    public void dbUpdateUserIntegration(String username, String allIntegration, String todayIntegration) {
        db.execSQL("update account set all_integration='" + allIntegration + "',today_integration='" + todayIntegration + "' where username='" + username + "'");
    }

    //清空指定用户的今日积分
    public void dbResetUserTodayIntegration() {
        db.execSQL("update account set today_integration='0'");
    }

    //插入笔记
    public boolean dbInsertIntoUserNote(String username, String bookEnglishName, String title, String content) {
        Cursor cursor = db.rawQuery("select * from user_note where username='" + username + "' and book_english_name='" + bookEnglishName + "' and note_title='" + title + "'", null);
        if (cursor.getCount() == 0) {
            db.execSQL("insert into user_note(username,book_english_name,note_title,note_content)values('" + username + "','" + bookEnglishName + "','" + title + "','" + content + "')");
            cursor.close();
            return false;
        } else {
            cursor.close();
            return true;
        }
    }

    //更新笔记
    public void dbUpdateUserNote(String username, String bookEnglishName, String title, String content) {
        Cursor cursor = db.rawQuery("select * from user_note where username='" + username + "' and book_english_name='" + bookEnglishName + "' and note_title='" + title + "'", null);
        if (cursor.getCount() == 0) {
            db.execSQL("insert into user_note(username,book_english_name,note_title,note_content)values('" + username + "','" + bookEnglishName + "','" + title + "','" + content + "')");
            cursor.close();
        } else {
            db.execSQL("update user_note set note_content='" + content + "' where username='" + username + "' and book_english_name='" + bookEnglishName + "' and note_title='" + title + "'");
            cursor.close();
        }
    }

    //获取用户的笔记书籍信息
    public String dbGetUserNoteList(String username) {
        Cursor cursor = db.rawQuery("select distinct book_english_name from user_note where username='" + username + "'", null);
        JSONArray userNoteArray = new JSONArray();
        while (cursor.moveToNext()) {
            try {
                JSONObject userNoteObject = new JSONObject();
                //先获取到书籍英文名，再到书籍表查询书籍信息
                String bookEnglishName = cursor.getString(cursor.getColumnIndex("book_english_name"));
                Cursor cursor1 = db.rawQuery("select * from book_table where book_english_name='" + bookEnglishName + "'", null);
                while (cursor1.moveToNext()) {
                    userNoteObject.put("book_name", cursor1.getString(cursor1.getColumnIndex("book_name")));
                    userNoteObject.put("book_english_name", cursor1.getString(cursor1.getColumnIndex("book_english_name")));
                    userNoteObject.put("author", cursor1.getString(cursor1.getColumnIndex("author")));
                    userNoteObject.put("years", cursor1.getString(cursor1.getColumnIndex("years")));
                }
                userNoteArray.put(userNoteObject);
                cursor1.close();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        return userNoteArray.toString();
    }

    //获取用户的指定书籍的所有笔记列表
    public String dbGetUserAllNoteList(String username, String bookEnglishName) {
        Cursor cursor = db.rawQuery("select * from user_note where username='" + username + "' and book_english_name='" + bookEnglishName + "'", null);
        JSONArray userAllNoteArray = new JSONArray();
        while (cursor.moveToNext()) {
            try {
                JSONObject userAllNoteObject = new JSONObject();
                userAllNoteObject.put("note_title", cursor.getString(cursor.getColumnIndex("note_title")));
                userAllNoteObject.put("note_content", cursor.getString(cursor.getColumnIndex("note_content")));
                userAllNoteObject.put("book_english_name", cursor.getString(cursor.getColumnIndex("book_english_name")));
                userAllNoteArray.put(userAllNoteObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        return userAllNoteArray.toString();
    }
}
