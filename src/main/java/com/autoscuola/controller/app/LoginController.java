package com.autoscuola.controller.app;

import com.autoscuola.bean.*;
import com.autoscuola.dao.*;
import com.autoscuola.exception.*;
import com.autoscuola.pattern.abstract_factory.DAOFactory;
import com.autoscuola.model.*;

import java.util.*;

public class LoginController {

    /** Il metodo accede allo strato di persistenza per verificare se le credenziali per l'accesso sono valide
     * L'email deve essere registrata
     * La password associata deve essere come quella inserita in fate di login
     * Il loginBean contiene il campo mail e il campo password
     * Effettua una Query per recuperare la password e confrontarla con quella inserita  */

    public void verificaCredenziali(LoginBean bean) throws IncorrectPasswordException, UserDoesNotExistException {

        UserDAO dao = DAOFactory.getDAOFactory().createClientDAO();         // Crea l'istanza corretta del DAO (Impostata nel file di configurazione)

        String password = dao.getPasswordByEmail(bean.getEmail());

        if (!password.equals(bean.getPassword())){
            throw new IncorrectPasswordException();
        }
    }

    /** Recupera l'User dalla persistenza e crea una nuova istanza di UserBean */
    public UserBean loadUser(LoginBean bean) throws UserDoesNotExistException, InvalidEmailException {

        UserDAO dao = DAOFactory.getDAOFactory().createClientDAO();
        Login login = new Login(bean.getEmail(), bean.getPassword());           // Creo model Login per comunicare con il dao

        try{
            User user = dao.loadUser(login);

            if(!user.isAmministratore()){
                StudenteBean studenteBean = new StudenteBean(user.getUsername(),user.getEmail());

                List<NoticeBean> noticeBeanList = new ArrayList<>();
                List<Notice> noticeList = retriveNotice(user);

                for(Notice notice: noticeList){
                    NoticeBean noticeBean = new NoticeBean(notice.getEmail());
                    noticeBeanList.add(noticeBean);
                }
                studenteBean.setNotices(noticeBeanList);
                return studenteBean;

            } else {
                return new AmministratoreBean(user.getUsername(),user.getEmail());
            }

        } catch (UserDoesNotExistException e){
            throw new UserDoesNotExistException();
        } catch (InvalidEmailException e) {
            throw new InvalidEmailException();
        }
    }

    public List<Notice> retriveNotice(User user){
        NoticeDAO dao = DAOFactory.getDAOFactory().createNoticeDAO();
        return dao.retrieveNotice(user);
    }
}