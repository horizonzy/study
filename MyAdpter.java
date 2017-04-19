package com.example.zhaoyan.asyntask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhaoyan on 2017/3/21.
 */

public class MyAdpter extends BaseAdapter {
    private List<JavaBean> jbs;
    LayoutInflater inflater;
    ImageLoader imageLoader;
    public MyAdpter(Context context,List<JavaBean> jbs){
        this.jbs=jbs;
        inflater=LayoutInflater.from(context);
        imageLoader=new ImageLoader();

    }

    @Override
    public int getCount() {
        return jbs.size();
    }

    @Override
    public Object getItem(int position) {
        return jbs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.item,null);
            viewHolder.icon= (ImageView) convertView.findViewById(R.id.iv);
            viewHolder.title= (TextView) convertView.findViewById(R.id.title);
            viewHolder.content= (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        JavaBean javaBean=jbs.get(position);
        viewHolder.icon.setImageResource(R.mipmap.ic_launcher);
        viewHolder.icon.setTag(javaBean.mIconUrl);
//        new ImageLoader().showImageByThread(javaBean.mIconUrl,viewHolder.icon);
        imageLoader.showImagebyAsyncTask(javaBean.mIconUrl,viewHolder.icon);
        viewHolder.title.setText(javaBean.mTitle);
        viewHolder.content.setText(javaBean.mContent);
        return convertView;
    }
    class ViewHolder{
        public ImageView icon;
        public TextView  title,content;
    }
}
