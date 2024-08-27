package com.wavemaker.example.controller;

import com.wavemaker.example.model.Task;
import com.wavemaker.example.model.User;
import com.wavemaker.example.service.TaskService;
import com.wavemaker.example.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.util.List;

@WebServlet("/task")
public class TaskServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(TaskServlet.class);
    TaskService taskService=new TaskService();

    public TaskServlet() throws SQLException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {

            resp.sendRedirect("index.html");
        }
        String title = req.getParameter("title");
        Date dueDate = Date.valueOf(req.getParameter("dueDate"));
        Time startTime = Time.valueOf(req.getParameter("startTime") + ":00");
        Time remainderTime = Time.valueOf(req.getParameter("remainderTime") + ":00");
        String priorityStr = req.getParameter("priority");
        String priority = (priorityStr);
        Task task = new Task(user.getUserId(),title,dueDate,startTime,remainderTime,priority);

        taskService.addTask(task);

        resp.sendRedirect("todo.html");
    }

    private int convertPriorityToInt(String priority) {
        switch (priority.toLowerCase()) {
            case "high":
                return 1;
            case "medium":
                return 2;
            case "low":
                return 3;
            default:
                return 0;
        }
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            resp.sendRedirect("index.html");
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Task task = objectMapper.readValue(req.getReader(), Task.class);
        task.setUserId(user.getUserId());

        taskService.updateTask(task);

        resp.setStatus(HttpServletResponse.SC_OK);
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            resp.sendRedirect("index.html");
            return;
        }

        int userId = user.getUserId();
        logger.info("user id is:{}",userId);

        try {

            List<Task> tasks = taskService.getAllTasksByUser(userId);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");


            ObjectMapper objectMapper = new ObjectMapper();
            String jsonTasks = objectMapper.writeValueAsString(tasks);

           resp.getWriter().write(jsonTasks);


        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskIdParam = req.getParameter("taskId");
        int taskId = Integer.parseInt(taskIdParam);
        try{
            taskService.deleteTask(taskId);
         resp.setStatus(HttpServletResponse.SC_OK);
    } catch (Exception e) {
        e.printStackTrace();

        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    }


}

