package com.revature.models;

public class Principal {
    private String user_id;
    private String password;

    public Principal() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "user_id='" + user_id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}


