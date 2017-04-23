package com.example.zhaoyan.notebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by zhaoyan on 2017/4/21.
 */

public class HabitAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;
    private List<HabitData> mDatas;

    public HabitAdapter(Context context,List<HabitData> mDatas) {
        this.mDatas=mDatas;
        this.mContext=context;
        this.mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.habit_view,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_isopen= (TextView) convertView.findViewById(R.id.tv_isOpen);
            viewHolder.tv_notifytime= (TextView) convertView.findViewById(R.id.tv_notifytime);
            viewHolder.tv_insistsday= (TextView) convertView.findViewById(R.id.tv_insistday);
            viewHolder.tv_signday= (TextView) convertView.findViewById(R.id.tv_signupday);
            viewHolder.tv_builddate= (TextView) convertView.findViewById(R.id.tv_builddate);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_title.setText(mDatas.get(position).getTitle());
        if(mDatas.get(position).isOpen()){
        viewHolder.tv_isopen.setText("是否对好友可见:是");
        }else {
            viewHolder.tv_isopen.setText("是否对好友可见:否");
        }
        viewHolder.tv_notifytime.setText("提醒时间："+mDatas.get(position).getNotifyTime());
        viewHolder.tv_insistsday.setText("连续坚持"+mDatas.get(position).getInsistDay()+"天");
        viewHolder.tv_signday.setText("总共签到"+mDatas.get(position).getSignin()+"天");
        viewHolder.tv_builddate.setText("添加时间："+mDatas.get(position).getBuildDate());
        return convertView;
    }
    class ViewHolder{
        public TextView tv_title,tv_isopen,tv_notifytime,tv_insistsday,tv_signday,tv_builddate;
    }
}
