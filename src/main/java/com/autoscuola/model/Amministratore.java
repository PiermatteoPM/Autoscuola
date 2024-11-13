package com.autoscuola.model;

public class Amministratore extends User {

    public Amministratore(String username, String email){
        super(username, email);
        super.amministratore = true;
    }

}