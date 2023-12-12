package com.example.demo2;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String number;
    private String sex;
    private String email;
    public Student(String name, String number, String sex, String email)         {
        this.name = name;
        this.number = number;
        this.sex = sex;
        this.email = email;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

