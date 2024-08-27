package com.wavemaker.example.repository;

import com.wavemaker.example.model.Task;
import com.wavemaker.example.util.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private static final Logger logger = LoggerFactory.getLogger(TaskRepository.class);
    private final Connection connection;

    public TaskRepository() throws SQLException {
        this.connection = DatabaseConnection.connectToDatabase();
    }


    public void addTask(Task task) throws SQLException {
        String query = "INSERT INTO tasks (USER_ID, TITLE, DUE_DATE, START_TIME, REMINDER_TIME, PRIORITY) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, task.getUserId());
            stmt.setString(2, task.getTitle());
            stmt.setDate(3, task.getDueDate());
            stmt.setTime(4, task.getStartTime());
            stmt.setTime(5, task.getRemainderTime());
            stmt.setString(6, task.getPriority());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    task.setTaskId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            logger.error("Error adding task: {}", e.getMessage());
            throw e;
        }
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

    public List<Task> getAllTasksByUser(int userId) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks WHERE USER_ID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Task task = new Task();
                    task.setUserId(rs.getInt("USER_ID"));
                    task.setTitle(rs.getString("TITLE"));
                    task.setDueDate(rs.getDate("DUE_DATE"));

                    task.setStartTime(rs.getTime("START_TIME"));
                    task.setRemainderTime(rs.getTime("REMINDER_TIME"));
                    task.setPriority(rs.getString("PRIORITY"));
                    task.setTaskId(rs.getInt("ID"));
                    tasks.add(task);

                }

            }
        }
        return tasks;
    }


    public void updateTask(Task task) throws SQLException {
        String query = "UPDATE tasks SET TITLE = ?, DUE_DATE = ?, START_TIME = ?, REMINDER_TIME = ?, PRIORITY = ? WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, task.getTitle());
            stmt.setDate(2, task.getDueDate());
            stmt.setTime(3, task.getStartTime());
            stmt.setTime(4, task.getRemainderTime());
            stmt.setString(5, task.getPriority());
            stmt.setInt(6, task.getTaskId());

            stmt.executeUpdate();
        }
    }


    public void deleteTask(int taskId) throws SQLException {
        String query = "DELETE FROM tasks WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, taskId);
            stmt.executeUpdate();
        }
    }


    public void markTaskAsCompleted(int taskId) throws SQLException {
        String query = "UPDATE tasks SET completed = TRUE WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, taskId);
            stmt.executeUpdate();
        }
    }
}
