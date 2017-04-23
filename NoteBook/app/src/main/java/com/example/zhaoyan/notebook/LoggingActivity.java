package com.example.zhaoyan.notebook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by zhaoyan on 2017/4/21.
 */

public class LoggingActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText log_count,log_password;
    private Button btn_login,btn_registe;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        ExitActivity.getInstance().addActivity(this);
        Bmob.initialize(this,RegisterActivity.APP_ID);
        initView();
        initEvent();
    }

    private void initEvent() {
        btn_registe.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    private void initView() {
        log_count= (EditText) findViewById(R.id.log_et_count);
        log_password= (EditText) findViewById(R.id.log_et_password);
        btn_login= (Button) findViewById(R.id.btn_login);
        btn_registe= (Button) findViewById(R.id.btn_register);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
               final String count=log_count.getText()+"";
               final String password=log_password.getText()+"";
                BmobUser b=new BmobUser();
                b.loginByAccount(count, password, new LogInListener<MyUser>() {
                    @Override
                    public void done(MyUser myUser, BmobException e) {
                        if(e==null){
                            Toast.makeText(LoggingActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoggingActivity.this,MainActivity.class));
                        }else {
                            Toast.makeText(LoggingActivity.this, "密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.btn_register:
                startActivity(new Intent(LoggingActivity.this,RegisterActivity.class));
                break;
        }
    }
}
