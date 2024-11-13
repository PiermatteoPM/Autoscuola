package com.autoscuola.controller.app;

import com.autoscuola.bean.*;
import com.autoscuola.dao.*;
import com.autoscuola.exception.*;
import com.autoscuola.model.Prenotazione;
import com.autoscuola.pattern.observer.PrenotazioneCollection;
import com.autoscuola.pattern.abstract_factory.DAOFactory;

public class AggiungiPrenotazioneController {

    public void aggiungiPrenotazione(PrenotazioneBean pB) throws PrenotazioneAlreadyExistsException {

        PrenotazioneDAO dao = DAOFactory.getDAOFactory().createPrenotazioneDAO();         // Crea l'istanza corretta del DAO (Impostata nel file di configurazione)

        // Crea la Prenotazione (model), id verrà impostato dal dao
        Prenotazione prenotazione = new Prenotazione(pB.getEmail(), pB.getUsername(), pB.getFirstname(), pB.getLastname(), pB.getApproved());


        try{    // Invio Prenotazione model al DAO
            dao.insertPrenotazione(prenotazione);

            /* Per pattern Observer !!! */
            if(prenotazione.getApproved()){ // La notifica all observer solo se la prenotazione è approvata -> Se è caricata da un amministratore
                PrenotazioneCollection.getInstance().addPrenotazione(prenotazione);
            }

        } catch (PrenotazioneAlreadyExistsException e){
            throw new PrenotazioneAlreadyExistsException();
        }
    }

}
