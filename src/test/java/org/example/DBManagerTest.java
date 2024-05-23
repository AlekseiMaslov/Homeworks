package org.example;

import org.example.entitites.EmployeeData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DBManagerTest {

  private DBHelper helper = new DBHelper();
  private DBManager manager = new DBManager();

  @BeforeEach
  void init() {
    SessionFactory factory = new Configuration()
            .configure()
            .buildSessionFactory();

    helper.initDb();
    manager.setFactory(factory);
  }

  @AfterEach
  void clear() {
    helper.dropDb();
  }

  @Test
  void getAllEmployees_checkCorrectResult() {
    //WHEN
    List<EmployeeData> allEmployees = manager.getAllEmployees();

    //THEN
    assertEquals(4, allEmployees.size());
  }

  @Test
  void deleteEmployeeData_whenDelete_thenEmployeeDeleted() {
    //WHEN
    manager.deleteEmployeeData(1);

    //THEN
    List<EmployeeData> employees = manager.getAllEmployees();
    assertEquals(3, employees.size());
  }

  @Test
  void addNewEmployee_whenAdded_thenGetFiveEmployees() {
    //WHEN
    manager.addNewEmployee();

    //THEN
    List<EmployeeData> employees = manager.getAllEmployees();
    assertEquals(5, employees.size());
    EmployeeData newEmployee = employees.get(4);
    assertEquals(555, newEmployee.getSalary());
    assertEquals(5, newEmployee.getId());
    assertEquals(newEmployee.getEmployee().getFirstName(), "NEW NAME");
  }

  @Test
  void changeEmployee_whenEmployeeChanged_thenNewNameAdded() {
    //WHEN
    manager.changeEmployee();

    //THEN
    EmployeeData employeeData = manager.getAllEmployees().get(0);
    assertEquals("UPDATED NAME", employeeData.getEmployee().getFirstName());
  }
}