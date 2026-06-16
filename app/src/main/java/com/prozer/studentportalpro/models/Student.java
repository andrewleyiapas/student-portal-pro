package com.prozer.studentportalpro.models;

public class Student {

    private int id;
    private String name;
    private String email;
    private String course;
    private String regNo;

    public Student(int id, String name, String email, String course, String regNo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
        this.regNo = regNo;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCourse() { return course; }
    public String getRegNo() { return regNo; }
}