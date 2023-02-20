package com.foodorder.model.Reserva.dao;

import com.foodorder.model.Reserva.pojo.Reserva;

import java.util.List;
import java.util.Map;


public interface ReservaDAO_interface {
    public void insert(Reserva reservaVO);
    public void insertToSta(Reserva reservaVO);
    public void update(Reserva reservaVO);
    public Reserva getById(Integer REN_ID);
    public List<Reserva> getAll();
    public List<Reserva> getByStoreIdRendate(Integer storeid, String rendate, String rentime, Integer renstatus);
    public void insertWithReservaDetails(Reserva reservaVO, List<Map> list);
    public List<Reserva> getBymemIdrenStatus(Integer memid, Integer renstatus);
    public void updateRenstatusByRenid(Integer renid, Integer renstatus);
	public List<Reserva> getBymemId(Integer memid);
	public List<Reserva> getBystoreId(Integer storeid);
	public List<Reserva> getBystoreIdrenStatus(Integer storeid, Integer renstatus);

    public Reserva gettable(Integer id);
}
