package com.example.Repository;

import com.example.Model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EmployeeInMemoryRepository implements EmployeeRepository {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeInMemoryRepository.class);
    private final Map<Integer, Employee> employeeMap = new ConcurrentHashMap<>();

    @Override
    public void addEmployee(Employee employee) {
        List<Employee> employees = getAllEmployees();

        int maxId = 0;

        for (Employee emp : employees) {
            if (emp.getEmpId() > maxId) {
                maxId = emp.getEmpId();
            }
        }


        employee.setEmpId(maxId + 1);
        employeeMap.put(employee.getEmpId(), employee);
    }

    @Override
    public void deleteEmployee(int empId) {
        employeeMap.remove(empId);
        logger.info("hello hi hi");
    }


    @Override
    public void updateEmployee(int empId, Employee employee) {
        employeeMap.put(empId, employee);
    }

    @Override
    public Employee getEmployeeById(int empId) {
        logger.info("This is the employee");
        return employeeMap.get(empId);

    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeMap.values());
    }
}
