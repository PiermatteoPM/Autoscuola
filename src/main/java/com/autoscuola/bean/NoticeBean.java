package com.autoscuola.bean;

public class NoticeBean {

    private String email;

    public NoticeBean(String email){
        setEmail(email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
