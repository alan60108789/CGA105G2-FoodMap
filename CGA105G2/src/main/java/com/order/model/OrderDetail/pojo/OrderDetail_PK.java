package com.order.model.OrderDetail.pojo;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Table(catalog = "cga105g2",name = "order_detail")
public class OrderDetail_PK implements Serializable {
    @Column(name = "ORDER_ID")
    public Integer orderId;
    @Column(name = "GOODS_ID")
    public Integer goodsId;
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderDetail_PK pk = (OrderDetail_PK) o;
        return Objects.equals(orderId, pk.goodsId)
                && Objects.equals(goodsId, pk.goodsId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(orderId, goodsId);
    }

}
