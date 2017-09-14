package com.example.zhaoyan.automan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zhaoyan.automan.com.example.zhaoyan.bean.ChatMessage;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by zhaoyan on 2017/3/22.
 */

public class ChatMessageAdapter extends BaseAdapter {
    private List<ChatMessage> mDatas;
    private LayoutInflater mInflater;
    public ChatMessageAdapter(Context context,List<ChatMessage> mDatas){
        this.mDatas=mDatas;
        mInflater=LayoutInflater.from(context);
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
            if(getItemViewType(position)==0){
                convertView=mInflater.inflate(R.layout.item_from_msg,parent,false);
                viewHolder=new ViewHolder();
                viewHolder.mData= (TextView) convertView.findViewById(R.id.from_msg_data);
                viewHolder.mMsg= (TextView) convertView.findViewById(R.id.from_msg_info);
            }else {
                convertView=mInflater.inflate(R.layout.item_to_msg,parent,false);
                viewHolder=new ViewHolder();
                viewHolder.mMsg= (TextView) convertView.findViewById(R.id.to_msg_info);
                viewHolder.mData= (TextView) convertView.findViewById(R.id.to_msg_data);
            }
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        ChatMessage chatMessage=mDatas.get(position);
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        viewHolder.mData.setText(df.format(chatMessage.getData()));
        viewHolder.mMsg.setText(chatMessage.getMsg());

        return convertView;
    }
    private class ViewHolder{
        TextView mData;
        TextView mMsg;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        //0为接受消息，1为发送消息
         ChatMessage chatMessage=mDatas.get(position);
         if(chatMessage.getType()== ChatMessage.Type.INCOMING){
             return 0;
         }else {
             return 1;
         }
    }
}
