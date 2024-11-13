package com.autoscuola.model;

import java.util.List;

public class Studente extends User {

    public User(String username, String email){
        super(username, email);
        super.amministratore = false;
    }
}

