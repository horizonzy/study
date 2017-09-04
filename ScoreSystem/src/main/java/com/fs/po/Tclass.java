package com.fs.po;

import java.util.List;

public class Tclass {
    private int id;
    private String name;
    private String major;
    private String t_name;
    private List<Stu> stuList;

    @Override
    public String toString() {
        return "Tclass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", t_name='" + t_name + '\'' +
                ", stuList=" + stuList +
                '}';
    }

    public Tclass() {
    }

    public Tclass(int id, String name, String major, String t_name, List<Stu> stuList) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.t_name = t_name;
        this.stuList = stuList;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public List<Stu> getStuList() {
        return stuList;
    }

    public void setStuList(List<Stu> stuList) {
        this.stuList = stuList;
    }
}
