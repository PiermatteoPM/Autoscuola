package com.autoscuola.bean;

import java.util.List;

public class PrenotazioneBean {
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private boolean approved = false;
    private String id;

    public PrenotazioneBean() {
    }

    public PrenotazioneBean(String email, String username, String firstname, String lastname, boolean approved) {
        setEmail(email);
        setFirstname(firstname);
        setLastname(lastname);
        setUsername(username);
        setApproved(approved);
    }

    public PrenotazioneBean(String email, String username, String firstname, String lastname, boolean approved, String id) {
        this(email,username, firstname, lastname, approved);
        setId(id);
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setApproved(boolean approved){
        this.approved = approved;
    }

    public String getUsername() {
        return username;
    }
    public String getLastname() {
        return lastname;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getEmail() {
        return email;
    }
    public boolean getApproved(){
        return approved;
    }
    public String getId() {
        return id;
    }


}