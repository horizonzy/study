package com.example.zhaoyan.automan.com.example.zhaoyan.utils;

import com.example.zhaoyan.automan.com.example.zhaoyan.bean.ChatMessage;
import com.example.zhaoyan.automan.com.example.zhaoyan.bean.Result;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by zhaoyan on 2017/3/22.
 */

public class HttpUtils {
    private static final String URL="http://www.tuling123.com/openapi/api";
    private static final String API_KEY="ae9a0b45b5634619b9dea4f0b7dd176d";

    public static ChatMessage sendMessage(String msg){
        ChatMessage chatMessage=new ChatMessage();
         String jsonString=doGet(msg);
        Gson gson=new Gson();
        Result result;
        try {
           result=gson.fromJson(jsonString, Result.class);
            chatMessage.setMsg(result.getText());
        } catch (JsonSyntaxException e) {
             chatMessage.setMsg("服务器繁忙，请稍后再试");
        }
        chatMessage.setData(new Date());
        chatMessage.setType(ChatMessage.Type.INCOMING);

        return chatMessage;
    }
    /*
    * 得到机器人返回的消息
    * */
    public static String doGet(String  msg) {
        InputStream is=null;
        String result = null;
        String url=setParams(msg);
        ByteArrayOutputStream baos=null;
        try {
            java.net.URL urlStr=new URL(url);
            HttpURLConnection conn= (HttpURLConnection) urlStr.openConnection();
            is=conn.getInputStream();
            int len;
            byte [] buf=new byte[128];
            baos=new ByteArrayOutputStream();
            while ((len=is.read(buf))!=-1){
                baos.write(buf,0,len);
            }
            baos.flush();
            result=new String(baos.toByteArray());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
          if(baos!=null){
              try {
                  baos.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
          if (is!=null){
              try {
                  is.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
        }
      return result;
    }

    private static String setParams(String msg) {
        String url= null;
        try {
            url = URL+"?key="+API_KEY+"&info="+ URLEncoder.encode(msg,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }


}
