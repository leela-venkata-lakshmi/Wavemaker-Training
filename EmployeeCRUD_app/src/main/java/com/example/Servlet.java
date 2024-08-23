package com.example;

import com.example.Controller.EmployeeController;
import com.example.Model.Employee;
import com.example.Repository.EmployeeDBRepository;
import com.example.Repository.EmployeeRepository;
import com.example.Service.EmployeeService;
import com.example.Service.EmployeeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;



@WebServlet("/crudApp")  // URL pattern for the servlet
public class Servlet extends HttpServlet {
    EmployeeRepository repository=new EmployeeDBRepository();
    EmployeeService service=new EmployeeServiceImpl(repository);
   EmployeeController controller=new EmployeeController(service);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        // Set response content type
        resp.setContentType("application/json");
        List<Employee> employees= controller.getAllEmployees();
        Gson gson=new Gson();
        String jsonResponse = gson.toJson(employees);
        PrintWriter out=resp.getWriter();
        out.print(jsonResponse);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        if(!validate(req))
        {

            resp.getWriter().println("Login First");
        }else {
            BufferedReader reader = req.getReader();
            Gson gson = new Gson();
            Employee employee = gson.fromJson(reader, Employee.class);
            controller.addEmployee(employee);

            PrintWriter out = resp.getWriter();
            out.print("{\"message\":\"Employee added successfully\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        if(!validate(req))
        {
            resp.getWriter().println("Login First");
        }else {
            String idParam = req.getParameter("id");
            int empId = Integer.parseInt(idParam);
//        Employee employee=controller.getEmployeeById(empId);
//        Gson gson =new Gson();
//        String jsonResponse= gson.toJson(employee);
            controller.deleteEmployee(empId);
            PrintWriter out = resp.getWriter();
            out.print("Employee deleted successfully.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        if(!validate(req))
        {
            resp.getWriter().println("Login First");
        }else {
        String id=req.getParameter("id");
        int empId=Integer.parseInt(id);
        BufferedReader reader=req.getReader();
        Gson gson=new Gson();
        Employee employee=gson.fromJson(reader,Employee.class);
        controller.updateEmployee(empId,employee);
        PrintWriter out=resp.getWriter();
        out.print("Updated employee :" );
        out.print(controller.getEmployeeById(empId));
        }
    }

    protected Boolean validate(HttpServletRequest req){
        Cookie[] cookies=req.getCookies();
        String password="";
        if(cookies!=null){
            for(Cookie cookie :cookies)
            {
                if("password".equals(cookie.getName()))
                {
                    password=cookie.getValue();
                    break;
                }
            }
        }
        if(password.equals("pass123"))
            return true;
        else
            return false;
    }
}
