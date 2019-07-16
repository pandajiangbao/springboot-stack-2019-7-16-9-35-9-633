package com.tw.apistackbase.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @JsonIgnore
    private Integer id;
    private String companyName;
    private Integer employeesNumber;
    private List<Employee> employees;
}
