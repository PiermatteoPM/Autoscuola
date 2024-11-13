package com.autoscuola.controller.app;

import com.autoscuola.bean.NoticeBean;
import com.autoscuola.bean.PrenotazioneBean;
import com.autoscuola.dao.*;
import com.autoscuola.others.Printer;
import com.autoscuola.pattern.abstract_factory.DAOFactory;
import com.autoscuola.pattern.observer.Observer;
import com.autoscuola.pattern.observer.PrenotazioneCollection;
import com.autoscuola.pattern.observer.Subject;
import com.autoscuola.model.Notice;
import com.autoscuola.model.Prenotazione;

import java.util.*;

public class HomePageController {

    public List<PrenotazioneBean> retrivePrenotazioniApproved() {

        PrenotazioneDAO dao = DAOFactory.getDAOFactory().createPrenotazioneDAO();        // Crea l'istanza corretta del DAO (Impostata nel file di configurazione)
        List<Prenotazione> Prenotazioni = dao.retrieveApprovedPrenotazioni();              // Recupero lista Prenotazione approvate

        return getPrenotazioniBean(prenotazioni);
    }

    /*public List<PrenotazioneBean> searchPrenotazioneByFilters(PrenotazioneBean prenotazioneBean) {

        PrenotazioneDAO dao = DAOFactory.getDAOFactory().createPlaylistDAO();  // Crea l'istanza corretta del DAO (Impostata nel file di configurazione)

        Prenotazione prenotazione = new Prenotazione();                             // Creo la entity da passare al DAO

        /* Popolo la prenotazione da cercare con solo le informazioni di cui l'utente è interessato */
        /*prenotazione.setPlaylistName(playlistBean.getPlaylistName());
        prenotazione.setPlaylistGenre(playlistBean.getPlaylistGenre());
        playlist.setEmotional(playlistBean.getEmotional());

        List<Playlist> playlists;

        if(emotionalEmpty(playlist.getEmotional()) && genreEmpty(playlist.getPlaylistGenre())){
            playlists = dao.searchPlaylistByTitle(playlist);        // Recupero lista Playlist filtrata solo per titolo della playlist
        } else if (!emotionalEmpty(playlist.getEmotional()) && !genreEmpty(playlist.getPlaylistGenre())){
            playlists = dao.searchPlaylistByFilters(playlist);    // Recupero lista Playlist controllando tutti i filtri
        } else if (emotionalEmpty(playlist.getEmotional())) {
            playlists = dao.searchPlaylistByGenre(playlist);      // Recupero lista Playlist controllando Titolo e Generi musicali
        } else {
            playlists = dao.searchPlaylistByEmotional(playlist);  // Recupero lista Playlist controllando Titolo ed Emotional
        }

        return getPlaylistsBean(playlists);
    }*/

    /** Nel caso in cui non volessimo che la view contattasse il model per fare attach */
    public void observePrenotazioneTable(Observer observer){
        Subject prenotazioneCollection = PrenotazioneCollection.getInstance();
        prenotazioneCollection.attach(observer);
    }

    public List<PrenotazioneBean> getPrenotazioniBean(List<Prenotazione> prenotazioni){
        List<PrenotazioneBean> prenotazioniBean = new ArrayList<>();           // Creo una lista di prenotazioneBean da restituire al Grafico

        try {
            for (Prenotazione p : prenotazioni){
                PrenotazioneBean pB = new PrenotazioneBean(p.getEmail(),p.getUsername(),p.getFirstname(),p.getLastname(),p.getApproved());
                pB.setId(p.getId());
                prenotazioniBean.add(pB);
            }
        }
        return prenotazioniBean;
    }

    public void deleteSelectedPrenotazione(PrenotazioneBean prenotazioneBean) {
        Prenotazione prenotazione = new Prenotazione(prenotazioneBean.getEmail(), prenotazioneBean.getUsername(), prenotazioneBean.getFirstname(), prenotazioneBean.getLastname(), prenotazioneBean.getApproved());

        if (prenotazione.getApproved()){
            PrenotazioneDAO dao = DAOFactory.getDAOFactory().createPrenotazioneDAO();
            dao.deletePrenotazione(prenotazione);

            /* OBSERVER -> REMOVE PER FAR AGGIORNARE LA HOME PAGE */
            PrenotazioneCollection.getInstance().removePrenotazione(prenotazione);
        }
    }

    public void removeNotice(NoticeBean noticeBean) {
        NoticeDAO dao = DAOFactory.getDAOFactory().createNoticeDAO();   // Crea l'istanza corretta del DAO (Impostata nel file di configurazione)
        Notice notice = new Notice(noticeBean.getEmail());
        dao.deleteNotice(notice);
    }

}
