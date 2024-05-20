package task1;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private static final String EMPLOYEE_DATA_QUERY =
            "SELECT employee.last_name, employee.first_name, employee.birth_date, " +
                    "department.department_name, position.position_name, employee_data.salary " +
                    "FROM employee_data JOIN employee ON employee.id = employee_data.employee_id " +
                    "JOIN department ON department.id = employee_data.department_id " +
                    "JOIN position ON position.id = employee_data.position_id";
    private static final String SALARY_UPDATE_QUERY =
            "UPDATE employee_data " +
                    "SET salary = salary + ?";
    private static final String INSERT_EMPLOYEE_QUERY =
            "INSERT INTO employee (id, last_name, first_name, birth_date) " +
                    "VALUES (?, ?, ?, ?)";
    private static final String DELETE_EMPLOYEE_QUERY =
            "DELETE FROM employee " +
                    "WHERE id = ?";

    public List<Employee> getEmployeeData() {
        List<Employee> employees = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(EMPLOYEE_DATA_QUERY)
        ) {
            while (rs.next()) {
                Employee e = new Employee();
                e.setFirstName(rs.getString("first_name"));
                e.setLastName(rs.getString("last_name"));
                e.setBirthDate(rs.getString("birth_date"));
                e.setDepartment(rs.getString("department_name"));
                e.setPosition(rs.getString("position_name"));
                e.setSalary(rs.getString("salary"));

                employees.add(e);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return employees;
    }

    public void updateSalary(int newSalary) {
        try (
                Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD);
                PreparedStatement ps = connection.prepareStatement(SALARY_UPDATE_QUERY);
        ) {
            ps.setInt(1, newSalary);
            ps.executeQuery();
            System.out.println("Data has been updated");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertEmployee(int id, String lastName, String firstName, LocalDate birthDate) {
        try (
                Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD);
                PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYEE_QUERY);
        ) {
            ps.setInt(1, id);
            ps.setString(2, lastName);
            ps.setString(3, firstName);
            ps.setDate(4, Date.valueOf(birthDate));
            int numberOfCreatedRows = ps.executeUpdate();
            System.out.println(numberOfCreatedRows + " rows have been inserted");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void deleteEmployee(int id) {
        try (
                Connection connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD);
                PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEE_QUERY);
        ) {
            ps.setInt(1, id);
            int numberOfCreatedRows = ps.executeUpdate();
            System.out.println(numberOfCreatedRows + " rows have been deleted");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
