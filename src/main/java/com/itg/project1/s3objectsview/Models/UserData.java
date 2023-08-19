package com.itg.project1.s3objectsview.Models;

import java.util.Date;
public class UserData {
    private String fullName;
    private String email;
    private String password;

    public UserData() {
    }

    @Override
    public String toString() {
        return "UserData{" +
                "FullName='" + fullName + '\'' +
                ", Email='" + email + '\'' +
                ", Password='" + password + '\'' +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
