package com.autoscuola.dao;

import com.autoscuola.model.*;

public interface UserDAO {

    /** Inserimento dell'utente in persistenza
     * Valore di ritorno booleano per verificare la correttezza dell'operazione */
    void insertUser(Login registration) throws EmailAlreadyInUseException, UsernameAlreadyInUseException;

    /** Recupera le informazioni di un utente in persistenza, ottenuta dall'email */
    User loadClient(Login login) throws UserDoesNotExistException;

    /** Retrive delle informazioni di un utente dalla persistenza, ottenuta dall'username che abbiamo detto essere unico */
    User retrieveUserByUsername(String username) throws UserDoesNotExistException;

    /** Ottiene la password associata all'email */
    String getPasswordByEmail(String email) throws UserDoesNotExistException;

    /** Aggiorna*/
    void updateGenreUser(User user);

    void tryCredentialExisting(Login login) throws EmailAlreadyInUseException, UsernameAlreadyInUseException;

}