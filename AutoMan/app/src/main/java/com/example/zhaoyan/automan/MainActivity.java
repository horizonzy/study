package com.example.zhaoyan.automan;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zhaoyan.automan.com.example.zhaoyan.bean.ChatMessage;
import com.example.zhaoyan.automan.com.example.zhaoyan.utils.HttpUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ChatMessageService chatMessageService;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ChatMessage fromMessage= (ChatMessage) msg.obj;
            mDatas.add(fromMessage);
            mAdapter.notifyDataSetChanged();
            mListView.smoothScrollToPosition(mListView.getCount()-1);

        }
    };
    private ListView mListView;
    private List<ChatMessage> mDatas;
    private EditText mInputMessage;
    private Button mSendButton;
    private ChatMessageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for(int i=0;i<mDatas.size();i++){
            ChatMessage chatMessage=mDatas.get(i);
            chatMessageService.saveMessage(chatMessage);
        }

    }

    private void initListener() {
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String toMsg=mInputMessage.getText().toString();
                if(TextUtils.isEmpty(toMsg)){
                    Toast.makeText(MainActivity.this,"发送消息不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    ChatMessage toMessage=new ChatMessage(toMsg, ChatMessage.Type.OUTCONMING,new Date());
                    mDatas.add(toMessage);
                    mInputMessage.setText("");
                    InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mInputMessage.getWindowToken(), 0);
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            ChatMessage fromMessage=HttpUtils.sendMessage(toMsg);
                            Message message=Message.obtain();
                            message.obj=fromMessage;
                            mHandler.sendMessage(message);
                        }
                    }.start();
                }
            }
        });
    }

    private void initData() {
        mDatas=new ArrayList<>();
        mDatas=chatMessageService.getDatas();
        mAdapter=new ChatMessageAdapter(MainActivity.this,mDatas);
        mListView.setAdapter(mAdapter);
    }

    private void initView() {
        chatMessageService=new ChatMessageService(MainActivity.this);
        mListView= (ListView) findViewById(R.id.listview);
        mInputMessage= (EditText) findViewById(R.id.input_msg);
        mSendButton= (Button) findViewById(R.id.btn_send_msg);
    }
}
