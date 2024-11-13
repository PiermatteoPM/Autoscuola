package com.autoscuola.model;

import java.util.*;

public class Prenotazione {
    private String id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private boolean approved;

    public Prenotazione(){
        this.approved = false;
    }

    public Prenotazione(String email, String username, String firstname, String lastname, boolean approved){
        this.email = email;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.approved = approved;
        this.id = "";
    }

    public Prenotazione(String email, String username, String firstname, String lastname, boolean approved, String id){
        this(email, username, firstname, lastname, approved);
        this.id = id;
    }



    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }


    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }


    public void setApproved(boolean approved){
        this.approved = approved;
    }

    public boolean getApproved(){
        return approved;
    }


}
