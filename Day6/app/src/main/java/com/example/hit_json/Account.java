package com.example.hit_json;

public class Account {
    int id;
    String username, passworđ;

    public Account(int id, String username, String passworđ) {
        this.id = id;
        this.username = username;
        this.passworđ = passworđ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassworđ() {
        return passworđ;
    }

    public void setPassworđ(String passworđ) {
        this.passworđ = passworđ;
    }
}
