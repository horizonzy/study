package com.fs.dao;

import java.util.List;

import com.fs.po.lx_Content;
import com.fs.po.lx_Kind;

public interface lx_ContentDao {
    public List<lx_Kind> selectallKind();
    public List<lx_Content> selectallmycontent();
    public List<Integer> selectNumByKind();
    public List<String> selectContentByKindId(int content_id);
   
}
