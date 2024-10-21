package com.autoscuola.controller;

import com.autoscuola.model.User;

import java.util.HashMap;
import java.util.Map;

public class LoginController {
    // Mappa per simulare un database di utenti
    private final Map<String, User> userDatabase;

    public LoginController() {
        userDatabase = new HashMap<>();
        // Aggiungi un utente di esempio
        userDatabase.put("testuser", new User("testuser", "password123"));
    }

    public boolean login(String username, String password) {
        User user = userDatabase.get(username);
        return user != null && user.getPassword().equals(password);
    }
}
