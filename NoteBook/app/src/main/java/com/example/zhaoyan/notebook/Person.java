package com.example.zhaoyan.notebook;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by zhaoyan on 2017/4/21.
 */

public class Person extends BmobObject{
    public Person(String count, String name, String password) {
        this.count = count;
        this.name = name;
        this.password = password;
    }
    public Person(){

    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String count;
    public String name;
    public String password;

}
