package com.code.model.service;


import com.code.model.Code.dao.CodeDAO_interface;
import com.code.model.Code.dao.impl.CodeHibernateDAO;
import com.code.model.Code.dao.impl.CodeJDBCDAO;
import com.code.model.Code.pojo.Code;
import com.code.model.MemCode.dao.impl.MemCodeJDBCDAO;
import com.code.model.MemCode.pojo.MemCode;
import com.emp.model.Employee.dao.impl.EmployeeJDBCDAO;
import com.emp.model.EmployeeRoot.dao.impl.EmployeeRootJDBCDAO;
import com.emp.model.EmployeeRoot.pojo.EmployeeRoot;
import com.member.model.Member.dao.impl.MemberDAO;
import com.store.model.Store.dao.impl.StoreDAO;
import com.subs.model.Subscribe.dao.impl.SubscribeJDBCDAO;
import com.subs.model.Subscribe.pojo.Subscribe;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CodeService {

    private CodeDAO_interface dao;

    public CodeService() {
//        dao =new CodeHibernateDAO();
        dao=new CodeJDBCDAO();
    }
    public Code todao(String codeNum,Integer codeOff,java.sql.Date codeNtime,String codeText){
        Code code =new Code();
        code.setCodeNum(codeNum);
        code.setCodeOff(codeOff);
        code.setCodeNtime(codeNtime);
        code.setCodeText(codeText);
        return code;
    };
    public void addEmp(Integer storeid, String codeNum,Integer codeOff,java.sql.Date codeNtime,
                       String codeText) {
        Code code=todao(codeNum,codeOff,codeNtime,codeText);
        code.setStoreId(storeid);
        dao.insert(code);
    }
    public JSONArray storeCodeAll(Integer storeid) {
        new CodeService();
        List<Code> list=dao.getBy(storeid,"店家");
        JSONArray json = new JSONArray( );
        JSONObject map=null;
        for(Code e : list){
            map=new JSONObject();
            map.put("CODE_ID", e.getCodeId());
            map.put("CODE_STATUS",e.getCodeStatus());
            map.put("CODE_OFF",e.getCodeOff());
            map.put("CODE_NUM",e.getCodeNum());
            map.put("CODE_TEXT",e.getCodeText());
            map.put("CODE_NTIME",e.getCodeNtime().toString());
            json.add(map);
        }
        return json;
    }
    public JSONArray statCodeAll() {
        new CodeService();
        List<Code> list=dao.getBy(2,"狀態");
        JSONArray json = new JSONArray( );
        JSONObject map=null;
        for(Code e : list){
            map=new JSONObject();
            map.put("CODE_RTIME", e.getCodeRtime().toString());
            map.put("STORE_NAME",new StoreDAO().getById(e.getStoreId()).getStoreName());
            map.put("CODE_NUM",e.getCodeNum());
            map.put("CODE_OFF","$"+e.getCodeOff());
            map.put("CODE_TEXT",e.getCodeText());
            map.put("CODE_NTIME",e.getCodeNtime().toString());
            json.add(map);
        }
        return json;
    }
    public JSONArray memCodeAllU(Integer memId) {
        MemCodeJDBCDAO memCodeJDBCDAO=new MemCodeJDBCDAO();
        JSONArray json = new JSONArray( );
        JSONObject map=null;
        List<MemCode> memCodeList=memCodeJDBCDAO.getByPK(memId,"memId");
        for(MemCode e : memCodeList){
            Integer codeid=e.getCodeId();
            Code code=dao.getById(codeid);
            if(code.getCodeStatus()==2){
                map=new JSONObject();
                map.put("CODE_ID", code.getCodeId());
                map.put("STORE_NAME",new StoreDAO().getById(code.getStoreId()).getStoreName());
                map.put("CODE_OFF",code.getCodeOff());
                map.put("CODE_NUM",code.getCodeNum());
                map.put("CODE_NTIME",code.getCodeNtime().toString());
                json.add(map);
            }
        }
        return json;
    }
    public JSONArray memCodeAll(Integer memId) {
        MemCodeJDBCDAO memCodeJDBCDAO=new MemCodeJDBCDAO();
        JSONArray json = new JSONArray( );
        JSONObject map=null;
        List<MemCode> memCodeList=memCodeJDBCDAO.getByPK(memId,"memId");
        for(MemCode e : memCodeList){
            Integer codeid=e.getCodeId();
            Code code=dao.getById(codeid);
            map=new JSONObject();
            map.put("CODE_ID", code.getCodeId());
            map.put("STORE_NAME",new StoreDAO().getById(code.getStoreId()).getStoreName());
            map.put("CODE_OFF",code.getCodeOff());
            map.put("CODE_NUM",code.getCodeNum());
            map.put("CODE_NTIME",code.getCodeNtime().toString());
            json.add(map);
        }
        return json;
    }

    public JSONArray empacc(){
        List<EmployeeRoot> emp=new EmployeeRootJDBCDAO().findByROOT_ID(4);
        List<Integer> empid=new ArrayList();
        JSONArray empacc = new JSONArray( );
        JSONObject map=null;
        for (EmployeeRoot e:emp){
            empid.add(e.getEmpId());
        }
        for (Integer e:empid){
            map=new JSONObject();
            map.put("EMP_ID",e);
            map.put("EMP_ACC",new EmployeeJDBCDAO().findByEMP_ID(e).getEmpAcc());
            empacc.add(map);
        }
        return empacc;
    }
    public JSONArray empCode(Integer empId) {
        Integer rootId=new CodeService().Coupon_root(empId);
        List<Code> list=dao.getBy(1,"狀態");
        JSONArray json = new JSONArray( );
        JSONObject map=null;
        switch (rootId){
            case 1:
                for(Code e : list){
                    if (e.getEmpId()==0) {
                        map = new JSONObject();
                        map.put("CODE_ID", e.getCodeId());
                        map.put("CODE_STATUS", e.getCodeStatus());
                        map.put("STORE_NAME",new StoreDAO().getById(e.getStoreId()).getStoreName());
                        map.put("CODE_OFF", e.getCodeOff());
                        map.put("CODE_NUM", e.getCodeNum());
                        map.put("CODE_TEXT", e.getCodeText());
                        map.put("CODE_NTIME", e.getCodeNtime().toString());
                        json.add(map);
                    }
                }
                break;
            case 4:
                for(Code e : list){
                    if (e.getEmpId()==empId) {
                        map = new JSONObject();
                        map.put("CODE_ID", e.getCodeId());
                        map.put("CODE_STATUS", e.getCodeStatus());
                        map.put("STORE_NAME",new StoreDAO().getById(e.getStoreId()).getStoreName());
                        map.put("CODE_OFF", e.getCodeOff());
                        map.put("CODE_NUM", e.getCodeNum());
                        map.put("CODE_TEXT", e.getCodeText());
                        map.put("CODE_NTIME", e.getCodeNtime().toString());
                        json.add(map);
                    }
                }
                break;
        }
        return json;
    }
    public Integer Coupon_root(Integer empId){
        EmployeeRootJDBCDAO erJDBC=new EmployeeRootJDBCDAO();
        List<EmployeeRoot>  list=erJDBC.findByEMP_ID(empId);
        Integer rootId=0;
        for (EmployeeRoot e :list){
            rootId=e.getRootId();
            if (rootId==1||rootId==4){
                break;
            }else {
                rootId=0;
            }
        }
        return rootId;
    }
    public boolean toempId(Integer codeId,Integer empId){
        Code code=new Code();
        code.setCodeId(codeId);
        code.setEmpId(empId);
        dao.update(code);
        return true;
    }
    public boolean tostat(Integer codeId,Integer status){
        Code code=new Code();
        code.setCodeId(codeId);
        code.setCodeStatus(status);
        dao.update(code);
        return true;
    }

    public Integer send(Integer codeId,Integer storeId){
        SubscribeJDBCDAO Subdao=new SubscribeJDBCDAO();
        MemCodeJDBCDAO Mcdao=new MemCodeJDBCDAO();
        List<Subscribe> subList= Subdao.getAllBystoreId(storeId);
        MemCode memCode=null;
        Integer comm=0;
        if (subList.size()>0){
            for (Subscribe e :subList){
                Integer MemId=e.getMemId();
                List<MemCode> memCodeList= Mcdao .getByPK(MemId,"MemId");
                Integer have=0;
                for (MemCode a:memCodeList){
                    if (a.getCodeId()==codeId){
                        have--;
                        break ;
                    }
                }
                if (have==0){
                    memCode=new MemCode();
                    memCode.setCodeId(codeId);
                    memCode.setMemId(e.getMemId());
                    new MemCodeJDBCDAO().insert(memCode);
                    comm++;
                }
            }
            return comm;
        }else {
            return -1;
        }
    }




}
