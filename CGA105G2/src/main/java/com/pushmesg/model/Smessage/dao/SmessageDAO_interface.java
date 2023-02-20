package com.pushmesg.model.Smessage.dao;

import com.pushmesg.model.Smessage.pojo.Smessage;

import java.util.List;

public interface SmessageDAO_interface {
	// 文字框發送給所有訂閱會員 => insert 
	public void insert(Smessage smessageVO);
	// 會員查詢 用會員編號 查訂閱表 得到訂閱id及店家id *再拿訂閱id去查推播內容* 合併 拿店家id去查店名
	public List<Smessage> getById(Integer SUB_ID);

}
