package com.javaTraining;

import java.util.Arrays;

public class Employee {
      float height;
    String name;
     int age;
     char gender;
     boolean married;
     double salary;
     short siblings;
     long empId;
     byte child;
     String[] friends=new String[10];

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public short getSiblings() {
        return siblings;
    }

    public void setSiblings(short siblings) {
        this.siblings = siblings;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public byte getChild() {
        return child;
    }

    public void setChild(byte child) {
        this.child = child;
    }

    public String[] getFriends() {
        return friends;
    }

    public void setFriends(String[] friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "height=" + height +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", married=" + married +
                ", salary=" + salary +
                ", siblings=" + siblings +
                ", empId=" + empId +
                ", child=" + child +
                ", friends=" + Arrays.toString(friends) +
                '}';
    }
}
