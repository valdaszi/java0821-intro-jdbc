package lt.bit.java2.entities;

import java.time.LocalDate;

public class Salary {
//    private Integer empNo;
    private Employee employee;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer salary;


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

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    /**
     * Nereikia isvesti employee, nes tada gausis amzinas ciklas!!!
     * @return
     */
    @Override
    public String toString() {
        return "Salary{" +
                "fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", salary=" + salary +
                '}';
    }
}
