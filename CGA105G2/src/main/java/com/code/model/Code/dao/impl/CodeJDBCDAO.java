package com.code.model.Code.dao.impl;

import com.code.model.Code.dao.CodeDAO_interface;
import com.code.model.Code.pojo.Code;
import com.core.common.Common;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.core.common.Common.PASSWORD;
import static com.core.common.Common.USER;

public class CodeJDBCDAO implements CodeDAO_interface {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void insert(Code pojo) {
        //sql寫mySQL語法
        String sql="INSERT INTO cga105g2.code (STORE_ID,CODE_NUM,CODE_OFF,CODE_TEXT,CODE_NTIME) VALUES (?,?,?,?,?)";;
        //JDBC_mySQL 講義P8 創建連線物件，可重複使用
        try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
            //JDBC_mySQL 講義P15
            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            //Class.forName("com.mysql.cj.jdbc.Driver"); 不用寫，請參考JDBC_mySQL 講義P7 JDBC 4.0開始會⾃動註冊，呼叫 Class.forName() 是之前載入JDBC Driver的⽅式，現在可以省略不寫
            //本專案jar檔為8.0.31版本
            //以下為寫pstmt.set內容
            pstmt.setInt(1,pojo.getStoreId());
            pstmt.setString(2,pojo.getCodeNum());
            pstmt.setInt(3,pojo.getCodeOff());
            pstmt.setString(4,pojo.getCodeText());
            pstmt.setDate(5,pojo.getCodeNtime());
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql="DELETE from cga105g2.code where CODE_ID=?";
        try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        }
    }

    @Override
    public void update(Code pojo) {
        long miliseconds = System.currentTimeMillis();
        String sql="update cga105g2.code set EMP_ID=?,CODE_STATUS=?,CODE_RTIME=? where CODE_ID=?";
        Code code_new=pojo;
        Code code_old=new Code();
        try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            code_old=getById(code_new.getCodeId());
            pstmt.setInt(1,code_old.getEmpId());
            pstmt.setInt(2,code_old.getCodeStatus());
            pstmt.setDate(3,code_old.getCodeRtime());
            pstmt.setInt(4,code_old.getCodeId());
            if (code_new.getEmpId()!=null){
                pstmt.setInt(1,code_new.getEmpId());
            };
            if (code_new.getCodeStatus()!=null){
                pstmt.setInt(2,code_new.getCodeStatus());
                pstmt.setDate(3,new Date(miliseconds));
            }
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        }
    }

    @Override
    public Code getById(Integer id) {
        Code code=new Code();
        code=getByCodeId(id);
        return code;
    }

    public void failUpdate(Integer codeStatus){
        long miliseconds = System.currentTimeMillis();
        Date today=new Date(miliseconds);
        for (Code e:getBy(codeStatus,"狀態")){
            if (e.getCodeNtime().before(today)){
                Code code_old=new Code();
                code_old.setCodeId(e.getCodeId());
                code_old.setEmpId(e.getEmpId());
                code_old.setCodeStatus(3);
                update(code_old);
            }
        }
    }

    public List<Integer> getBycodeNum(String codeNum,Integer storeId) {
        String sql = "select STORE_ID,CODE_NUM,CODE_OFF from cga105g2.code where STORE_ID=? and CODE_STATUS=2";
        List<Integer> list=new ArrayList<>();
        try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
            pstmt.setInt(1, storeId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("CODE_NUM").trim().equals(codeNum.trim())){
                    list.add(rs.getInt("CODE_OFF"));
                }
            }
            return list;
        }catch (SQLException e){
            list.add(0);
            return list;
        }
    }
    @Override
    public List<Integer> getCodeId(String codeNum,Integer storeId) {
        String sql = "select CODE_ID,STORE_ID,CODE_NUM,CODE_OFF from cga105g2.code where STORE_ID=? and CODE_STATUS=2";
        List<Integer> list=new ArrayList<>();
        try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
            pstmt.setInt(1, storeId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("CODE_NUM").trim().equals(codeNum.trim())){
                    list.add(rs.getInt("CODE_ID"));
                }
            }
            return list;
        }catch (SQLException e){
            list.add(0);
            return list;
        }
    }


    public Code getByCodeId(Integer id) {
        String sql = "select CODE_ID,STORE_ID,EMP_ID,CODE_NUM,CODE_OFF,CODE_STATUS,CODE_TEXT,CODE_TIME,CODE_RTIME,CODE_NTIME from cga105g2.code where CODE_ID=?";
        Code code = null;
        try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                code = new Code();
                code.setCodeId(rs.getInt("CODE_ID"));
                code.setStoreId(rs.getInt("STORE_ID"));
                code.setEmpId(rs.getInt("EMP_ID"));
                code.setCodeNum(rs.getString("CODE_NUM"));
                code.setCodeOff(rs.getInt("CODE_OFF"));
                code.setCodeStatus(rs.getInt("CODE_STATUS"));
                code.setCodeText(rs.getString("CODE_TEXT"));
                code.setCodeTime(rs.getTimestamp("CODE_TIME"));
                code.setCodeRtime(rs.getDate("CODE_RTIME"));
                code.setCodeNtime(rs.getDate("CODE_NTIME"));
            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        }
        return code;
    }
    @Override
    public List<Code> getBy(Integer id,String string){
        List<Code> list=new ArrayList<Code>();
        String sql = "select CODE_ID,STORE_ID,EMP_ID,CODE_NUM,CODE_OFF,CODE_STATUS,CODE_TEXT,CODE_TIME,CODE_RTIME,CODE_NTIME from cga105g2.code";
        if (string.equals("店家")){
            sql+=" where STORE_ID=?;";
        }
        if (string.equals("狀態")){
            sql+=" where CODE_STATUS=?;";
        }
        Code code = null;
        try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                code = new Code();
                code.setCodeId(rs.getInt("CODE_ID"));
                code.setStoreId(rs.getInt("STORE_ID"));
                code.setEmpId(rs.getInt("EMP_ID"));
                code.setCodeNum(rs.getString("CODE_NUM"));
                code.setCodeOff(rs.getInt("CODE_OFF"));
                code.setCodeStatus(rs.getInt("CODE_STATUS"));
                code.setCodeText(rs.getString("CODE_TEXT"));
                code.setCodeTime(rs.getTimestamp("CODE_TIME"));
                code.setCodeRtime(rs.getDate("CODE_RTIME"));
                code.setCodeNtime(rs.getDate("CODE_NTIME"));
                list.add(code);
            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        }
        return list;
    }

    @Override
    public List<Code> getAll() {
        String sql="select CODE_ID,STORE_ID,EMP_ID,CODE_NUM,CODE_OFF,CODE_STATUS,CODE_TEXT,CODE_TIME,CODE_RTIME,CODE_NTIME from cga105g2.code order by CODE_ID";
        List<Code> list=new ArrayList<Code>();
        try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()){
                Code code = new Code();
                code.setCodeId(rs.getInt("CODE_ID"));
                code.setStoreId(rs.getInt("STORE_ID"));
                code.setEmpId(rs.getInt("EMP_ID"));
                code.setCodeNum(rs.getString("CODE_NUM"));
                code.setCodeOff(rs.getInt("CODE_OFF"));
                code.setCodeStatus(rs.getInt("CODE_STATUS"));
                code.setCodeText(rs.getString("CODE_TEXT"));
                code.setCodeTime(rs.getTimestamp("CODE_TIME"));
                code.setCodeRtime(rs.getDate("CODE_RTIME"));
                code.setCodeNtime(rs.getDate("CODE_NTIME"));
                list.add(code);
            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        }
        return list;
    }
}
