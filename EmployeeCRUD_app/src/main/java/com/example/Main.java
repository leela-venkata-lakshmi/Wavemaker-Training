package com.example;

import com.example.Controller.EmployeeController;
import com.example.Model.Address;
import com.example.Model.Employee;
import com.example.Repository.EmployeeDBRepository;
import com.example.Repository.EmployeeFileRepository;
import com.example.Repository.EmployeeInMemoryRepository;
import com.example.Repository.EmployeeRepository;
import com.example.Service.EmployeeService;
import com.example.Service.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
         EmployeeRepository repository=null;
        EmployeeService service;

        System.out.println("Choose your option");
        System.out.println("1 -> Store in memory");
        System.out.println("2 -> Store in File");
        System.out.println("3 -> Store in DataBase");
        int option= sc.nextInt();
        boolean exit=false;
        if(option==1) {
            repository = new EmployeeInMemoryRepository();

        }else if(option==2){
            repository = new EmployeeFileRepository("employees.txt");

        }else if(option==3){
            repository = new EmployeeDBRepository();

        }else{
            System.out.println("Invalid option");
        }
        logger.info("repository value: {}",repository);
        service =new EmployeeServiceImpl(repository);
        EmployeeController controller=new EmployeeController(service);

        while(!exit) {
            System.out.println("Select your option:");
            System.out.println("1 : Add an employee.");
            System.out.println("2 : Remove an employee");
            System.out.println("3 : Find an employee with ID.");
            System.out.println("4 : Update an employee details");
            System.out.println("5 : Get list of employees.");
            System.out.println("6 : Exit.");

            int choice = sc.nextInt();

            String name,dept,street,state;
            int pinCode;
            Address address;
            Employee employee;

            switch (choice) {
                case 1:
                    System.out.println("Provide Employee Details: ");
                    System.out.print("Enter employee name: ");
                     name = sc.next();
                    System.out.print("Enter department name: ");
                     dept = sc.next();
                    System.out.print("Enter employee street: ");
                     street = sc.next();
                    System.out.print("Enter employee state: ");
                     state = sc.next();
                    System.out.print("Enter employee pincode: ");
                     pinCode = sc.nextInt();
                    System.out.println("Employee added successfully.");
                     address=new Address(street,state,pinCode);
                     employee=new Employee(name,dept,address);
                    controller.addEmployee(employee);
                    logger.info("Employee id {}:",employee.getEmpId());
                    break;
                case 2:
                    System.out.print("Enter employee Id to remove: ");
                    int id = sc.nextInt();
                    System.out.println("Employee removed successfully.");
                    controller.deleteEmployee(id);
                    break;
                case 3:
                    System.out.print("Provide employee Id: ");
                    id = sc.nextInt();
                    Employee employee1=controller.getEmployeeById(id);
                    System.out.printf("%-10s %-12s %-15s %-20s%n","Id" , "Name","Department","Address");
                    System.out.println("------------------------------------------------------------------------------");
                    System.out.printf("%-10s %-12s %-15s %-20s%n",employee1.getEmpId(), employee1.getEmpName(),employee1.getDeptName(),employee1.getAddress().toString());

                    break;
                case 4:
                    System.out.print("Enter employee Id: ");
                    int updateId=sc.nextInt();
                    System.out.print("Enter employee name: ");
                     name = sc.next();
                    System.out.print("Enter department name: ");
                     dept = sc.next();
                    System.out.print("Enter employee street: ");
                     street = sc.next();
                    System.out.print("Enter employee state: ");
                     state = sc.next();
                    System.out.print("Enter employee pincode: ");
                     pinCode = sc.nextInt();
                    System.out.println("Employee added successfully.");
                    address=new Address(street,state,pinCode);
                    Employee e=controller.getEmployeeById(updateId);

                     e.setAddress(address);
                     e.setEmpName(name);
                     e.setDeptName(dept);
                    controller.updateEmployee(updateId,e);
                   logger.info("Employee id {}:",e.getEmpId());
                    break;

                case 5:
                    System.out.println("\n");
                    System.out.println("List of employees:");
                    System.out.printf("%-10s %-12s %-15s %-20s%n","Id" , "Name","Department","Address");
                    System.out.println("------------------------------------------------------------------------------");
                    List<Employee> employees=controller.getAllEmployees();
                    if(employees.isEmpty()){logger.warn("employees list is null");}
                    employees.forEach(emp->
                            System.out.printf("%-10s %-12s %-15s %-20s%n",
                                    emp.getEmpId(), emp.getEmpName(),emp.getDeptName(),emp.getAddress().toString()));
                    System.out.println("\n");
                    break;
                case 6:
                    exit=true;
                    break;
            }

        }

    }
}