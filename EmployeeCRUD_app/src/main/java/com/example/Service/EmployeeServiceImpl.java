package com.example.Service;

import com.example.Model.Employee;
import com.example.Repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
//     public EmployeeServiceImpl(){}
    private final EmployeeRepository employeeRepository;
//    private static int idCounter=0;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void addEmployee(Employee employee) {
//         employee.setEmpId(generateId());
        logger.info("Adding employee: {}", employee);
        employeeRepository.addEmployee(employee);
        logger.info("Employee added successfully: {}", employee);
    }

//    private synchronized int generateId()
//    {
//        return ++idCounter;
//    }
    @Override
    public void updateEmployee(int empId,Employee employee) {
        logger.info("Updating employee: {}", employee);
        employeeRepository.updateEmployee(empId,employee);
        logger.info("Employee updated successfully: {}", employee);
    }

    @Override
    public void deleteEmployee(int empId) {
        logger.info("Deleting employee with ID: {}", empId);
        employeeRepository.deleteEmployee(empId);
        logger.info("Employee deleted successfully with ID: {}", empId);
    }

    @Override
    public Employee getEmployeeById(int empId) {
        logger.info("Fetching employee with ID: {}", empId);
        Employee employee = employeeRepository.getEmployeeById(empId);
        if (employee != null) {
            logger.info("Employee found: {}", employee);
        } else {
            logger.warn("Employee with ID: {} not found", empId);
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
//        logger.info("Fetching all employees");
        List<Employee> employees = employeeRepository.getAllEmployees();
//        logger.info("Fetched {} employees", employees.size());
        return employees;
    }
}
