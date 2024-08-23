package com.example.Controller;

import com.example.Model.Employee;
import com.example.Service.EmployeeService;
import com.example.Service.EmployeeServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EmployeeController {
    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    public void addEmployee(Employee employee){
        logger.info("Adding employee: {}",employee);
        employeeService.addEmployee(employee);
        logger.info("Employee added successfully: {}",employee);
    }

    public void updateEmployee(int updateId, Employee employee){
        logger.info("Updating employee : {}",employee);
        employeeService.updateEmployee(updateId,employee);
        logger.info("Updated successfully employee : {}",employee);
    }

    public void deleteEmployee(int empId){
        logger.info("Deleting employee with Id: {}",empId);
        employeeService.deleteEmployee(empId);
        logger.info("Employee with Id: {} deleted successfully.",empId);
    }

    public Employee getEmployeeById(int empId){
        logger.info("Fetching employee with Id: {}",empId);
        Employee employee = employeeService.getEmployeeById(empId);
        if (employee != null) {
            logger.info("Employee found: {}", employee);
        } else {
            logger.warn("Employee with ID: {} not found", empId);
        }
        return employee;
    }

    public List<Employee> getAllEmployees(){
//        logger.info("Fetching all employees");
        List<Employee> employees = employeeService.getAllEmployees();
//        logger.info("Fetched {} employees", employees.size());
        return employees;
    }
}
