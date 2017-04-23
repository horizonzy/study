package com.example.zhaoyan.notebook;

import java.io.Serializable;
import java.util.Date;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;

/**
 * Created by zhaoyan on 2017/4/21.
 */

public class HabitData extends BmobObject{
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime;
    }

    public int getInsistDay() {
        return insistDay;
    }

    public void setInsistDay(int insistDay) {
        this.insistDay = insistDay;
    }

    public int getSignin() {
        return signin;
    }

    public void setSignin(int signin) {
        this.signin = signin;
    }

    public String getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(String buildDate) {
        this.buildDate = buildDate;
    }

    public String title;
    public boolean isOpen;
    public String notifyTime;
    public int insistDay;
    public int signin;
    public String buildDate;

    public HabitData(String title, boolean isOpen, String notifyTime, int insistDay, int signin, String buildDate) {
        this.title = title;
        this.isOpen = isOpen;
        this.notifyTime = notifyTime;
        this.insistDay = insistDay;
        this.signin = signin;
        this.buildDate = buildDate;
    }
}
