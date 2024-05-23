package org.example.entitites;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employee_data")
public class EmployeeData {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "salary")
  private Integer salary;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "employee_id", referencedColumnName = "id")
  private Employee employee;
}
