package com.example.cjb.myapplication.entity;

public class user {
    private String usernmae;//用户名
    private String password;//密码
    private String nickname;//昵称
    private int allIntegration;//总积分
    private int todayIntegration;//今日积分
    private String timing;//定时时间
    private String faceImage;//头像

    public user() {
    }

    public user(String usernmae, String password, String nickname, int allIntegration, int todayIntegration, String timing, String faceImage) {
        this.usernmae = usernmae;
        this.password = password;
        this.nickname = nickname;
        this.allIntegration = allIntegration;
        this.todayIntegration = todayIntegration;
        this.timing = timing;
        this.faceImage = faceImage;
    }

    public String getUsernmae() {
        return usernmae;
    }

    public void setUsernmae(String usernmae) {
        this.usernmae = usernmae;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAllIntegration() {
        return allIntegration;
    }

    public void setAllIntegration(int allIntegration) {
        this.allIntegration = allIntegration;
    }

    public int getTodayIntegration() {
        return todayIntegration;
    }

    public void setTodayIntegration(int todayIntegration) {
        this.todayIntegration = todayIntegration;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }
}
