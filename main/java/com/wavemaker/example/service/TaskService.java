package com.wavemaker.example.service;

import com.wavemaker.example.model.Task;
import com.wavemaker.example.repository.TaskRepository;

import java.sql.SQLException;
import java.util.List;

public class TaskService {
    TaskRepository taskRepository=new TaskRepository();
    public TaskService() throws SQLException {
    }

    public void addTask(Task task) {
        try {
            taskRepository.addTask(task);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public List<Task> getAllTasksByUser(int userId)
    {
        try {
         return taskRepository.getAllTasksByUser(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateTask(Task task) {
        try {
            taskRepository.updateTask(task);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }


    public void deleteTask(int taskId) {
        try {
            taskRepository.deleteTask(taskId);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void markTaskAsCompleted(int taskId) {
        try {
            taskRepository.markTaskAsCompleted(taskId);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
