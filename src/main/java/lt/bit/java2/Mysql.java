package lt.bit.java2;

import lt.bit.java2.entities.Employee;
import lt.bit.java2.entities.Salary;
import lt.bit.java2.services.ConnectionsManager;
import lt.bit.java2.services.EmployeeService;
import lt.bit.java2.services.EntityManagerUtil;

import javax.persistence.EntityManager;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Testinis uždavinukas parodantis kaip prisijungti prie MySQL DB su JDBC
 * @author Vycka
 * @version 0.1
 */
public class Mysql {

    public static void main(String[] args) {

        System.out.println("args = " + args.length);
        for (String a : args) {
            System.out.println(a);
        }

//        Employee employee = employeeService.getEmployee(9);
//        if (employee != null) throw new ArithmeticException("Kazkas negerai #9");
//
//        employee = employeeService.getEmployee(1);
//        if (employee == null) throw new ArithmeticException("Kazkas negerai #1");
//        employee.setFirstName("Agurkas");
//        employeeService.updateEmployee(1, employee);
//
//        employee = employeeService.getEmployee(1);
//        System.out.println(employee);
//
//        System.out.println("***");
//
//        List<Employee> employees = employeeService.getEmployees(LocalDate.of(1999, 1, 1),  3, 0);
//        System.out.println(employees);
//
//        System.out.println("***");
//
//        employees = employeeService.getEmployees(LocalDate.of(2001, 12, 31), 5, 1000);
//        System.out.println(employees);

//        EmployeeService employeeService = new EmployeeService();
//        test(employeeService);
//
//        employeeService.setUseConnectionPool(true);
//        test(employeeService);

//        EmployeeService employeeService = new EmployeeService();
//        Employee emp10001 = employeeService.getEmployee(10001);
//        System.out.println(emp10001);

        selectExample(1);

        updateExample(1, "Burokas");
        selectExample(1);

        updateExample(1, "Morkis");
        selectExample(1);

        selectExample(1);
        selectExample(10001);

        createExample();
        selectExample(2);

        deleteExample(2);
        selectExample(2);
    }

    static void selectExample(int empNo) {
        System.out.println();
        System.out.println("*** selectExample *** " + empNo);
        EntityManager em = EntityManagerUtil.getEntityManager();
        Employee employee = em.find(Employee.class, empNo);
        System.out.println(employee);
        em.close();
    }

    static void updateExample(int empNo, String firstName) {
        System.out.println();
        System.out.println("*** updateExample *** " + empNo + " " + firstName);
        EntityManager em = EntityManagerUtil.getEntityManager();
        Employee employee = em.find(Employee.class, empNo);
        System.out.println(employee);

        try {
            em.getTransaction().begin();
            employee.setFirstName(firstName);
            em.persist(employee);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        em.close();
    }

    static void createExample() {
        System.out.println();
        System.out.println("*** createExample ***");
        Employee employee = new Employee();
        employee.setEmpNo(2);
        employee.setFirstName("Žiurkė");
        employee.setLastName("Ėdžiūnė");
        employee.setBirthDate(LocalDate.of(1999, 12, 31));
        employee.setHireDate(LocalDate.of(2019, 6, 24));

        EntityManager em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();

        em.merge(employee);

        em.getTransaction().commit();
        em.close();
    }

    static void deleteExample(int empNo) {
        System.out.println();
        System.out.println("*** deleteExample *** " + empNo);
        EntityManager em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();

        Employee employee = em.find(Employee.class, empNo);
        if (employee != null) {
            em.remove(employee);
        }

        em.getTransaction().commit();
        em.close();
    }

    static void test(EmployeeService employeeService) {
        List<Employee> employees = new ArrayList<>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 999; i++) {
            employees.add(employeeService.getEmployee(10001 + i));
        }
        long stop = System.currentTimeMillis();

        System.out.println(employees.size() + " in " + (stop - start) + "ms");
    }
}
