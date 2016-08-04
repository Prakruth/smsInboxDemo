package com.main.hatke.buyhatke_app;

/**
 * Created by Prakruth on 30-Jul-16.
 */
public class SMSdata {
    private String number;
    private String body;
    private String contact_name;

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
