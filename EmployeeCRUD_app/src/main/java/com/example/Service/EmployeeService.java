package com.example.Service;

import com.example.Model.Employee;

import java.util.List;

public interface EmployeeService {
    void addEmployee(Employee employee);

    void updateEmployee(int empId,Employee employee);

    void deleteEmployee(int empId);

    Employee getEmployeeById(int empId);

    List<Employee> getAllEmployees();
}
