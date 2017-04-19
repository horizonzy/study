package com.example.zhaoyan.asyntask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhaoyan on 2017/3/22.
 */

public class ImageLoader extends Thread {
  private ImageView iv;
    private String url;
    private LruCache<String,Bitmap> mCache;
    public ImageLoader(){
        int maxMemory= (int) Runtime.getRuntime().maxMemory();
        int cacheSize=maxMemory/4;
        mCache=new LruCache<String,Bitmap>(maxMemory){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }
    public Bitmap getBitmaoFromCache(String url){
        return mCache.get(url);
    }
    public void addBitmpToCache(String url,Bitmap bitmap){
         if(getBitmaoFromCache(url)==null){
             mCache.put(url,bitmap);
         }
    }
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bitmap bitmap= (Bitmap) msg.obj;
            if(iv.getTag().equals(url))
            iv.setImageBitmap(bitmap);
        }
    };

    public void showImageByThread(final String url, ImageView iv){
          this.iv=iv;
        this.url=url;
       new Thread(){
           @Override
           public void run() {
               super.run();
               final Bitmap bitmap=getBitmapFromUrl(url);
               Message m=Message.obtain();
               m.obj=bitmap;
               mHandler.sendMessage(m);
           }
       }.start();
    }
    public Bitmap getBitmapFromUrl(String url){
        Bitmap bitmap=null;
        InputStream is=null;
        try {
            URL httpUrl=new URL(url);
            HttpURLConnection connection= (HttpURLConnection) httpUrl.openConnection();
             is=connection.getInputStream();
             bitmap= BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public void showImagebyAsyncTask(String url,ImageView iv)   {
          Bitmap bitmap=getBitmaoFromCache(url);
        if(bitmap==null) {
            new LoadImage().execute(url);
        }else {
            if(iv.getTag().equals(url))
            iv.setImageBitmap(bitmap);
        }
    }
    class LoadImage extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap=getBitmapFromUrl(params[0]);
            if(bitmap!=null){
                addBitmpToCache(params[0],bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }
    }
}

