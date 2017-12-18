package com.videoondemand.model;

public class User {

    private String username;
    private String password;
    private int id_role;
    private int active;

    public User (String username, String password, int id_role){
        this.username = username;
        this.password = password;
        this.id_role = id_role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId_role() {
        return id_role;
    }

    public int getActive() {
        return active;
    }
}
