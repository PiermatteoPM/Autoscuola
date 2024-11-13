package com.autoscuola.controller.app;

import com.autoscuola.bean.*;
import com.autoscuola.others.Printer;
import com.autoscuola.pattern.abstract_factory.DAOFactory;
import com.autoscuola.pattern.observer.PrenotazioneCollection;
import com.autoscuola.model.*;

import java.util.*;

public class AccountController {

    /**
     * Recupera tutte le prenotazioni globali by username
     */
    public List<PrenotazioneBean> retrievePrenotazioni(UserBean userBean) {
        PrenotazioneDAO dao = DAOFactory.getDAOFactory().createPrenotazioneDAO();         // Crea l'istanza corretta del DAO (Impostata nel file di configurazione)
        String email = userBean.getEmail();

        // Recupero lista Prenotazioni
        List<Prenotazione> prenotazioni = dao.retrievePrenotazioniByEmail(email);

        ArrayList<PrenotazioneBean> prenotazioniBean = new ArrayList<>();
        try {
            for (Prenotazione p : prenotazioni){
                PrenotazioneBean pB = new PrenotazioneBean(p.getEmail(),p.getUsername(),p.getFirstname(),p.getLastname(),p.getApproved());
                pB.setId(p.getId());
                prenotazioniBean.add(pB);
            }

        return prenotazioniBean;
    }

    /** Utilizzata per aggiornare i generi musicali preferiti dell'utente in caso in cui prema il bottone Salva */
    public void updateGenreUser(UserBean userBean){
        UserDAO dao = DAOFactory.getDAOFactory().createClientDAO();         // Crea l'istanza corretta del DAO (Impostata nel file di configurazione)

        User user;
        if(userBean.isAmministratore()){
            user = new Amministratore(userBean.getUsername(),userBean.getEmail());
        } else {
            user = new Studente(userBean.getUsername(),userBean.getEmail());
        }

        // Invio utente model al DAO
        dao.updateGenreUser(user);
    }

    /** Utilizzata per permettere di eliminare le prenotazioni
     * Non è stato implementata l'eliminazione nel front-end, ma si nel back-end */
    public Boolean deletePrenotazione(PrenotazioneBean pB){

        PrenotazioneDAO dao = DAOFactory.getDAOFactory().createPrenotazioneDAO(); // Crea l'istanza corretta del DAO (Impostata nel file di configurazione)

        Prenotazione prenotazione = new Prenotazione(pB.getEmail(), pB.getUsername(), pB.getFirstname(), pB.getLastname(), pB.getApproved());
        prenotazione.setId(pB.getId());

        dao.deletePrenotazione(prenotazione);

        if(prenotazione.getApproved()) {
            /* OBSERVER -> REMOVE PER FAR AGGIORNARE LA HOME PAGE */
            PrenotazioneCollection.getInstance().removePrenotazione(prenotazione);
        }
        return true;
    }

    private void handleDAOException(Exception e) {
        Printer.logPrint(e.getMessage());
    }
}
