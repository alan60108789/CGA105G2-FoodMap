package com.emp.model.Root.dao;

import com.emp.model.Root.pojo.Root;

import java.util.List;




public interface RootDAO_interface {
    public List<Root> findByROOT_TEXT(String ROOT_TEXT);
    public List<Root> getAll();
    public Root findByRootId(Integer integer);

}
