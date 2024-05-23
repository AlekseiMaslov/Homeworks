package org.example.entitites;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "department")
public class Department {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "department_name")
  private String name;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name="employee_departments",
          joinColumns=  @JoinColumn(name="department_id", referencedColumnName="id"),
          inverseJoinColumns= @JoinColumn(name=" employee_id", referencedColumnName="id") )
  private List<Employee> employees = new ArrayList<Employee>();
}
