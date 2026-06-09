package com.prozer.studentportalpro.models;

public class Student {
    private int id;
    private String name;
    private String email;
    private String password;
    private String course;
    private String regNo;

    public Student() {}

    public Student(int id, String name, String email, String password, String course, String regNo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.course = course;
        this.regNo = regNo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; }
}