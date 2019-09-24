package lt.bit.java2.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "employees")
public class Employee {

    /**
     * Primary key
     */
    @Id
    @Column(name = "emp_no")
    private Integer empNo;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(columnDefinition = "enum (Types#CHAR)")
    private String gender;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Collection<Salary> salaries;

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Collection<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(Collection<Salary> salaries) {
        this.salaries = salaries;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empNo=" + empNo +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", hireDate=" + hireDate +
                ", gender='" + gender + '\'' +
                ", salaries=" + getSalaries() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(empNo, employee.empNo) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(birthDate, employee.birthDate) &&
                Objects.equals(hireDate, employee.hireDate) &&
                Objects.equals(gender, employee.gender); // &&
//                Objects.equals(salaries, employee.salaries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, firstName, lastName, birthDate, hireDate, gender); //, salaries);
    }
}
