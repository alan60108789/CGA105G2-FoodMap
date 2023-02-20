package com.foodorder.model.Reserva.dao.impl;

import com.core.common.Common;
import com.foodorder.model.Reserva.dao.ReservaDAO_interface;
import com.foodorder.model.Reserva.pojo.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ReservaJDBCDAO implements ReservaDAO_interface {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Reserva reservaVO) {
        if (reservaVO.getCodeId() != null) {
            // sql寫mySQL語法
            String sql = "INSERT INTO cga105g2.reserva (STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_DATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            // JDBC_mySQL 講義P8 創建連線物件，可重複使用
            try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
                 // JDBC_mySQL 講義P15
                 PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                         ResultSet.CONCUR_READ_ONLY)) {
                // Class.forName("com.mysql.cj.jdbc.Driver"); 不用寫，請參考JDBC_mySQL 講義P7 JDBC
                // 4.0開始會⾃動註冊，呼叫 Class.forName() 是之前載入JDBC Driver的⽅式，現在可以省略不寫
                // 本專案jar檔為8.0.31版本
                // 以下為寫pstmt.set內容
                pstmt.setInt(1, reservaVO.getStoreId());
                pstmt.setInt(2, reservaVO.getMemId());
                pstmt.setString(3, reservaVO.getRenName());
                pstmt.setString(4, reservaVO.getRenPhone());
                pstmt.setString(5, reservaVO.getRenTime());
                pstmt.setDate(6, reservaVO.getRenDate());
                pstmt.setInt(7, reservaVO.getRenHeadcount());
                pstmt.setInt(8, reservaVO.getCodeId());
                pstmt.setInt(9, reservaVO.getRenPrice());
                pstmt.setInt(10, reservaVO.getRenFprice());
                // 送出
                pstmt.executeUpdate();
            } catch (SQLException se) {
                throw new RuntimeException("A database error occured. " + se.getMessage());
                // Clean up JDBC resources
            }
        }

        if (reservaVO.getCodeId() == null) {
            // sql寫mySQL語法
            String sql = "INSERT INTO cga105g2.reserva (STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_DATE, REN_HEADCOUNT, REN_PRICE, REN_FPRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            // JDBC_mySQL 講義P8 創建連線物件，可重複使用
            try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
                 // JDBC_mySQL 講義P15
                 PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                         ResultSet.CONCUR_READ_ONLY)) {
                // Class.forName("com.mysql.cj.jdbc.Driver"); 不用寫，請參考JDBC_mySQL 講義P7 JDBC
                // 4.0開始會⾃動註冊，呼叫 Class.forName() 是之前載入JDBC Driver的⽅式，現在可以省略不寫
                // 本專案jar檔為8.0.31版本
                // 以下為寫pstmt.set內容
                pstmt.setInt(1, reservaVO.getStoreId());
                pstmt.setInt(2, reservaVO.getMemId());
                pstmt.setString(3, reservaVO.getRenName());
                pstmt.setString(4, reservaVO.getRenPhone());
                pstmt.setString(5, reservaVO.getRenTime());
                pstmt.setDate(6, reservaVO.getRenDate());
                pstmt.setInt(7, reservaVO.getRenHeadcount());
                pstmt.setInt(8, reservaVO.getRenPrice());
                pstmt.setInt(9, reservaVO.getRenFprice());
                // 送出
                pstmt.executeUpdate();
            } catch (SQLException se) {
                throw new RuntimeException("A database error occured. " + se.getMessage());

            }
        }

    }
    @Override
    public void update(Reserva reservaVO) {
        long miliseconds = System.currentTimeMillis();
        String sql = "UPDATE cga105g2.reserva set REN_STATUS=?, REN_TABLE=?, REN_DATE=? where REN_ID = ?";
        Reserva reserva_new = reservaVO;
        Reserva reserva_old = new Reserva();
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            reserva_old = getById(reserva_new.getRenId());
            pstmt.setInt(1, reserva_old.getRenStatus());
            pstmt.setInt(2, reserva_old.getRenTable());
            pstmt.setDate(3, reserva_old.getRenDate());
            pstmt.setInt(4, reserva_old.getRenId());

            if (reserva_new.getRenStatus() != null) {
                pstmt.setInt(1, reserva_new.getRenStatus());
            }
            if (reserva_new.getRenTable() != null) {
                pstmt.setInt(2, reserva_new.getRenTable());
            } else {
                // 因為初始值為null,如果不寫預設帶入0
                pstmt.setNull(2, Types.NULL);
            }
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }

    }

    public void wattingupdate(Reserva reservaVO) {
        String sql = "UPDATE cga105g2.reserva set REN_PRICE=?, REN_FPRICE=?";
        if (reservaVO.getCodeId()!=0){
            sql+=",CODE_ID=?";
        }
        sql+=" where REN_ID = ?";
        Reserva reserva_new = reservaVO;
        Reserva reserva_old = new Reserva();
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            reserva_old = getById(reserva_new.getRenId());
            //原價
            pstmt.setInt(1, reserva_new.getRenPrice());
            //支付金額
            pstmt.setInt(2, reserva_new.getRenFprice());
            //優惠券
            if (reservaVO.getCodeId()!=0){
                pstmt.setInt(3, reserva_new.getCodeId());
                pstmt.setInt(4, reserva_new.getRenId());
            }else {
                pstmt.setInt(3, reserva_new.getRenId());
            }
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }

    }


    @Override
    public void updateRenstatusByRenid(Integer renid, Integer renstatus) {
        long miliseconds = System.currentTimeMillis();
        String sql = "UPDATE cga105g2.reserva set REN_STATUS=? where REN_ID = ?";
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, renstatus);
            pstmt.setInt(2, renid);
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }

    }

    @Override
    public Reserva getById(Integer id) {
        String sql = "SELECT REN_ID, STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_STATUS, REN_TABLE, REN_DATE, REN_CURDATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE FROM cga105g2.reserva where REN_ID = ?";
        Reserva reserva = null;
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reserva = new Reserva();
                reserva.setRenId(rs.getInt("REN_ID"));
                reserva.setStoreId(rs.getInt("STORE_ID"));
                reserva.setMemId(rs.getInt("MEM_ID"));
                reserva.setRenName(rs.getString("REN_NAME"));
                reserva.setRenPhone(rs.getString("REN_PHONE"));
                reserva.setRenTime(rs.getString("REN_TIME"));
                reserva.setRenStatus(rs.getInt("REN_STATUS"));
                reserva.setRenTable(rs.getInt("REN_TABLE"));
                reserva.setRenDate(rs.getDate("REN_DATE"));
                reserva.setRenCurdate(rs.getTimestamp("REN_CURDATE"));
                reserva.setRenHeadcount(rs.getInt("REN_HEADCOUNT"));
                reserva.setCodeId(rs.getInt("CODE_ID"));
                reserva.setRenPrice(rs.getInt("REN_PRICE"));
                reserva.setRenFprice(rs.getInt("REN_FPRICE"));
            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }
        return reserva;
    }

    @Override
    public List<Reserva> getBymemIdrenStatus(Integer memid, Integer renstatus) {
        String sql = "SELECT REN_ID, STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_STATUS, REN_TABLE, REN_DATE, REN_CURDATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE FROM cga105g2.reserva where MEM_ID = ? and REN_STATUS = ?";
        Reserva reserva = null;
        List<Reserva> list = new ArrayList<Reserva>();
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, memid);
            pstmt.setInt(2, renstatus);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reserva = new Reserva();
                reserva.setRenId(rs.getInt("REN_ID"));
                reserva.setStoreId(rs.getInt("STORE_ID"));
                reserva.setMemId(rs.getInt("MEM_ID"));
                reserva.setRenName(rs.getString("REN_NAME"));
                reserva.setRenPhone(rs.getString("REN_PHONE"));
                reserva.setRenTime(rs.getString("REN_TIME"));
                reserva.setRenStatus(rs.getInt("REN_STATUS"));
                reserva.setRenTable(rs.getInt("REN_TABLE"));
                reserva.setRenDate(rs.getDate("REN_DATE"));
                reserva.setRenCurdate(rs.getTimestamp("REN_CURDATE"));
                reserva.setRenHeadcount(rs.getInt("REN_HEADCOUNT"));
                reserva.setCodeId(rs.getInt("CODE_ID"));
                reserva.setRenPrice(rs.getInt("REN_PRICE"));
                reserva.setRenFprice(rs.getInt("REN_FPRICE"));
                list.add(reserva);

            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }
        return list;
    }

    @Override
    public List<Reserva> getBymemId(Integer memid) {
        String sql = "SELECT REN_ID, STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_STATUS, REN_TABLE, REN_DATE, REN_CURDATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE FROM cga105g2.reserva where MEM_ID = ?";
        Reserva reserva = null;
        List<Reserva> list = new ArrayList<Reserva>();
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, memid);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reserva = new Reserva();
                reserva.setRenId(rs.getInt("REN_ID"));
                reserva.setStoreId(rs.getInt("STORE_ID"));
                reserva.setMemId(rs.getInt("MEM_ID"));
                reserva.setRenName(rs.getString("REN_NAME"));
                reserva.setRenPhone(rs.getString("REN_PHONE"));
                reserva.setRenTime(rs.getString("REN_TIME"));
                reserva.setRenStatus(rs.getInt("REN_STATUS"));
                reserva.setRenTable(rs.getInt("REN_TABLE"));
                reserva.setRenDate(rs.getDate("REN_DATE"));
                reserva.setRenCurdate(rs.getTimestamp("REN_CURDATE"));
                reserva.setRenHeadcount(rs.getInt("REN_HEADCOUNT"));
                reserva.setCodeId(rs.getInt("CODE_ID"));
                reserva.setRenPrice(rs.getInt("REN_PRICE"));
                reserva.setRenFprice(rs.getInt("REN_FPRICE"));
                list.add(reserva);

            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }
        return list;
    }

    @Override
    public List<Reserva> getAll() {
        String sql = "SELECT REN_ID, STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_STATUS, REN_TABLE, REN_DATE, REN_CURDATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE FROM cga105g2.reserva order by REN_ID";
        Reserva reserva = null;
        List<Reserva> list = new ArrayList<Reserva>();
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reserva = new Reserva();
                reserva.setRenId(rs.getInt("REN_ID"));
                reserva.setStoreId(rs.getInt("STORE_ID"));
                reserva.setMemId(rs.getInt("MEM_ID"));
                reserva.setRenName(rs.getString("REN_NAME"));
                reserva.setRenPhone(rs.getString("REN_PHONE"));
                reserva.setRenTime(rs.getString("REN_TIME"));
                reserva.setRenStatus(rs.getInt("REN_STATUS"));
                reserva.setRenTable(rs.getInt("REN_TABLE"));
                reserva.setRenDate(rs.getDate("REN_DATE"));
                reserva.setRenCurdate(rs.getTimestamp("REN_CURDATE"));
                reserva.setRenHeadcount(rs.getInt("REN_HEADCOUNT"));
                reserva.setCodeId(rs.getInt("CODE_ID"));
                reserva.setRenPrice(rs.getInt("REN_PRICE"));
                reserva.setRenFprice(rs.getInt("REN_FPRICE"));
                list.add(reserva);

            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }
        return list;
    }

    public List<Reserva> getByStatus(Integer renStatus) {
        List<Reserva> list = new ArrayList<Reserva>();
        String sql = "SELECT REN_ID, STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_STATUS, REN_TABLE, REN_DATE, REN_CURDATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE FROM cga105g2.reserva where REN_STATUS = ?";
        Reserva reserva = null;
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, renStatus);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reserva = new Reserva();
                reserva.setRenId(rs.getInt("REN_ID"));
                reserva.setStoreId(rs.getInt("STORE_ID"));
                reserva.setMemId(rs.getInt("MEM_ID"));
                reserva.setRenName(rs.getString("REN_NAME"));
                reserva.setRenPhone(rs.getString("REN_PHONE"));
                reserva.setRenTime(rs.getString("REN_TIME"));
                reserva.setRenStatus(rs.getInt("REN_STATUS"));
                reserva.setRenTable(rs.getInt("REN_TABLE"));
                reserva.setRenDate(rs.getDate("REN_DATE"));
                reserva.setRenCurdate(rs.getTimestamp("REN_CURDATE"));
                reserva.setRenHeadcount(rs.getInt("REN_HEADCOUNT"));
                reserva.setCodeId(rs.getInt("CODE_ID"));
                reserva.setRenPrice(rs.getInt("REN_PRICE"));
                reserva.setRenFprice(rs.getInt("REN_FPRICE"));
                list.add(reserva);
            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }
        return list;
    }

    public void failUpdate(Integer renStatus) {
        long miliseconds = System.currentTimeMillis();
        Date today = new Date(miliseconds);
        for (Reserva e : getByStatus(renStatus)) {
            if (e.getRenDate().before(today)) {
                Reserva reserva_old = new Reserva();
                reserva_old.setRenId(e.getRenId());
                reserva_old.setRenStatus(4);// 4為失效
                update(reserva_old);
            }
        }
    }

    @Override
    public List<Reserva> getByStoreIdRendate(Integer storeid, String rendate, String rentime, Integer renstatus) {
        List<Reserva> list = new ArrayList<Reserva>();
        String sql = "SELECT REN_ID, STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_STATUS, REN_TABLE, REN_DATE, REN_CURDATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE FROM cga105g2.reserva where STORE_ID = ? and REN_DATE = ? and REN_TIME = ? and REN_STATUS = ?";
        Reserva reserva = null;
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, storeid);
            pstmt.setString(2, rendate);
            pstmt.setString(3, rentime);
            pstmt.setInt(4, renstatus);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reserva = new Reserva();
                reserva.setRenId(rs.getInt("REN_ID"));
                reserva.setStoreId(rs.getInt("STORE_ID"));
                reserva.setMemId(rs.getInt("MEM_ID"));
                reserva.setRenName(rs.getString("REN_NAME"));
                reserva.setRenPhone(rs.getString("REN_PHONE"));
                reserva.setRenTime(rs.getString("REN_TIME"));
                reserva.setRenStatus(rs.getInt("REN_STATUS"));
                reserva.setRenTable(rs.getInt("REN_TABLE"));
                reserva.setRenDate(rs.getDate("REN_DATE"));
                reserva.setRenCurdate(rs.getTimestamp("REN_CURDATE"));
                reserva.setRenHeadcount(rs.getInt("REN_HEADCOUNT"));
                reserva.setCodeId(rs.getInt("CODE_ID"));
                reserva.setRenPrice(rs.getInt("REN_PRICE"));
                reserva.setRenFprice(rs.getInt("REN_FPRICE"));
                list.add(reserva);
            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }
        return list;
    }

    @Override
    public void insertWithReservaDetails(Reserva reservaVO, List<Map> list) {
        if (reservaVO.getCodeId() != null) {
            String sql = "INSERT INTO cga105g2.reserva (STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_DATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String sql2 = "INSERT INTO cga105g2.reserva_detail (REN_ID, MEAL_ID, RD_QUANTITY, PD_PRICE) VALUES (?,?,?,?);";
            try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
                 PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement pstmt2 = con.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS)) {
                // 1●設定於 pstm.executeUpdate()之前
                con.setAutoCommit(false);
                // 先新增
                pstmt.setInt(1, reservaVO.getStoreId());
                pstmt.setInt(2, reservaVO.getMemId());
                pstmt.setString(3, reservaVO.getRenName());
                pstmt.setString(4, reservaVO.getRenPhone());
                pstmt.setString(5, reservaVO.getRenTime());
                pstmt.setDate(6, reservaVO.getRenDate());
                pstmt.setInt(7, reservaVO.getRenHeadcount());
                pstmt.setInt(8, reservaVO.getCodeId());
                pstmt.setInt(9, reservaVO.getRenPrice());
                pstmt.setInt(10, reservaVO.getRenFprice());
                pstmt.executeUpdate();
                //掘取對應的自增主鍵值
                String next_id = null;
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    next_id = rs.getString(1);
                } else {
                }
                rs.close();
                // 再同時新增訂單明細
                for (int i = 0; i < list.size(); i++) {
                    pstmt2.setInt(1, Integer.valueOf(next_id));
                    pstmt2.setInt(2, (Integer) list.get(i).get("mealId"));
                    pstmt2.setInt(3, (Integer) list.get(i).get("rdQuantity"));
                    pstmt2.setInt(4, (Integer) list.get(i).get("pdPrice"));
                    pstmt2.executeUpdate();
                }
                con.commit();
                con.setAutoCommit(true);

            } catch (SQLException se) {
                throw new RuntimeException("A database error occured. " + se.getMessage());
            }
        }
        if (reservaVO.getCodeId() == null) {
            String sql = "INSERT INTO cga105g2.reserva (STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_DATE, REN_HEADCOUNT, REN_PRICE, REN_FPRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String sql2 = "INSERT INTO cga105g2.reserva_detail (REN_ID, MEAL_ID, RD_QUANTITY, PD_PRICE) VALUES (?,?,?,?);";
            try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
                 PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement pstmt2 = con.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS)) {
                // 1●設定於 pstm.executeUpdate()之前
                con.setAutoCommit(false);
                // 先新增
                pstmt.setInt(1, reservaVO.getStoreId());
                pstmt.setInt(2, reservaVO.getMemId());
                pstmt.setString(3, reservaVO.getRenName());
                pstmt.setString(4, reservaVO.getRenPhone());
                pstmt.setString(5, reservaVO.getRenTime());
                pstmt.setDate(6, reservaVO.getRenDate());
                pstmt.setInt(7, reservaVO.getRenHeadcount());
                pstmt.setInt(8, reservaVO.getRenPrice());
                pstmt.setInt(9, reservaVO.getRenFprice());
                pstmt.executeUpdate();
                //掘取對應的自增主鍵值
                String next_id = null;
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    next_id = rs.getString(1);
                } else {
                }
                rs.close();
                // 再同時新增訂單明細
                for (int i = 0; i < list.size(); i++) {
                    pstmt2.setInt(1, Integer.valueOf(next_id));
                    pstmt2.setInt(2, (Integer) list.get(i).get("mealId"));
                    pstmt2.setInt(3, (Integer) list.get(i).get("rdQuantity"));
                    pstmt2.setInt(4, (Integer) list.get(i).get("pdPrice"));
                    pstmt2.executeUpdate();
                }
                con.commit();
                con.setAutoCommit(true);

            } catch (SQLException se) {
                throw new RuntimeException("A database error occured. " + se.getMessage());
            }
        }
    }

    @Override
    public List<Reserva> getBystoreId(Integer storeid) {
        String sql = "SELECT REN_ID, STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_STATUS, REN_TABLE, REN_DATE, REN_CURDATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE FROM cga105g2.reserva where STORE_ID = ?";
        Reserva reserva = null;
        List<Reserva> list = new ArrayList<Reserva>();
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, storeid);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reserva = new Reserva();
                reserva.setRenId(rs.getInt("REN_ID"));
                reserva.setStoreId(rs.getInt("STORE_ID"));
                reserva.setMemId(rs.getInt("MEM_ID"));
                reserva.setRenName(rs.getString("REN_NAME"));
                reserva.setRenPhone(rs.getString("REN_PHONE"));
                reserva.setRenTime(rs.getString("REN_TIME"));
                reserva.setRenStatus(rs.getInt("REN_STATUS"));
                reserva.setRenTable(rs.getInt("REN_TABLE"));
                reserva.setRenDate(rs.getDate("REN_DATE"));
                reserva.setRenCurdate(rs.getTimestamp("REN_CURDATE"));
                reserva.setRenHeadcount(rs.getInt("REN_HEADCOUNT"));
                reserva.setCodeId(rs.getInt("CODE_ID"));
                reserva.setRenPrice(rs.getInt("REN_PRICE"));
                reserva.setRenFprice(rs.getInt("REN_FPRICE"));
                list.add(reserva);

            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }
        return list;
    }

    @Override
    public List<Reserva> getBystoreIdrenStatus(Integer storeid, Integer renstatus) {
        String sql = "SELECT REN_ID, STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_STATUS, REN_TABLE, REN_DATE, REN_CURDATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE FROM cga105g2.reserva where STORE_ID = ? and REN_STATUS = ?";
        Reserva reserva = null;
        List<Reserva> list = new ArrayList<Reserva>();
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, storeid);
            pstmt.setInt(2, renstatus);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reserva = new Reserva();
                reserva.setRenId(rs.getInt("REN_ID"));
                reserva.setStoreId(rs.getInt("STORE_ID"));
                reserva.setMemId(rs.getInt("MEM_ID"));
                reserva.setRenName(rs.getString("REN_NAME"));
                reserva.setRenPhone(rs.getString("REN_PHONE"));
                reserva.setRenTime(rs.getString("REN_TIME"));
                reserva.setRenStatus(rs.getInt("REN_STATUS"));
                reserva.setRenTable(rs.getInt("REN_TABLE"));
                reserva.setRenDate(rs.getDate("REN_DATE"));
                reserva.setRenCurdate(rs.getTimestamp("REN_CURDATE"));
                reserva.setRenHeadcount(rs.getInt("REN_HEADCOUNT"));
                reserva.setCodeId(rs.getInt("CODE_ID"));
                reserva.setRenPrice(rs.getInt("REN_PRICE"));
                reserva.setRenFprice(rs.getInt("REN_FPRICE"));
                list.add(reserva);

            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }
        return list;
    }

    @Override
    public void insertToSta(Reserva reservaVO) {
        // sql寫mySQL語法
        String sql = "INSERT INTO cga105g2.reserva (STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_DATE, REN_HEADCOUNT, REN_PRICE, REN_FPRICE,REN_STATUS,REN_TABLE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        // JDBC_mySQL 講義P8 創建連線物件，可重複使用
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             // JDBC_mySQL 講義P15
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            Integer s= Integer.valueOf(reservaVO.getRenPhone());
            pstmt.setInt(1, reservaVO.getStoreId());
            pstmt.setInt(2, reservaVO.getMemId());
            pstmt.setString(3, reservaVO.getRenName());
            pstmt.setString(4, reservaVO.getRenPhone());
            pstmt.setString(5, reservaVO.getRenTime());
            pstmt.setDate(6, reservaVO.getRenDate());
            pstmt.setInt(7, reservaVO.getRenHeadcount());
            pstmt.setInt(8, reservaVO.getRenPrice());
            pstmt.setInt(9, reservaVO.getRenFprice());
            pstmt.setInt(10, 2);
            pstmt.setInt(11, s);
            // 送出
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
            // Clean up JDBC resources
        }
    }

    @Override
    public Reserva gettable(Integer id) {
        String sql = "SELECT * FROM cga105g2.reserva where REN_TABLE = ?";
        Reserva reserva = null;
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reserva = new Reserva();
                reserva.setRenId(rs.getInt("REN_ID"));
                reserva.setStoreId(rs.getInt("STORE_ID"));
                reserva.setMemId(rs.getInt("MEM_ID"));
                reserva.setRenName(rs.getString("REN_NAME"));
                reserva.setRenPhone(rs.getString("REN_PHONE"));
                reserva.setRenTime(rs.getString("REN_TIME"));
                reserva.setRenStatus(rs.getInt("REN_STATUS"));
                reserva.setRenTable(rs.getInt("REN_TABLE"));
                reserva.setRenDate(rs.getDate("REN_DATE"));
                reserva.setRenCurdate(rs.getTimestamp("REN_CURDATE"));
                reserva.setRenHeadcount(rs.getInt("REN_HEADCOUNT"));
                reserva.setCodeId(rs.getInt("CODE_ID"));
                reserva.setRenPrice(rs.getInt("REN_PRICE"));
                reserva.setRenFprice(rs.getInt("REN_FPRICE"));
            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }
        return reserva;
    }
    public Reserva getphone(Integer sid,String phone,Integer pn) {
        String sql = "SELECT * FROM cga105g2.reserva where STORE_ID = ? and REN_PHONE = ? and REN_STATUS = ? and REN_HEADCOUNT = ?";
        Reserva reserva = null;
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, sid);
            pstmt.setString(2, phone);
            pstmt.setInt(3, 2);
            pstmt.setInt(4, pn);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reserva = new Reserva();
                reserva.setRenId(rs.getInt("REN_ID"));
                reserva.setStoreId(rs.getInt("STORE_ID"));
                reserva.setMemId(rs.getInt("MEM_ID"));
                reserva.setRenName(rs.getString("REN_NAME"));
                reserva.setRenPhone(rs.getString("REN_PHONE"));
                reserva.setRenTime(rs.getString("REN_TIME"));
                reserva.setRenStatus(rs.getInt("REN_STATUS"));
                reserva.setRenTable(rs.getInt("REN_TABLE"));
                reserva.setRenDate(rs.getDate("REN_DATE"));
                reserva.setRenCurdate(rs.getTimestamp("REN_CURDATE"));
                reserva.setRenHeadcount(rs.getInt("REN_HEADCOUNT"));
                reserva.setRenPrice(rs.getInt("REN_PRICE"));
                reserva.setRenFprice(rs.getInt("REN_FPRICE"));
            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }
        return reserva;
    }



}
