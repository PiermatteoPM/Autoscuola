package com.autoscuola.bean;

import com.autoscuola.exception.InvalidEmailException;

import java.util.List;

public class AmministratoreBean extends UserBean {

    public AmministratoreBean(){
        super.amministratore = true;
    }

    public AmministratoreBean(String username, String email) throws InvalidEmailException {
        super(username, email);
        super.amministratore = true;
    }
}
