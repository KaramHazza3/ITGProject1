package com.itg.project1.s3objectsview.Models;

public class LoginData {
    private String email;
    private String password;

    public LoginData() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "Email='" + email + '\'' +
                ", Password='" + password + '\'' +
                '}';
    }
}
