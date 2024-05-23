package org.example;

import org.example.entitites.Employee;
import org.example.entitites.EmployeeData;

import java.util.List;

public class View {

  private static final String EMPLOYEE_DATA = "id - %s, name - %s, last name - %s, salary - %s, departments - ";

  public void printEmployeeData(List<EmployeeData> employeeData) {
    employeeData.forEach(ed -> {
      Employee employee = ed.getEmployee();
      System.out.println("-----------------");
      System.out.println(String.format(EMPLOYEE_DATA,
              employee.getId(), employee.getFirstName(), employee.getLastName(), ed.getSalary()));
      employee.getDepartments().forEach(d -> System.out.println(d.getName()));
    });
  }
}
