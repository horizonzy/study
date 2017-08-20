package com.fs.po;

public class Stu {
    private int id;
    private String name;
    private String sex;
    private String num;
    private String pwd;
    private Tclass tclass;

    public Stu(int id, String name, String sex, String num, String pwd, Tclass tclass) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.num = num;
        this.pwd = pwd;
        this.tclass = tclass;
    }

    public Stu() {
    }

    @Override
    public String toString() {
        return "Stu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", num='" + num + '\'' +
                ", pwd='" + pwd + '\'' +
                ", tclass=" + tclass +
                '}';
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public Tclass getTclass() {
        return tclass;
    }

    public void setTclass(Tclass tclass) {
        this.tclass = tclass;
    }
}
