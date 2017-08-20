package com.fs.service;

import com.fs.po.Tclass;

import java.util.List;

public interface TclassServiceSupport {
    List<Tclass> getAllTclass();
    int getTclassidByName(String name);
}
