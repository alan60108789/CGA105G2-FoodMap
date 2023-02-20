package com.foodorder.model.ReservaDetail.pojo;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
@Table(catalog = "cga105g2",name = "reserva_detail")
public class ReservaDetail_PK implements Serializable {
    @Column(name = "REN_ID")
    public Integer renId;
    @Column(name = "MEAL_ID")
    public Integer mealId;
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReservaDetail_PK pk = (ReservaDetail_PK) o;
        return Objects.equals(renId, pk.mealId)
                && Objects.equals(mealId, pk.mealId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(renId, mealId);
    }

}
