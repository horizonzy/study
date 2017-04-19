package com.example.zhaoyan.asyntask;

import android.widget.ImageView;

/**
 * Created by zhaoyan on 2017/3/21.
 */

public class JavaBean {
    public String mIconUrl;
    public String mContent;
    public String mTitle;

    public JavaBean(String iconUrl, String mContent, String mTitle) {
        this.mIconUrl = iconUrl;
        this.mContent = mContent;
        this.mTitle = mTitle;
    }
}
