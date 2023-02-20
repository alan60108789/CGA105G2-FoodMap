package com.foodorder.model.service;

import com.art.model.Article.dao.ArtDAO_interface;
import com.art.model.Article.dao.impl.ArtJDBCDAO;
import com.art.model.Article.pojo.Article;
import com.code.model.Code.dao.CodeDAO_interface;
import com.code.model.Code.dao.impl.CodeJDBCDAO;
import com.foodorder.model.Meal.dao.MealDAO_interface;
import com.foodorder.model.Meal.dao.impl.MealJDBCDAO;
import com.foodorder.model.Meal.pojo.Meal;
import com.foodorder.model.Reserva.dao.ReservaDAO_interface;
import com.foodorder.model.Reserva.dao.impl.ReservaJDBCDAO;
import com.foodorder.model.Reserva.pojo.Reserva;
import com.foodorder.model.ReservaDetail.dao.ReservaDetailDAO_interface;
import com.foodorder.model.ReservaDetail.dao.impl.ReservaDetailJDBCDAO;
import com.foodorder.model.ReservaDetail.pojo.ReservaDetail;
import com.member.model.Member.dao.MemberDAO_interface;
import com.member.model.Member.dao.impl.MemberDAO;
import com.member.model.Member.pojo.Member;
import com.store.model.Store.dao.StoreDAO_interface;
import com.store.model.Store.dao.impl.StoreDAO;
import com.store.model.Store.pojo.Store;

import java.util.List;
import java.util.Map;

public class FoodorderService {
	private MealDAO_interface dao;
	private StoreDAO_interface storedao;
	private ReservaDAO_interface reservadao;
	private CodeDAO_interface codedao;
	private ReservaDetailDAO_interface reservaDetaildao;
	private ArtDAO_interface artdao;
	private MemberDAO_interface memberdao;

	public FoodorderService() {
//		dao = new MealHibernateDAO();
		dao = new MealJDBCDAO();
		storedao = new StoreDAO();
		reservadao = new ReservaJDBCDAO();
		codedao = new CodeJDBCDAO();
		reservaDetaildao = new ReservaDetailJDBCDAO();
		artdao = new ArtJDBCDAO();
		memberdao = new MemberDAO();
	}
	// 取出所有餐點 且 狀態是上架的
	public List<Meal> getAllStatusOk(Integer storeid) {
		return dao.getByStoreIdStatus(storeid, 1);
	}
	
	// 按下修改 先取出 該筆資料
	public Meal getOneMeal(Integer mealid) {
		return dao.getByMealId(mealid);
	}
	
	// 修改送出按鈕  就是 下架該套餐 且 上架修改套餐的那一筆
	public void updateMeal(Integer mealid, Meal meal) {
//		dao.getByStoreIdStatus(mealid, 1);
//		Meal oldmeal = dao.getByMealId(mealid);
		
		dao.update(mealid, 0);
		dao.insert(meal);
		//交易版本
//		dao.MealUpdateCommit(meal, mealid);

	}
	
	// 刪除按鈕
	public void deleteMeal(Integer mealid) {
		dao.update(mealid, 0);
	}
	
	// 新增按鈕
	public void insertMeal(Meal meal) {
		dao.insert(meal);
	}
	
	// 取得店家資訊
	public Store getStoreInfo(Integer storeId) {

		Store store = storedao.getById(storeId);
		return store;
	}
	// 更新店家資訊
	public void updateStoreInfo(Integer storeId, String tablecount, String orderlimit, String tempstr) {
		storedao.updateordersetting(storeId, tempstr, Integer.parseInt(tablecount), Integer.parseInt(orderlimit));
	}

	// 取得該日該店家所有訂單
	public List<Reserva> getReservabyStoreidRendate(Integer storeid, String rendate, String rentime, Integer renstatus){
		return reservadao.getByStoreIdRendate(storeid, rendate, rentime, renstatus);
	}

	// 利用店家id和餐點狀態撈出所有餐點
	public List<Meal> getAllMealbyStoreidStatus(Integer id, Integer status){
		return dao.getByStoreIdStatus(id, status);
	}

	// 優惠劵金額
	public List<Integer> getCodeMoney(String codeNum,Integer storeId){
		return codedao.getBycodeNum(codeNum, storeId);
	}
	// 優惠劵id
	public List<Integer> getCodeId(String codeNum,Integer storeId){
		return codedao.getCodeId(codeNum,storeId);
	}
	// 新增訂單 及 新增訂單明細
	public void insertReservaReservaDetail(Reserva reservaVO, List<Map> list) {
		reservadao.insertWithReservaDetails(reservaVO, list);
	}

	// 用會員id及訂單狀態取出訂單
	public List<Reserva> getBymemIdrenStatus(Integer memid, Integer renstatus){
		return reservadao.getBymemIdrenStatus(memid, renstatus);
	}

	// 利用訂單id取出該訂單所有餐點編號及數量
	public List<ReservaDetail> getByPK(Integer id, String pk){
		return reservaDetaildao.getByPK(id, pk);
	}
	// 利用餐點編號取出餐點名稱
	public Meal getByMealId(Integer id) {
		return dao.getByMealId(id);
	}
	// 會員查詢已預約 刪除更改狀態
	public void updaterenStatusByrenId(Integer renid, Integer renstatus) {
		reservadao.updateRenstatusByRenid(renid, renstatus);
	}

	// 用會員id取出所有訂單
	public List<Reserva> getReservaBymemId(Integer memid) {
		return reservadao.getBymemId(memid);
	}

	// 利用會員id加店家id 查出所有文章資訊
	public List<Article> getArticleByMemIdStoreId(Integer memId, Integer storeId){
		return artdao.getAllByMemIdStoreId(memId, storeId);
	}


	// 用店家id及訂單狀態取出訂單
	public List<Reserva> getBystoreIdrenStatus(Integer storeid, Integer renstatus){
		return reservadao.getBystoreIdrenStatus(storeid, renstatus);
	}
	// 用店家id取出所有訂單
	public List<Reserva> getReservaBystoreId(Integer storeid) {
		return reservadao.getBystoreId(storeid);
	}
	// 用會員id取得該會員資料
	public Member getmemberById(Integer memId) {
		return memberdao.getById(memId);

	}

}
