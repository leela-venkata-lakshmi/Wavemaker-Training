//package com.example.Repository;
//
//import com.example.Model.Employee;
//import com.example.Service.EmployeeService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class EmployeeFileRepository implements EmployeeRepository{
//    private static final Logger log = LoggerFactory.getLogger(EmployeeFileRepository.class);
//    private final String fileName;
//
//    public EmployeeFileRepository(String fileName)
//    {
//        this.fileName=fileName;
//    }
//
//    /*
//    * BufferedWriter is a class in java which is used as a temporary storage to reduce IO operations wrapped
//    * around FileWriter which writes the characters into a file.
//     */
//
//    @Override
//    public void addEmployee(Employee employee) {
//        try(BufferedWriter writer= new BufferedWriter(new FileWriter(fileName,true))){
//            writer.write(employee.toString() +"\n");
//            log.info("File added successfully.");
//        }catch (IOException e)
//        {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public List<Employee> getAllEmployees() {
//        List<Employee> employees=new ArrayList<>();
//        String line;
//        try(BufferedReader reader=new BufferedReader(new FileReader(fileName))){
//            while ((line= reader.readLine())!=null)
//            {
//                log.info("********{}",line);
//                Employee employee=Employee.fromString(line);
//                employees.add(employee);
//            }
//        }catch (IOException e)
//        {
//             throw new RuntimeException(e);
//        }
//        return employees;
//    }
//
//    @Override
//    public void deleteEmployee(int empId) {
//        List<Employee> employees=getAllEmployees();
//        employees.removeIf(emp->  emp.getEmpId()==empId );
//        writeToFile(employees);
//    }
//
//    @Override
//    public Employee getEmployeeById(int empId) {
//       return getAllEmployees().stream().filter(emp -> emp.getEmpId() == empId).findFirst().orElse(null);
//    }
//
//    @Override
//    public void updateEmployee(int empId,Employee employee) {
//        List<Employee> employeeList=getAllEmployees();
//    }
//
//
//    private void writeToFile(List<Employee> employees) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
//            for (Employee employee : employees) {
//                writer.write(employee.toString() + "\n");
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Error writing employees to file", e);
//        }
//    }
//}
//
//
//
package com.example.Repository;

import com.example.Model.Employee;
import com.example.Service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFileRepository implements EmployeeRepository {
    private static final Logger log = LoggerFactory.getLogger(EmployeeFileRepository.class);
    private final String fileName;

    public EmployeeFileRepository(String fileName) {
        this.fileName = fileName;
    }

    /*
     * BufferedWriter is a class in java which is used as a temporary storage to reduce IO operations wrapped
     * around FileWriter which writes the characters into a file.
     */

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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(employee.toString() + "\n");
            log.info("Employee added successfully: {}", employee);
        } catch (IOException e) {
            log.error("Error adding employee: {}", employee, e);
            throw new RuntimeException("Error adding employee to file", e);
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                log.info("Reading line: {}", line);
                Employee employee = Employee.fromString(line);
                employees.add(employee);
            }
        } catch (IOException e) {
            log.error("Error reading employees from file", e);
            throw new RuntimeException("Error reading employees from file", e);
        }
        return employees;
    }

    @Override
    public void deleteEmployee(int empId) {
        List<Employee> employees = getAllEmployees();
        boolean removed = employees.removeIf(emp -> emp.getEmpId() == empId);
        if (removed) {
            log.info("Employee with ID {} deleted successfully.", empId);
        } else {
            log.warn("Employee with ID {} not found.", empId);
        }
        writeToFile(employees);
    }

    @Override
    public Employee getEmployeeById(int empId) {
        return getAllEmployees().stream().filter(emp -> emp.getEmpId() == empId).findFirst().orElse(null);
    }

    @Override
    public void updateEmployee(int empId, Employee updatedEmployee) {
        List<Employee> employees = getAllEmployees();
        boolean updated = false;

        for (int i = 0; i < employees.size(); i++) {
            Employee currentEmployee = employees.get(i);
            if (currentEmployee.getEmpId() == empId) {
                employees.set(i, updatedEmployee);
                updated = true;
                log.info("Employee with ID {} updated successfully.", empId);
                break;
            }
        }

        if (!updated) {
            log.warn("Employee with ID {} not found. Update operation failed.", empId);
        } else {
            writeToFile(employees);
        }
    }

    private void writeToFile(List<Employee> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Employee employee : employees) {
                writer.write(employee.toString() + "\n");
            }
            log.info("All employees written to file successfully.");
        } catch (IOException e) {
            log.error("Error writing employees to file", e);
            throw new RuntimeException("Error writing employees to file", e);
        }
    }
}
