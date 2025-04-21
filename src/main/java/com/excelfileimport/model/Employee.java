package com.excelfileimport.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;

    private String empFirstName;

    private String empLastName;

    @Temporal(TemporalType.DATE)
    private Date empDateofBirth;

    private double empSalary;

    private String empAddress;
}
