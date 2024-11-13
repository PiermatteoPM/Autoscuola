package com.autoscuola.bean;

import com.autoscuola.exception.InvalidEmailException;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;

public abstract class UserBean {

    private String username;
    private String email;

    protected boolean amministratore;

    protected UserBean() {
    }

    protected UserBean(String username, String email) throws InvalidEmailException {
        setUsername(username);
        setEmail(email);
    }

    public void setEmail(String email) throws InvalidEmailException {
        if(checkMailCorrectness(email)){
            this.email = email;
        } else {
            throw new InvalidEmailException();
        }
    }
    public String getEmail() {
        return email;
    }


    public void setUsername(String nome) {
        this.username = nome;
    }
    public String getUsername() {
        return username;
    }



    public boolean isAmministratore() {
        return amministratore;
    }

    /** Verifica se la email inserita rispetta i canoni per essere una email */
    private boolean checkMailCorrectness(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
