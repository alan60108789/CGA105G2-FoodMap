package com.foodorder.model.ReservaReservaDetailMeal.dao;

import java.util.List;

import com.foodorder.model.ReservaDetail.pojo.ReservaDetail;
import com.foodorder.model.ReservaReservaDetailMeal.pojo.ReservaReservaDetailMeal;



public interface ReservaReservaDetailMealDAO_interface {

    public List<ReservaReservaDetailMeal> getById(Integer id, String chooseId);

}
