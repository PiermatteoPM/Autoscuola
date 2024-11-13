package com.autoscuola.controller.app;

import com.autoscuola.bean.*;
import com.autoscuola.exception.*;
import com.autoscuola.pattern.abstract_factory.DAOFactory;
import com.autoscuola.model.*;

public class RegistrazioneController {

    /** Query al dao per registrare un utente */
    public void registerStudente(LoginBean regBean, UserBean userBean) throws EmailAlreadyInUseException, UsernameAlreadyInUseException, InvalidEmailException {

        UserDAO dao = DAOFactory.getDAOFactory().createUserDAO();         // Crea l'istanza corretta del DAO (Impostata nel file di configurazione)

        // Crea lo studente (model) per inviarlo al DAO
        Login registration = new Login(regBean.getUsername(), regBean.getEmail(), regBean.getPassword());

        try{
            dao.insertUser(registration);
        } catch (EmailAlreadyInUseException e){
            throw new EmailAlreadyInUseException();
        } catch (UsernameAlreadyInUseException e){
            throw new UsernameAlreadyInUseException();
        }

        /* NON CI SI PUO REGISTRARE COME UN Amministratore */

        StudenteBean studenteBean = (StudenteBean) userBean;
        studenteBean.setUsername(registration.getUsername());
        studenteBean.setEmail(regBean.getEmail());
    }

    public void tryCredentialExisting(LoginBean regBean) throws EmailAlreadyInUseException, UsernameAlreadyInUseException {
        UserDAO dao = DAOFactory.getDAOFactory().createUserDAO();         // Crea l'istanza corretta del DAO (Impostata nel file di configurazione)

        Login login = new Login();
        login.setEmail(regBean.getEmail());
        login.setUsername(regBean.getUsername());
        dao.tryCredentialExisting(login);
    }
}