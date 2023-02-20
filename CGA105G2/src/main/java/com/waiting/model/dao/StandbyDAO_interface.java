package com.waiting.model.dao;


import com.waiting.model.pojo.Standby;

import java.util.List;

public interface StandbyDAO_interface {
	public Integer insert(Standby standbyVo);

	public void update(Standby standbyVo);

	public void delete(Integer staId);

	public Standby findByPrimaryKey(Integer staId);

	public List<Standby> getAll();

	public Integer standByCount();
	public Integer getStandbyId();
	public void resetStandBy();
}
