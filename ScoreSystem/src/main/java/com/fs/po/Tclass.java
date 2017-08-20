package com.fs.po;

import java.util.List;

public class Tclass {
    private int id;
    private String name;
    private String major;
    private List<Stu> stuList;

    @Override
    public String toString() {
        return "Tclass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", stuList=" + stuList +
                '}';
    }

    public Tclass(int id, String name, String major, List<Stu> stuList) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.stuList = stuList;
    }

    public Tclass() {
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

    public List<Stu> getStuList() {
        return stuList;
    }

    public void setStuList(List<Stu> stuList) {
        this.stuList = stuList;
    }
}
