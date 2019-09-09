package lt.bit.java2;

import lt.bit.java2.entities.Employee;
import lt.bit.java2.services.ConnectionsManager;
import lt.bit.java2.services.EmployeeService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

        EmployeeService employeeService = new EmployeeService();
        Employee emp10001 = employeeService.getEmployee(10001);
        System.out.println(emp10001);
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

