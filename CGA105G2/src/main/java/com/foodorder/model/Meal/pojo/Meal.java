package com.foodorder.model.Meal.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Meal {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MEAL_ID")
  private Integer mealId;
  @Column(name = "STORE_ID")
  private Integer storeId;
  @Column(name = "MEAL_NAME")
  private String mealName;
  @Column(name = "MEAL_PRICE")
  private Integer mealPrice;
  @Column(name = "MEAL_STATUS")
  private Integer mealStatus;



	public Meal() {
		super();
	}

	public Meal(Integer mealId, Integer mealStatus) {
		super();
		this.mealId = mealId;
		this.mealStatus = mealStatus;
	}

	public Meal(Integer storeId, String mealName, Integer mealPrice, Integer mealStatus) {
		super();
		this.storeId = storeId;
		this.mealName = mealName;
		this.mealPrice = mealPrice;
		this.mealStatus = mealStatus;
	}

	public Meal(Integer mealId, Integer storeId, String mealName, Integer mealPrice, Integer mealStatus) {
		super();
		this.mealId = mealId;
		this.storeId = storeId;
		this.mealName = mealName;
		this.mealPrice = mealPrice;
		this.mealStatus = mealStatus;
	}

  public Integer getMealId() {
    return mealId;
  }

  public void setMealId(Integer mealId) {
    this.mealId = mealId;
  }


  public Integer getStoreId() {
    return storeId;
  }

  public void setStoreId(Integer storeId) {
    this.storeId = storeId;
  }


  public String getMealName() {
    return mealName;
  }

  public void setMealName(String mealName) {
    this.mealName = mealName;
  }


  public Integer getMealPrice() {
    return mealPrice;
  }

  public void setMealPrice(Integer mealPrice) {
    this.mealPrice = mealPrice;
  }


  public Integer getMealStatus() {
    return mealStatus;
  }

  public void setMealStatus(Integer mealStatus) {
    this.mealStatus = mealStatus;
  }

	@Override
	public int hashCode() {
		return Objects.hash(mealId, mealName, mealPrice, mealStatus, storeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meal other = (Meal) obj;
		return Objects.equals(mealId, other.mealId) && Objects.equals(mealName, other.mealName)
				&& Objects.equals(mealPrice, other.mealPrice) && Objects.equals(mealStatus, other.mealStatus)
				&& Objects.equals(storeId, other.storeId);
	}

	@Override
	public String toString() {
		return "Meal [mealId=" + mealId + ", storeId=" + storeId + ", mealName=" + mealName + ", mealPrice=" + mealPrice
				+ ", mealStatus=" + mealStatus + "]";
	}



}
