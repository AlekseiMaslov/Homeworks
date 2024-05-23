package org.example.entitites;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "employee")
public class Employee {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @OneToOne(mappedBy = "employee")
  private EmployeeData employeeData;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name="employee_departments",
          joinColumns=  @JoinColumn(name="employee_id", referencedColumnName="id"),
          inverseJoinColumns= @JoinColumn(name="department_id", referencedColumnName="id") )
  private List<Department> departments = new ArrayList<Department>();

}
