package lt.bit.java2.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "salaries")
@IdClass(SalaryPK.class)
public class Salary {

    @Id
    @ManyToOne
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no", nullable = false)
    private Employee employee;

    @Id
    @Column(name = "from_date", insertable = false, updatable = false)
    private LocalDate fromDate;

    @Column(name = "to_date")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salary salary1 = (Salary) o;
        return Objects.equals(employee, salary1.employee) &&
                Objects.equals(fromDate, salary1.fromDate) &&
                Objects.equals(toDate, salary1.toDate) &&
                Objects.equals(salary, salary1.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, fromDate, toDate, salary);
    }
}
