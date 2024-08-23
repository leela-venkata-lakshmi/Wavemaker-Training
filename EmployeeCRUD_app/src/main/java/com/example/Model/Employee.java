package com.example.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Employee {
    private static final Logger log = LoggerFactory.getLogger(Employee.class);
    int empId;
    private String empName;
    private String deptName;
    private Address address;

    public Employee(){}
    public Employee(String empName, String deptName, Address address)
    {
        this.empName=empName;
        this.deptName=deptName;
        this.address=address;
    }
    public void setEmpId(int empId) {
//        long Id=System.currentTimeMillis();
       this.empId=empId;
    }

    public int getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + this.empId +
                ", empName='" + empName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", address=" + address +
                '}';
    }

    public static Employee fromString(String str) {
        String[] parts = str.split(",");
        int empId = 0;  // Default value in case of invalid or missing ID
        String name = "";
        String deptName = null;
        Address address = null;

        try {
            // Ensure the parts array has at least one element for empId
            if (parts.length > 0 && parts[0] != null && !parts[0].trim().isEmpty()) {
                empId = Integer.parseInt(parts[0]);
                System.out.println("Parsed number: " + empId);
            } else {
                System.out.println("Employee ID is missing or invalid.");
            }

            // Ensure the parts array has at least two elements for name
            if (parts.length > 1 && parts[1] != null && !parts[1].trim().isEmpty()) {
                name = parts[1];
            } else {
                System.out.println("Employee name is missing.");
            }

            // Ensure the parts array has at least three elements for department name
            if (parts.length > 2 && parts[2] != null && !parts[2].equals("null")) {
                deptName = parts[2];
            }

            // Ensure the parts array has at least four elements for address
            if (parts.length > 3 && parts[3] != null && !parts[3].equals("null")) {
                address = Address.fromString(parts[3]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for employee ID: '" + parts[0] + "'. Please provide a valid number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Input string does not contain enough parts to parse an Employee object.");
        }

        return new Employee(name, deptName, address);
    }

}


