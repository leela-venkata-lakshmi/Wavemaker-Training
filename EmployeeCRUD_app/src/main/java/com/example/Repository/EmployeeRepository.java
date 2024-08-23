package com.example.Repository;
import com.example.Model.Employee;

import java.util.List;


public interface EmployeeRepository {

    void addEmployee(Employee employee);
    void updateEmployee(int empId,Employee employee);
    void deleteEmployee(int empId);
    Employee getEmployeeById(int empId);
    List<Employee> getAllEmployees();


}
