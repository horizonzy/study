package com.example.zhaoyan.notebook;

import cn.bmob.v3.BmobUser;

/**
 * Created by zhaoyan on 2017/4/23.
 */

public class MyUser extends BmobUser {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
