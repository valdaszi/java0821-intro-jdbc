package lt.bit.java2.services;

import lt.bit.java2.entities.Employee;
import lt.bit.java2.entities.Salary;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    private static String URL = "jdbc:mysql://localhost:3306/employees?serverTimezone=Europe/Vilnius";
    private static String USER = "root";
    private static String PASSWORD = "mysql123";

    private boolean useConnectionPool;
    private ConnectionsManager connectionsManager;

    public EmployeeService() {
        connectionsManager = new ConnectionsManager();
    }

    public boolean isUseConnectionPool() {
        return useConnectionPool;
    }

    public void setUseConnectionPool(boolean useConnectionPool) {
        this.useConnectionPool = useConnectionPool;
    }

    private Employee mapEmployeeFromResult(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();

        employee.setEmpNo(resultSet.getInt("emp_no"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setGender(resultSet.getString("gender"));
        employee.setHireDate(resultSet.getDate("hire_date").toLocalDate());
        employee.setBirthDate(resultSet.getDate("birth_date").toLocalDate());

        // ar yra duomenys is salary?
        if (resultSet.getDate("from_date") != null) {
            employee.setSalaries(new ArrayList<>());
            do {
                Salary salary = mapSalaryFromResult(resultSet);
                salary.setEmployee(employee);
                employee.getSalaries().add(salary);
            } while(resultSet.next());
        }

        return employee;
    }

    private Salary mapSalaryFromResult(ResultSet resultSet) throws SQLException {
        Salary salary = new Salary();

        salary.setFromDate(resultSet.getDate("from_date").toLocalDate());
        salary.setToDate(resultSet.getDate("to_date").toLocalDate());
        salary.setSalary(resultSet.getInt("salary"));

        return salary;
    }

    /**
     * Išvedme darbuotojus dirbančius nuo tam tikros datos
     * @param date įdarbinimo data
     * @param limit kiek darbuotojų išvesti
     * @param offset nuo kokio darbuotojo
     * @return darbuotoju sarasas
     */
    public List<Employee> getEmployees(LocalDate date, int limit, int offset) {
        final String SQL = "SELECT * FROM employees WHERE hire_date > ? LIMIT ? OFFSET ?";
        try (Connection connection = connectionsManager.getConnection(useConnectionPool);
             PreparedStatement statement = connection.prepareStatement(SQL);
        ) {

            statement.setInt(2, limit);
            statement.setInt(3, offset);
            statement.setDate(1, Date.valueOf(date));

            List<Employee> employees = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employees.add(mapEmployeeFromResult(resultSet));
            }
            return employees;

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println("Kazkas atsitiko: " + e.getMessage());
            return null;
        }
    }


    /**
     * Istraukti is DB informacija apie darbuotoja
     * @param empNo darbuotojo id
     * @return darbuotojo info
     */
    public Employee getEmployee(int empNo) {
        final String SQL = "SELECT e.emp_no, first_name, last_name, birth_date, hire_date, gender, salary, from_date, to_date " +
                " FROM employees e" +
                " LEFT JOIN salaries s ON e.emp_no = s.emp_no " +
                " WHERE e.emp_no = ?";
        try (Connection connection = connectionsManager.getConnection(useConnectionPool);
             PreparedStatement statement = connection.prepareStatement(SQL);
        ) {

            statement.setInt(1, empNo);

            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return mapEmployeeFromResult(resultSet);
//            }
//            return null;
            return resultSet.next() ? mapEmployeeFromResult(resultSet) : null;

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println("Kazkas atsitiko: " + e.getMessage());
            return null;
        }
    }

    /**
     * Pakeisti darbuotojo duomenis
     * @param empNo darbuotojo numeris
     * @param employee nauji duomenys apie darbuotoja
     */
    public void updateEmployee(int empNo, Employee employee) {
        try (Connection connection = connectionsManager.getConnection(useConnectionPool);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE employees SET first_name = ?, last_name = ?, gender = ?, birth_date = ?, hire_date = ? WHERE emp_no = ?")) {

            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getGender());
            preparedStatement.setDate(4, Date.valueOf(employee.getBirthDate()));
            preparedStatement.setDate(5, Date.valueOf(employee.getHireDate()));
            preparedStatement.setInt(6, empNo);

            int r = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createEmployee(Employee employee) {
        try (Connection connection = connectionsManager.getConnection(useConnectionPool);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO employees(first_name, last_name, gender, birth_date, hire_date, emp_no) VALUES(?,?,?,?,?,?)")) {

            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getGender());
            preparedStatement.setDate(4, Date.valueOf(employee.getBirthDate()));
            preparedStatement.setDate(5, Date.valueOf(employee.getHireDate()));
            preparedStatement.setInt(6, employee.getEmpNo());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int empNo) {
        try (Connection connection = connectionsManager.getConnection(useConnectionPool);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employees WHERE emp_no = ?")) {

            preparedStatement.setInt(1, empNo);

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
