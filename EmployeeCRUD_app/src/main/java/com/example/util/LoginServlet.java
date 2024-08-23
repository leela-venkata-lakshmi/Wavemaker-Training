package com.example.util;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user=req.getParameter("UserName");
        String pass=req.getParameter("Password");
        if(user.equals("admin") && pass.equals("pass123"))
        {
            Cookie userCookie=new Cookie("password",pass);
            resp.addCookie(userCookie);
            resp.getWriter().print("login successful");
        }else{
            resp.getWriter().print("Try Again.");
        }
    }
}
