package com.example.zhaoyan.notebook;

import android.content.DialogInterface;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoyan on 2017/4/21.
 */

public class FirstFragment extends Fragment implements View.OnClickListener{
    private ListView listView;
    private List<HabitData> mDatas=new ArrayList<>();
    private Button btn_add_note,btn_seehabit;
    private HabitAdapter habitAdapter;
    private int a=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab1,container,false);

        listView= (ListView) view.findViewById(R.id.listview1);
        btn_add_note= (Button) view.findViewById(R.id.btn_add_note);
        btn_add_note.setOnClickListener(this);
        habitAdapter=new HabitAdapter(getContext(),mDatas);
        listView.setAdapter(habitAdapter);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_note:
                LayoutInflater inflater=LayoutInflater.from(getContext());
                View view=inflater.inflate(R.layout.dialog_view,null);
                final EditText et_habitname= (EditText) view.findViewById(R.id.et_habit_name);
                final CheckBox checkBox= (CheckBox) view.findViewById(R.id.checkbox);
                final TimePicker timePicker= (TimePicker) view.findViewById(R.id.timePicker);
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("添加习惯");
                builder.setView(view);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title=et_habitname.getText()+"";
                        boolean isOpen=false;
                        if(checkBox.isChecked()){
                         isOpen=true;
                        }else {
                            isOpen=false;
                        }
                        String time=timePicker.getHour()+":"+timePicker.getMinute();
                        int insistday=1;
                        int signupday=1;
                        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        Date date=new Date();
                        String builddate=format.format(date);
                        HabitData habitData=new HabitData(title,isOpen,time,insistday,signupday,builddate);
                        mDatas.add(habitData);
                        habitAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.create().show();
                break;

        }
    }
}
