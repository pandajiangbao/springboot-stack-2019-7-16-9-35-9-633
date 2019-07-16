package com.tw.apistackbase.controller;

import com.tw.apistackbase.Entity.Company;
import com.tw.apistackbase.Entity.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    static List<Company> companies=new ArrayList<>();
    static{
        companies.add(new Company(1,"alibaba",EmployeesController.employees.size(),EmployeesController.employees));
        companies.add(new Company(2,"apple",EmployeesController.employees.size(),EmployeesController.employees));
        companies.add(new Company(3,"weiruan",EmployeesController.employees.size(),EmployeesController.employees));
        companies.add(new Company(4,"onepiece",EmployeesController.employees.size(),EmployeesController.employees));
        companies.add(new Company(5,"banana",EmployeesController.employees.size(),EmployeesController.employees));
        companies.add(new Company(6,"monkey",EmployeesController.employees.size(),EmployeesController.employees));
        companies.add(new Company(7,"flex",EmployeesController.employees.size(),EmployeesController.employees));
        companies.add(new Company(8,"search",EmployeesController.employees.size(),EmployeesController.employees));
    }
    @GetMapping()
    public ResponseEntity getAllCompanies(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize) {
        if(page!=null&&pageSize!=null){
            return ResponseEntity.ok().body(companies.subList((page-1)*pageSize,page*pageSize));
        }
        return ResponseEntity.ok().body(companies);
    }
    @GetMapping("/{id}")
    public ResponseEntity getCompanyById(@PathVariable Integer id) {
        for (Company company : companies) {
            if (company.getId().equals(id)) {
                return ResponseEntity.ok().body(company);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity getEmployeesInCompanyById(@PathVariable Integer id) {
        for (Company company : companies) {
            if (company.getId().equals(id)) {
                return ResponseEntity.ok().body(company.getEmployees());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody Company company) {
        companies.add(company);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        for (Company company : companies) {
            if (company.getId().equals(id)) {
                companies.remove(company);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Company company, @PathVariable Integer id) {
        for (Company temp : companies) {
            if (temp.getId().equals(id)) {
                companies.set(companies.indexOf(temp), company);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
