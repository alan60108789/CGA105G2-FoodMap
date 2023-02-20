package com.code.model.MemCode.pojo;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
@Table(catalog = "cga105g2",name = "mem_code")
public class MemCode_PK implements Serializable {
    @Column(name = "CODE_ID")
    public Integer codeId;
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
        MemCode_PK pk = (MemCode_PK) o;
        return Objects.equals(codeId, pk.memId)
                && Objects.equals(memId, pk.memId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(codeId, memId);
    }

}
