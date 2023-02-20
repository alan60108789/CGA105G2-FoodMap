package com.pushmesg.model.service;

import com.pushmesg.model.Smessage.dao.SmessageDAO_interface;
import com.pushmesg.model.Smessage.dao.impl.SmessageJDBCDAO;
import com.pushmesg.model.Smessage.pojo.Smessage;
import com.store.model.Store.dao.impl.StoreDAO;
import com.subs.model.Subscribe.dao.impl.SubscribeJDBCDAO;
import com.subs.model.Subscribe.pojo.Subscribe;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class pgService {
    private SmessageDAO_interface dao;

    public pgService() {
        dao = new SmessageJDBCDAO();
    }

    public Integer send(Integer storeId, String pgtext) {
        Smessage sg = new Smessage();
        sg.setSmessageTxet(pgtext);
        SubscribeJDBCDAO Subdao = new SubscribeJDBCDAO();
        List<Subscribe> subList = Subdao.getAllBystoreId(storeId);
        Integer comm = 0;
        if (subList.size() > 0) {
            for (Subscribe e : subList) {
                sg.setSubId(e.getSubId());
                dao.insert(sg);
                comm++;
            }
            return comm;
        } else {
            return -1;
        }
    }
    public Integer see(Integer memId) {
        SubscribeJDBCDAO sb = new SubscribeJDBCDAO();
        List<Subscribe> list = sb.getAllByMemId(memId);
        Integer n=0;
        for (Subscribe e : list) {
            Integer subid = e.getSubId();
            List<Smessage> slist = dao.getById(subid);
            if (slist.size() > 0) {
                for (Smessage i : slist) {
                    n++;
                }
            }
        }
        return n;
    }
    public JSONArray all(Integer memId) {
        JSONArray json = new JSONArray();
        JSONObject obj = null;
        SubscribeJDBCDAO sb = new SubscribeJDBCDAO();
        List<Subscribe> list = sb.getAllByMemId(memId);
        Integer n=0;
        for (Subscribe e : list) {
            Integer subid = e.getSubId();
            Integer sid = e.getStoreId();
            String sname = new StoreDAO().getById(sid).getStoreName();
            List<Smessage> slist = dao.getById(subid);
            if (slist.size() > 0) {
                for (Smessage i : slist) {
                    obj = new JSONObject();
                    String[] stext = i.getSmessageTxet().split(":");
                    obj.put("store",sname);
                    obj.put("pgid",i.getSmessageId());
                    obj.put("lab",stext[0]);
                    obj.put("text",stext[1]);
                    json.add(obj);
                    n++;
                }
            }
        }
        json.toJSONString();
        return json;
    }
}
