package com.example.zhaoyan.automan.com.example.zhaoyan.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by zhaoyan on 2017/3/22.
 */

public class ChatMessage implements Serializable{
    public ChatMessage(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    private String name;
    private String msg;
    private Type type;
    private Date data;
    public enum  Type{
        INCOMING,OUTCONMING
    }

    public ChatMessage(String msg, Type type, Date data) {
        this.msg = msg;
        this.type = type;
        this.data = data;
    }
}
