package com.emp.model.EmployeeRoot.pojo;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
@Table(catalog = "cga105g2",name = "employee_root")
public class EmployeeRoot_PK implements Serializable {
    @Column(name = "EMP_ID")
    public Integer empId;
    @Column(name = "ROOT_ID")
    public Integer rootId;
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EmployeeRoot_PK pk = (EmployeeRoot_PK) o;
        return Objects.equals(empId, pk.rootId)
                && Objects.equals(rootId, pk.rootId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(empId, rootId);
    }

}
