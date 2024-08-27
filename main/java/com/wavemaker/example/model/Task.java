package com.wavemaker.example.model;
import java.sql.Time;
import java.sql.Date;

public class Task {

    private int taskId;
    private int userId;
    private String title;
    private Date dueDate;
    private Time startTime;
    private Time remainderTime;
    private String priority;

//    public Task(){}
    public Task(int userId, String title, Date dueDate, Time startTime, Time remainderTime, String priority) {
//       this.taskId = taskId;
        this.userId = userId;
        this.title = title;
        this.dueDate = dueDate;
        this.startTime = startTime;
        this.remainderTime = remainderTime;
        this.priority = priority;
    }

    public Task() {

    }


    // Getters and Setters
    public int getTaskId() {
        return taskId;

    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getRemainderTime() {
        return remainderTime;
    }

    public void setRemainderTime(Time remainderTime) {
        this.remainderTime = remainderTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
