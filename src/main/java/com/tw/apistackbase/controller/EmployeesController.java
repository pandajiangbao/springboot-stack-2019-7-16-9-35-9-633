package com.tw.apistackbase.controller;

import com.tw.apistackbase.Entity.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    static List<Employee> employees = new ArrayList<>();
    static {
        employees.add(new Employee(1, "panda", 22, "male", 9999));
        employees.add(new Employee(2, "222", 223, "male", 9999));
        employees.add(new Employee(3, "333", 223, "female", 9999));
        employees.add(new Employee(4, "444", 333, "female", 9999));
        employees.add(new Employee(5, "555", 123, "female", 9999));
        employees.add(new Employee(6, "666", 34, "male", 9999));
        employees.add(new Employee(7, "777", 45, "male", 9999));
        employees.add(new Employee(8, "888", 11, "male", 9999));
    }

    @GetMapping()
    public ResponseEntity getAllEmployees(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String gender) {
        if(page!=null&&pageSize!=null){
            int endIndex=page*pageSize;
            if ((page-1)*pageSize>employees.size()) return ResponseEntity.badRequest().build();
            if (page*pageSize>employees.size()){
                endIndex=employees.size();
            }
            return ResponseEntity.ok().body(employees.subList((page-1)*pageSize,endIndex));
        }
        if (gender!=null&&gender.equals("male")){
            return ResponseEntity.ok().body(employees.stream().filter(item-> item.getGender().equals("male")).collect(Collectors.toList()));
        }
        return ResponseEntity.ok().body(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity getEmployeeById(@PathVariable Integer id) {
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                return ResponseEntity.ok().body(employee);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody Employee employee) {
        employees.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                employees.remove(employee);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Employee employee, @PathVariable Integer id) {
        for (Employee temp : employees) {
            if (temp.getId().equals(id)) {
                employees.set(employees.indexOf(temp), employee);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
