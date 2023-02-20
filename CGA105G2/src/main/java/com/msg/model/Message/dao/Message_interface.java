package com.msg.model.Message.dao;

import com.msg.model.Message.pojo.Message;

import java.util.List;



public interface Message_interface {
    public void insert(Message Message);
    public Message getByFollowId(Integer followId);
    public List<Message> getAllByFollowId(Integer followId);
    public List<Message> getAll();
    public void delete(Integer followId);
}
