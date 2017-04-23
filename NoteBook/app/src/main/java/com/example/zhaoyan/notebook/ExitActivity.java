package com.example.zhaoyan.notebook;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyan on 2017/4/21.
 */

public class ExitActivity {
    private List<Activity> list=new ArrayList<Activity>();
    private static ExitActivity ea;
    private ExitActivity(){

    }
    public static ExitActivity getInstance(){
        if(ea==null){
            ea=new ExitActivity();
        }
        return ea;
    }
    public void addActivity(Activity activity){
        list.add(activity);
    }
    public void exit(){
        for (Activity activity:list) {
            activity.finish();
        }
        System.exit(0);
    }
}
