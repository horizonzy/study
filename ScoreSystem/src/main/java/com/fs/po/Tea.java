package com.fs.po;

public class Tea {
    private int id;
    private String name;
    private int process;
    private String num;
    private String pwd;

    @Override
    public String toString() {
        return "Tea{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", process=" + process +
                ", num='" + num + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public Tea(int id, String name, int process, String num, String pwd) {
        this.id = id;
        this.name = name;
        this.process = process;
        this.num = num;
        this.pwd = pwd;
    }

    public Tea() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
