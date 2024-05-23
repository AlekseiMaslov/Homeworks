package org.example;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entitites.Department;
import org.example.entitites.Employee;
import org.example.entitites.EmployeeData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DBManager {

  private static final String FROM_EMPLOYEE_DATA = "from EmployeeData";
  private SessionFactory factory;

  public List<EmployeeData> getAllEmployees() {
    try (Session s = factory.openSession()) {
      Query<EmployeeData> query = s.createQuery(FROM_EMPLOYEE_DATA, EmployeeData.class);
      return query.list();
    }
  }

  public void deleteEmployeeData(Integer id) {
    try (Session s = factory.openSession()) {
      s.beginTransaction();
      s.createNativeQuery("DELETE FROM employee_data WHERE employee_id = :id")
              .setParameter("id", id)
              .executeUpdate();
      s.createNativeQuery("DELETE FROM employee WHERE id = :id")
              .setParameter("id", id)
              .executeUpdate();
      s.getTransaction().commit();
    }
  }

  public void addNewEmployee() {
    try (Session s = factory.openSession()) {
      Department d = new Department();
      d.setId(1);
      d.setName("department_1");

      Employee e = new Employee();
      e.setId(5);
      e.setFirstName("NEW NAME");
      e.setLastName("NEW LAST NAME");
      e.setDepartments(List.of(d));

      EmployeeData ed = new EmployeeData();
      ed.setId(5);
      ed.setSalary(555);
      ed.setEmployee(e);

      s.beginTransaction();
      s.save(ed);
      s.getTransaction().commit();
    }
  }


  public void changeEmployee() {
    try (Session s = factory.openSession()) {
      Employee employeeToUpdate = s.get(Employee.class, 1);
      employeeToUpdate.setFirstName("UPDATED NAME");
      s.beginTransaction();
      s.saveOrUpdate(employeeToUpdate);
      s.getTransaction().commit();
    }
  }
}
