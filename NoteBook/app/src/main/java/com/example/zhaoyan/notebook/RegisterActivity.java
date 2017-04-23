package com.example.zhaoyan.notebook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by zhaoyan on 2017/4/21.
 */

public class RegisterActivity extends AppCompatActivity {
    public static final String APP_ID="de57abde39fedea5252f8a8374b741b7";
    private EditText et_count,et_name,et_password,et_check_password;
    private Button btn_check_yes;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_view);
        ExitActivity.getInstance().addActivity(this);
        Bmob.initialize(RegisterActivity.this,APP_ID);
        initView();
        initEvent();


    }

    private void initEvent() {
        btn_check_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String count=et_count.getText()+"";
                String name=et_name.getText()+"";
                String password=et_password.getText()+"";
                String check_password=et_check_password.getText()+"";
                if(check_password.equals(password)){
                    MyUser myUser=new MyUser();
                    myUser.setUsername(count);
                    myUser.setPassword(password);
                    myUser.setName(name);
                    myUser.signUp(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            if(e==null){
                                Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Toast.makeText(RegisterActivity.this,"该账号已被人注册",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else {
                    Toast.makeText(RegisterActivity.this,"你两次输入的密码不同，请重新输入",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        et_count= (EditText) findViewById(R.id.regste_et_count);
        et_name= (EditText) findViewById(R.id.regste_et_name);
        et_password= (EditText) findViewById(R.id.regste_et_password);
        et_check_password= (EditText) findViewById(R.id.et_check_password);
        btn_check_yes= (Button) findViewById(R.id.btn_registe_yes);
    }
}
