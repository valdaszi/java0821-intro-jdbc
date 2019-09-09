package lt.bit.java2.entities;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Employee entity
 * <ul>
 *     <li>Pirmas</li>
 *     <li>antras</li>
 * </ul>
 *  <br>
 *     <strong>Ohoho</strong> o cia paprastas tekstas
 *     <pre>
 *         ssdsdsdsd sdsd
 *         sdsdsdsd sdsdsdsd sdsdsd
 *         sdsdsds
 *     </pre>
 */
public class Employee {

    /**
     * Primary key
     */
    private Integer empNo;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private LocalDate hireDate;
    private String gender;

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
}
