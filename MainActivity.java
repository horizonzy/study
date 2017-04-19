package com.example.zhaoyan.asyntask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RefreshListView.IReflashListener{
     public static final String url="http://www.imooc.com/api/teacher?type=4&num=30";
     private RefreshListView lv;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        new MyAsyncTask().execute(url);
    }

    private void initView() {
        lv= (RefreshListView) findViewById(R.id.listview);
        lv.setInterface(this);
    }

    @Override
    public void onReflash() {

    }

    class MyAsyncTask extends AsyncTask<String,Void,List<JavaBean>>{

       @Override
       protected List<JavaBean> doInBackground(String... params) {
           return getJsonData(params[0]);
       }

       @Override
       protected void onPreExecute() {
           super.onPreExecute();
       }

       @Override
       protected void onPostExecute(List<JavaBean> javaBeen) {
            MyAdpter adapter=new MyAdpter(MainActivity.this,javaBeen);
           lv.setAdapter(adapter);
           super.onPostExecute(javaBeen);
       }
   }

    private List<JavaBean> getJsonData(String url) {
        List<JavaBean> jbs=new ArrayList<>();
        JavaBean javabean;
        String jsonString=getJsonString(url);
        try {
            JSONObject jsonObject=new JSONObject(jsonString);
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            for(int i=0;i<jsonArray.length();i++){
                  jsonObject = (JSONObject) jsonArray.get(i);
                 String iconUrl=jsonObject.getString("picSmall");
                 String title=jsonObject.getString("name");
                 String content=jsonObject.getString("description");
                javabean=new JavaBean(iconUrl,title,content);
                 jbs.add(javabean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jbs;
    }

    private String getJsonString(String url) {
        String result="";
        try {
            URL httpUrl=new URL(url);
            HttpURLConnection con= (HttpURLConnection) httpUrl.openConnection();
            BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
            String str;
            while((str=br.readLine())!=null){
                 result=str;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
