package com.fs.service;

import java.util.List;

import com.fs.po.lx_Kind;

public interface lx_ContentServiceSupport {
    public List<lx_Kind> kindsService();
    public List<Integer> getKindNum(); 
    public List<String > getContentByKindId(int id);
}
