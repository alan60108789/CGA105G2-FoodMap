package com.store.model.Store.dao.impl;

import com.core.common.Common;
import com.store.model.Store.dao.StoreDAO_interface;
import com.store.model.Store.pojo.Store;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO implements StoreDAO_interface {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Store Store) {

        String sql = "INSERT INTO cga105g2.store (EMP_ID,STORE_NAME,STORE_PHONE1,STORE_HOURS,STORE_CITY,STORE_DISTRICT,STORE_ADDRESS,STORE_URL,STORE_WEB,STORE_ACC,STORE_PWD,STORE_MAIL,STORE_COM_ID,STORE_COM_ADDRESS,STORE_TW_ID,STORE_PHONE2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            pstmt.setInt(1, Store.getEmpId());
            pstmt.setString(2, Store.getStoreName());
            pstmt.setString(3, Store.getStorePhone1());
            pstmt.setString(4, Store.getStoreHours());
            pstmt.setString(5, Store.getStoreCity());
            pstmt.setString(6, Store.getStoreDistrict());
            pstmt.setString(7, Store.getStoreAddress());
            pstmt.setString(8, Store.getStoreUrl());
            pstmt.setString(9, Store.getStoreWeb());
            pstmt.setString(10, Store.getStoreAcc());
            pstmt.setString(11, Store.getStorePwd());
            pstmt.setString(12, Store.getStoreMail());
            pstmt.setString(13, Store.getStoreComId());
            pstmt.setString(14, Store.getStoreComAddress());
            pstmt.setString(15, Store.getStoreTwId());
            pstmt.setString(16, Store.getStorePhone2());

            pstmt.executeUpdate();

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());

        }

    }

    @Override
    public void update(Store Store) {

        String sql = "UPDATE cga105g2.store set EMP_ID=?,STORE_NAME=?,STORE_PHONE1=?,STORE_HOURS=?,STORE_MAP=?,STORE_CITY=?,STORE_DISTRICT=?,STORE_ADDRESS=?,STORE_URL=?,STORE_WEB=?,STORE_ACC=?,STORE_PWD=?,STORE_MAIL=?,STORE_COM_ID=?,STORE_COM_ADDRESS=?,"
                + "STORE_TW_ID=?,STORE_PHONE2=?,STORE_TEXT=?,STORE_PLAN=?,STORE_NPLAN=?,STORE_ONTIME=?,STORE_ETIME=?,STORE_TABLE=?,STORE_ETABLE=? where STORE_ID = ?";

        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            pstmt.setInt(1, Store.getEmpId());
            pstmt.setString(2, Store.getStoreName());
            pstmt.setString(3, Store.getStorePhone1());
            pstmt.setString(4, Store.getStoreHours());
            pstmt.setString(5, Store.getStoreMap());
            pstmt.setString(6, Store.getStoreCity());
            pstmt.setString(7, Store.getStoreDistrict());
            pstmt.setString(8, Store.getStoreAddress());
            pstmt.setString(9, Store.getStoreUrl());
            pstmt.setString(10, Store.getStoreWeb());
            pstmt.setString(11, Store.getStoreAcc());
            pstmt.setString(12, Store.getStorePwd());
            pstmt.setString(13, Store.getStoreMail());
            pstmt.setString(14, Store.getStoreComId());
            pstmt.setString(15, Store.getStoreComAddress());
            pstmt.setString(16, Store.getStoreTwId());
            pstmt.setString(17, Store.getStorePhone2());
            pstmt.setString(18, Store.getStoreText());
            pstmt.setInt(19, Store.getStorePlan());
            pstmt.setInt(20, Store.getStoreNplan());
            pstmt.setDate(21, Store.getStoreOntime());
            pstmt.setString(22, Store.getStoreEtime());
            pstmt.setInt(23, Store.getStoreTable());
            pstmt.setInt(24, Store.getStoreEtable());
            pstmt.setInt(25, Store.getStoreId());

            pstmt.executeUpdate();

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }

    }

    @Override
    public void delete(Integer storeId) {

        String sql = "DELETE FROM cga105g2.store where STORE_ID = ?";

        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            pstmt.setInt(1, storeId);

            pstmt.executeUpdate();

            // Handle any driver errors

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
            // Clean up JDBC resources
        }

    }

    @Override
    public Store getById(Integer storeId) {
        String sql = "SELECT STORE_ID,EMP_ID,STORE_STATUS,STORE_NAME,STORE_PHONE1,STORE_HOURS,STORE_MAP,STORE_CITY,STORE_DISTRICT,STORE_ADDRESS,STORE_URL,STORE_WEB,STORE_ACC,STORE_PWD,STORE_MAIL,STORE_COM_ID,STORE_COM_ADDRESS,STORE_TW_ID,STORE_PHONE2,STORE_TEXT,STORE_PLAN,STORE_NPLAN,STORE_TIME,STORE_ONTIME,STORE_RETIME,STORE_ETIME,STORE_TABLE,STORE_ETABLE FROM cga105g2.store where STORE_ID = ?";
        Store store = null;
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            pstmt.setInt(1, storeId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                // memberVO 也稱為 Domain objects
                store = new Store();
                store.setStoreId(rs.getInt("STORE_ID"));
                store.setEmpId(rs.getInt("EMP_ID"));
                store.setStoreStatus(rs.getInt("STORE_STATUS"));
                store.setStoreName(rs.getString("STORE_NAME"));
                store.setStorePhone1(rs.getString("STORE_PHONE1"));
                store.setStoreHours(rs.getString("STORE_HOURS"));
                store.setStoreMap(rs.getString("STORE_MAP"));
                store.setStoreCity(rs.getString("STORE_CITY"));
                store.setStoreDistrict(rs.getString("STORE_DISTRICT"));
                store.setStoreAddress(rs.getString("STORE_ADDRESS"));
                store.setStoreUrl(rs.getString("STORE_URL"));
                store.setStoreWeb(rs.getString("STORE_WEB"));
                store.setStoreAcc(rs.getString("STORE_ACC"));
                store.setStorePwd(rs.getString("STORE_PWD"));
                store.setStoreMail(rs.getString("STORE_MAIL"));
                store.setStoreComId(rs.getString("STORE_COM_ID"));
                store.setStoreComAddress(rs.getString("STORE_COM_ADDRESS"));
                store.setStoreTwId(rs.getString("STORE_TW_ID"));
                store.setStorePhone2(rs.getString("STORE_PHONE2"));
                store.setStoreText(rs.getString("STORE_TEXT"));
                store.setStorePlan(rs.getInt("STORE_PLAN"));
                store.setStoreNplan(rs.getInt("STORE_NPLAN"));
                store.setStoreTime(rs.getTimestamp("STORE_TIME"));
                store.setStoreOntime(rs.getDate("STORE_ONTIME"));
                store.setStoreRetime(rs.getTimestamp("STORE_RETIME"));
                store.setStoreEtime(rs.getString("STORE_ETIME"));
                store.setStoreTable(rs.getInt("STORE_TABLE"));
                store.setStoreEtable(rs.getInt("STORE_ETABLE"));

            }
            // Handle any driver errors

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }
        return store;
    }


    public List<Store> getByStoreName(String storeName) { //要叫louie新增
        String sql = "SELECT * FROM cga105g2.store where STORE_NAME like ?";
        List<Store> list = new ArrayList<Store>();
        Store store = new Store();
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setString(1, "%" + storeName + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // memberVO 也稱為 Domain objects
                store = new Store();
                store.setStoreId(rs.getInt("STORE_ID"));
                store.setEmpId(rs.getInt("EMP_ID"));
                store.setStoreStatus(rs.getInt("STORE_STATUS"));
                store.setStoreName(rs.getString("STORE_NAME"));
                store.setStorePhone1(rs.getString("STORE_PHONE1"));
                store.setStoreHours(rs.getString("STORE_HOURS"));
                store.setStoreMap(rs.getString("STORE_MAP"));
                store.setStoreCity(rs.getString("STORE_CITY"));
                store.setStoreDistrict(rs.getString("STORE_DISTRICT"));
                store.setStoreAddress(rs.getString("STORE_ADDRESS"));
                store.setStoreUrl(rs.getString("STORE_URL"));
                store.setStoreWeb(rs.getString("STORE_WEB"));
                store.setStoreAcc(rs.getString("STORE_ACC"));
                store.setStorePwd(rs.getString("STORE_PWD"));
                store.setStoreMail(rs.getString("STORE_MAIL"));
                store.setStoreComId(rs.getString("STORE_COM_ID"));
                store.setStoreComAddress(rs.getString("STORE_COM_ADDRESS"));
                store.setStoreTwId(rs.getString("STORE_TW_ID"));
                store.setStorePhone2(rs.getString("STORE_PHONE2"));
                store.setStoreText(rs.getString("STORE_TEXT"));
                store.setStorePlan(rs.getInt("STORE_PLAN"));
                store.setStoreNplan(rs.getInt("STORE_NPLAN"));
                store.setStoreTime(rs.getTimestamp("STORE_TIME"));
                store.setStoreOntime(rs.getDate("STORE_ONTIME"));
                store.setStoreRetime(rs.getTimestamp("STORE_RETIME"));
                store.setStoreEtime(rs.getString("STORE_ETIME"));
                store.setStoreTable(rs.getInt("STORE_TABLE"));
                store.setStoreEtable(rs.getInt("STORE_ETABLE"));
                list.add(store); // Store the row in the list
            }
            return list;
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
            // Clean up JDBC resources
        }
    }


    @Override
    public List<Store> getAll() {

        String sql = "SELECT STORE_ID,EMP_ID,STORE_STATUS,STORE_NAME,STORE_PHONE1,STORE_HOURS,STORE_MAP,STORE_CITY,STORE_DISTRICT,STORE_ADDRESS,STORE_URL,STORE_WEB,STORE_ACC,STORE_PWD,STORE_MAIL,STORE_COM_ID,STORE_COM_ADDRESS,STORE_TW_ID,STORE_PHONE2,STORE_TEXT,STORE_PLAN,STORE_NPLAN,STORE_TIME,STORE_ONTIME,STORE_RETIME,STORE_ETIME,STORE_TABLE,STORE_ETABLE FROM cga105g2.store order by STORE_ID";

        List<Store> list = new ArrayList<Store>();
        Store store = null;

        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // memberVO 也稱為 Domain objects
                store = new Store();
                store.setStoreId(rs.getInt("STORE_ID"));
                store.setEmpId(rs.getInt("EMP_ID"));
                store.setStoreStatus(rs.getInt("STORE_STATUS"));
                store.setStoreName(rs.getString("STORE_NAME"));
                store.setStorePhone1(rs.getString("STORE_PHONE1"));
                store.setStoreHours(rs.getString("STORE_HOURS"));
                store.setStoreMap(rs.getString("STORE_MAP"));
                store.setStoreCity(rs.getString("STORE_CITY"));
                store.setStoreDistrict(rs.getString("STORE_DISTRICT"));
                store.setStoreAddress(rs.getString("STORE_ADDRESS"));
                store.setStoreUrl(rs.getString("STORE_URL"));
                store.setStoreWeb(rs.getString("STORE_WEB"));
                store.setStoreAcc(rs.getString("STORE_ACC"));
                store.setStorePwd(rs.getString("STORE_PWD"));
                store.setStoreMail(rs.getString("STORE_MAIL"));
                store.setStoreComId(rs.getString("STORE_COM_ID"));
                store.setStoreComAddress(rs.getString("STORE_COM_ADDRESS"));
                store.setStoreTwId(rs.getString("STORE_TW_ID"));
                store.setStorePhone2(rs.getString("STORE_PHONE2"));
                store.setStoreText(rs.getString("STORE_TEXT"));
                store.setStorePlan(rs.getInt("STORE_PLAN"));
                store.setStoreNplan(rs.getInt("STORE_NPLAN"));
                store.setStoreTime(rs.getTimestamp("STORE_TIME"));
                store.setStoreOntime(rs.getDate("STORE_ONTIME"));
                store.setStoreRetime(rs.getTimestamp("STORE_RETIME"));
                store.setStoreEtime(rs.getString("STORE_ETIME"));
                store.setStoreTable(rs.getInt("STORE_TABLE"));
                store.setStoreEtable(rs.getInt("STORE_ETABLE"));
                list.add(store); // Store the row in the list
            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
            // Clean up JDBC resources
        }
        return list;
    }

    @Override
    public void updateStatus(Integer storeId, Integer storeStatus) {
        String sql = "UPDATE cga105g2.store set STORE_STATUS=? where STORE_ID = ?";

        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            pstmt.setInt(1, storeStatus);
            pstmt.setInt(2, storeId);
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }

    }

    @Override
    public void updateempId(Integer storeId, Integer empId) {
        String sql = "UPDATE cga105g2.store set EMP_ID=? where STORE_ID = ?";
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, empId);
            pstmt.setInt(2, storeId);
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }
    }

    @Override
    public void updatePlan(Integer storeId, Integer storePlan) {
        String sql = "UPDATE cga105g2.store set STORE_PLAN=? where STORE_ID = ?";
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, storePlan);
            pstmt.setInt(2, storeId);
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }

    }

    @Override
    public void updateNplan(Integer storeId, Integer storeNplan) {
        String sql = "UPDATE cga105g2.store set STORE_NPLAN=? where STORE_ID = ?";
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1, storeNplan);
            pstmt.setInt(2, storeId);
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }

    }

    @Override
    public void updateordersetting(Integer storeId, String storeEtime, Integer storeTable, Integer storeEtable) {
        String sql = "UPDATE cga105g2.store set STORE_ETIME=?,STORE_TABLE=?,STORE_ETABLE=? where STORE_ID = ?";
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setString(1, storeEtime);
            pstmt.setInt(2, storeTable);
            pstmt.setInt(3, storeEtable);
            pstmt.setInt(4, storeId);
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }

    }

    @Override
    public Store signin(String storeAcc, String storePwd) {
        String sql = "SELECT * FROM cga105g2.store where STORE_ACC = ? AND STORE_PWD = ?";
        Store store = new Store();
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setString(1, storeAcc);
            pstmt.setString(2, storePwd);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // memberVO 也稱為 Domain objects
                store.setStoreId(rs.getInt("STORE_ID"));
                store.setStoreStatus(rs.getInt("STORE_STATUS"));
                store.setStoreName(rs.getString("STORE_NAME"));
            } else {
                store.setStoreId(0);
            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }
        return store;
    }
    @Override
    public List<Store> getBySsta(Integer sta) {
        String sql = "SELECT * FROM cga105g2.store where STORE_STATUS=?";
        List<Store> list = new ArrayList<Store>();
        Store store = new Store();
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1,sta);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // memberVO 也稱為 Domain objects
                store = new Store();
                store.setStoreId(rs.getInt("STORE_ID"));
                store.setEmpId(rs.getInt("EMP_ID"));
                store.setStoreStatus(rs.getInt("STORE_STATUS"));
                store.setStoreName(rs.getString("STORE_NAME"));
                store.setStoreCity(rs.getString("STORE_CITY"));
                store.setStoreDistrict(rs.getString("STORE_DISTRICT"));
                store.setStoreAcc(rs.getString("STORE_ACC"));
                store.setStoreComAddress(rs.getString("STORE_COM_ADDRESS"));
                store.setStorePhone2(rs.getString("STORE_PHONE2"));
                store.setStoreMail(rs.getString("STORE_MAIL"));
                store.setStoreComId(rs.getString("STORE_COM_ID"));
                store.setStoreTwId(rs.getString("STORE_TW_ID"));
                list.add(store); // Store the row in the list
            }
            return list;
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
            // Clean up JDBC resources
        }
    }

    @Override
    public List<Store> getAllByAddress(String storeCity, String storeDistrict) {
        String sql = "SELECT * FROM cga105g2.store where STORE_CITY = ? AND STORE_DISTRICT = ?";
        List<Store> list = new ArrayList<Store>();
        Store store = null;
        storeCity=storeCity.replace("臺", "台");
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setString(1, storeCity);
            pstmt.setString(2, storeDistrict);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // memberVO 也稱為 Domain objects
                store = new Store();
                store.setStoreId(rs.getInt("STORE_ID"));
                store.setStoreStatus(rs.getInt("STORE_STATUS"));
                store.setStoreName(rs.getString("STORE_NAME"));
                store.setStoreCity(rs.getString("STORE_CITY"));
                store.setStoreDistrict(rs.getString("STORE_DISTRICT"));
                store.setStoreAddress(rs.getString("STORE_ADDRESS"));
                store.setStoreUrl(rs.getString("STORE_URL"));
                list.add(store);
            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }
        return list;


    }

    @Override
    public void update2(Store Store) {
        String sql = "UPDATE cga105g2.store set STORE_PHONE1=?,STORE_HOURS=?,STORE_ADDRESS=?,STORE_WEB=?,STORE_ACC=?,STORE_MAIL=?,STORE_COM_ID=?,STORE_COM_ADDRESS=?,"
                + "STORE_TW_ID=?,STORE_PHONE2=?,STORE_TEXT=? where STORE_ID = ?";
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setString(1, Store.getStorePhone1());
            pstmt.setString(2, Store.getStoreHours());
            pstmt.setString(3, Store.getStoreAddress());
            pstmt.setString(4, Store.getStoreWeb());
            pstmt.setString(5, Store.getStoreAcc());
            pstmt.setString(6, Store.getStoreMail());
            pstmt.setString(7, Store.getStoreComId());
            pstmt.setString(8, Store.getStoreComAddress());
            pstmt.setString(9, Store.getStoreTwId());
            pstmt.setString(10, Store.getStorePhone2());
            pstmt.setString(11, Store.getStoreText());
            pstmt.setInt(12, Store.getStoreId());
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }


    }

	@Override
	public void update3(Store store) {
		String sql = "UPDATE cga105g2.store set STORE_PWD=? where STORE_ACC=?";
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setString(1, store.getStorePwd());
			pstmt.setString(2, store.getStoreAcc());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		
	}

	@Override
	public void update4(Store store) {
		String sql = "UPDATE cga105g2.store set STORE_PWD=? where STORE_ID=?";
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setString(1, store.getStorePwd());
			pstmt.setInt(2, store.getStoreId());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		
	}
	@Override
	public void updateplan(Integer storeId,Integer plan) {
		String sql = "UPDATE cga105g2.store set STORE_PLAN=? ,STORE_NPLAN=? where STORE_ID=?";
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, plan);
            pstmt.setInt(2, plan);
			pstmt.setInt(3, storeId);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}
	@Override
	public void inserts(Store Store) {
		String sql = "UPDATE cga105g2.store set STORE_PHONE1=?,STORE_ACC=?,STORE_PWD=?,STORE_COM_ADDRESS=?,STORE_TW_ID=?,STORE_PHONE2=?,STORE_STATUS=? where STORE_ID = ?";
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setString(1, Store.getStorePhone1());
            pstmt.setString(2, Store.getStoreAcc());
            pstmt.setString(3, Store.getStorePwd());
            pstmt.setString(4, Store.getStoreComAddress());
            pstmt.setString(5, Store.getStoreTwId());
            pstmt.setString(6, Store.getStorePhone2());
            pstmt.setInt(7, Store.getStoreStatus());
            pstmt.setInt(8, Store.getStoreId());
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }
		
	}

	@Override
	public boolean getByAcc(String storeacc) {
		String sql = "SELECT * FROM cga105g2.store where STORE_ACC = ?";
        Store store = null;
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setString(1, storeacc);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // memberVO 也稱為 Domain objects
                store = new Store();
                store.setStoreId(rs.getInt("STORE_ID"));
            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        }
		if(store == null) {
        return false;
		}else {
			return true;
		}
	}
    public Integer getByplan() {
        String sql = "SELECT * FROM cga105g2.store where STORE_NPLAN like ?";
        Store store = new Store();
        try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1,3);
            ResultSet rs = pstmt.executeQuery();
            Integer q=0;
            while (rs.next()) {
                q+=1;
            }
            Integer orz=10-q;
            return orz;
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
            // Clean up JDBC resources
        }
    }

}








