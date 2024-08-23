package com.example.Repository;

import com.example.Model.Address;
import com.example.Model.Employee;
import com.example.util.DataBaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDBRepository implements EmployeeRepository {

    private static final Logger log = LoggerFactory.getLogger(EmployeeDBRepository.class);
    Employee employee;
    private Connection getConnection() throws SQLException {
        return DataBaseConnection.connectToDatabase();
    }

    public EmployeeDBRepository()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void addEmployee(Employee employee) {
        String insertEmployeeSQL = "INSERT INTO employee (EMP_NAME, DEPT_NAME,STREET,STATE,PINCODE) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertEmployeeSQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, employee.getEmpName());
            preparedStatement.setString(2, employee.getDeptName());
            preparedStatement.setString(3, employee.getAddress().getStreet());
            preparedStatement.setString(4,employee.getAddress().getState());
            preparedStatement.setInt(5,employee.getAddress().getPinCode());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        employee.setEmpId(generatedKeys.getInt(1));
                    }
                }
            }

            log.info("Employee added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            log.warn("error in adding");
        }
    }


    @Override
    public void updateEmployee(int empId,Employee employee){
        String query = "UPDATE employee SET EMP_NAME = ?, DEPT_NAME = ?, STREET = ?, STATE = ?, PINCODE = ? WHERE ID = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, employee.getEmpName());
            preparedStatement.setString(2, employee.getDeptName());
            preparedStatement.setString(3, employee.getAddress().getStreet());
            preparedStatement.setString(4, employee.getAddress().getState());
            preparedStatement.setInt(5, employee.getAddress().getPinCode());
            preparedStatement.setInt(6, empId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                log.info("Employee updated successfully.");
            } else {
                log.warn("No employee found with ID: {}", empId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.warn("Error in updating employee: {}", e.getMessage());
        }
    }

    @Override
    public void deleteEmployee(int empId){
        String query="DELETE FROM EMPLOYEE WHERE ID=?";
        try(Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(query))
        {
            preparedStatement.setInt(1,empId);
            int affectedRows =preparedStatement.executeUpdate();

            if(affectedRows>0)
            {
                log.info("Employee deleted successfully.");
            }else{
                log.warn("No employee found with ID: {}", empId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.warn("Error in deleting employee {}",e.getMessage());
        }
    }

    @Override
    public Employee getEmployeeById(int empId){
        String query = "SELECT * FROM employee WHERE ID = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, empId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String empName = resultSet.getString("EMP_NAME");
                    String deptName = resultSet.getString("DEPT_NAME");
                    String street = resultSet.getString("STREET");
                    String state = resultSet.getString("STATE");
                    int pincode = resultSet.getInt("PINCODE");

                    Address address = new Address(street, state, pincode);
                    employee=new Employee(empName, deptName, address);
                    employee.setEmpId(empId);
                    return employee;
                } else {
                    log.warn("No employee found with ID: {}", empId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.warn("Error in fetching employee: {}", e.getMessage());
        }

        return null;


    }

    @Override
    public List<Employee> getAllEmployees(){
        String query ="Select * from Employee";
        List<Employee> employees=new ArrayList<>();

        try(Connection connection=getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        ResultSet resultSet=preparedStatement.executeQuery()){
            while(resultSet.next()){
                int empId=resultSet.getInt("ID");
                String empName=resultSet.getString("EMP_NAME");
                String deptName=resultSet.getString("DEPT_NAME");
                String street=resultSet.getString("STREET");
                String state=resultSet.getString("STATE");
                int pinCode=resultSet.getInt("PINCODE");


//                System.out.println(empId);
                Address address=new Address(street,state,pinCode);
                employee=new Employee(empName,deptName,address);
                employee.setEmpId(empId);
                employees.add(employee);

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            log.warn("Error in fetching employees {}",e.getMessage());
        }
        return employees;
    }

}