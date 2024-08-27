package com.wavemaker.example.controller;

import com.wavemaker.example.model.User;
import com.wavemaker.example.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/login")
public class UserServlet extends HttpServlet {
    UserService userService=new UserService();

    public UserServlet() throws SQLException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");

        User user=userService.findByUserName(username);
        if(user!=null && BCrypt.checkpw(password,user.getPassword()))
        {
            HttpSession session= req.getSession();
            session.setAttribute("user",user);
            resp.sendRedirect("todo.html");
        }else{
            PrintWriter out=resp.getWriter();
            out.print("Invalid Credentials. Please try again.");
            resp.sendRedirect("index.html?error=Invalid+credentials");

        }
    }

}
