package com.saveArt.model.SaveArt.pojo;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
@Table(catalog = "cga105g2",name = "save_art")
public class SaveArt_PK implements Serializable {
    @Column(name = "ART_ID")
    public Integer artId;
    @Column(name = "MEM_ID")
    public Integer memId;
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SaveArt_PK pk = (SaveArt_PK) o;
        return Objects.equals(artId, pk.memId)
                && Objects.equals(memId, pk.memId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(artId, memId);
    }

}
