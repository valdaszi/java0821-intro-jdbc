package lt.bit.java2.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class SalaryPK implements Serializable {

    private Employee employee;
    private LocalDate fromDate;

    private SalaryPK() {}

    public SalaryPK(Employee employee, LocalDate fromDate) {
        this.employee = employee;
        this.fromDate = fromDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }
}
