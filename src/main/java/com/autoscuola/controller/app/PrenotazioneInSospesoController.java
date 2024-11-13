package com.autoscuola.controller.app;

import com.autoscuola.bean.*;
import com.autoscuola.dao.*;
import com.autoscuola.exception.*;
import com.autoscuola.others.Printer;
import com.autoscuola.pattern.abstract_factory.DAOFactory;
import com.autoscuola.pattern.observer.PrenotazioneCollection;

import com.autoscuola.model.*;

import java.util.*;

public class PrenotazioneInSospesoController {

    public void approvePrenotazione(PrenotazioneBean pB){
        PrenotazioneDAO dao = DAOFactory.getDAOFactory().createPrenotazioneDAO();

        Prenotazione prenotazione = new Prenotazione(pB.getEmail(), pB.getUsername(), pB.getFirstname(), pB.getLastname(), pB.getApproved());
        prenotazione.setId(pB.getId());

        // Istanza di prenotazione ha ancora il parametro approved a false
        Prenotazione prenotazioneApproved = dao.approvePrenotazione(prenotazione);
        pB.setApproved(prenotazioneApproved.getApproved());

        /* OBSERVER -> ADD PER FAR AGGIORNARE LA HOME PAGE */
        PrenotazioneCollection prenotazioneCollection = PrenotazionetCollection.getInstance();
        prenotazioneCollection.addPrenotazione(prenotazione);
    }

    /** Recupera tutte le prenotazione globali, sia approvate che non */
    public List<PrenotazioneBean> retrievePrenotazioni(){

        PrenotazioneDAO dao = DAOFactory.getDAOFactory().createPrenotazioneDAO();

        // Recupero lista Prenotazione
        List<Prenotazione> Prenotazioni = dao.retrievePendingPrenotazioni();
        List<PrenotazioneBean> prenotazioniBean = new ArrayList<>();

        try{
            for (Prenotazione p : prenotazioni){
                PrenotazioneBean pB = new PrenotazioneBean(p.getEmail(),p.getUsername(),p.getFirstname(),p.getLastname(),p.getApproved());
                pB.setId(p.getId());

                prenotazioniBean.add(pB);
            }
        }

        return prenotazioniBean;
    }

    public void rejectPrenotazione(PrenotazioneBean pB) {
        PrenotazioneDAO dao = DAOFactory.getDAOFactory().createPrenotazioneDAO();

        Prenotazione prenotazione = new Prenotazione(pB.getEmail(), pB.getUsername(), pB.getFirstname(), pB.getLastname(), pB.getApproved());
        prenotazione.setId(pB.getId());

        dao.deletePrenotazione(prenotazione);
    }

    public void sendNotification(NoticeBean noticeBean) {

        NoticeDAO dao = DAOFactory.getDAOFactory().createNoticeDAO();
        Notice notice = new Notice(noticeBean.getEmail());

        dao.addNotice(notice);
    }
}