package com.example.zhaoyan.automan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.zhaoyan.automan.com.example.zhaoyan.bean.ChatMessage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyan on 2017/3/25.
 */

public class ChatMessageService {
    private Context mContext;
    public ChatMessageService(Context context){

        this.mContext=context;
    }
    public void saveMessage(ChatMessage chatMessage){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(chatMessage);
            objectOutputStream.flush();
            byte [] data=byteArrayOutputStream.toByteArray();
            objectOutputStream.close();
            byteArrayOutputStream.close();
            DbHelper dbhelper=DbHelper.getDbhelper(mContext);
            SQLiteDatabase db=dbhelper.getWritableDatabase();
            db.execSQL("insert into chatmessage (chatmessageclass) values(?)",new Object[]{data});
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<ChatMessage> getDatas(){
        List<ChatMessage> mData=new ArrayList<>();
        ChatMessage chatMessage=null;
        DbHelper dbhelper=DbHelper.getDbhelper(mContext);
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from (select * from chatmessage order by _id desc limit 0,10) order by _id asc",null);
        if(cursor!=null){
            while(cursor.moveToNext()){
                byte [] data=cursor.getBlob(cursor.getColumnIndex("chatmessageclass"));
                ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(data);
                try {
                    ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
                    chatMessage= (ChatMessage) objectInputStream.readObject();
                    mData.add(chatMessage);
                    objectInputStream.close();
                    byteArrayInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        db.close();
        return mData;
    }
}
