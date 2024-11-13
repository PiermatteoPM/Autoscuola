package com.autoscuola.model;

import java.util.List;

public class Login extends User {
    private String password;

    /** Utilizzato in fase di login */
    public Login(String email, String password){
        setEmail(email);
        this.password = password;
    }

    /** Utilizzato in fase di registrazione */
    public Login(String username, String email, String password){
        super(username, email);
        setPassword(password);
    }

    public Login() {

    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
