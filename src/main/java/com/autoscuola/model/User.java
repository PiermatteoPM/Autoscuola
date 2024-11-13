package com.autoscuola.model;

import java.util.List;

/** Rappresenta la base di ogni utilizzatore della piattaforma */
public abstract class User {

    private String email;
    private String username;

    protected boolean amministratore;

    protected User(){
    }

    protected User(String username, String email){
        this.username = username;
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public boolean isAmministratore() {
        return amministratore;
    }
}
