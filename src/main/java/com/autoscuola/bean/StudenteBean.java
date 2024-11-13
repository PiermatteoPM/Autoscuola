package com.autoscuola.bean;

import com.autoscuola.exception.InvalidEmailException;

import java.util.List;

public class StudenteBean extends UserBean{

    private List<NoticeBean> notices;

    public StudenteBean(String email) throws InvalidEmailException {
        setEmail(email);
        super.amministratore = false;
    }

    public StudenteBean(String username, String email) throws InvalidEmailException {
        super(username,email);
        super.amministratore = false;
    }

    public void setNotices(List<NoticeBean> notices) {
        this.notices = notices;
    }

    public List<NoticeBean> getNotices() {
        return notices;
    }
}