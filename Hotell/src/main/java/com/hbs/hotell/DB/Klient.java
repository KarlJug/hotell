package com.hbs.hotell.DB;

import com.hbs.hotell.util.DataTransferObject;

public class Klient implements DataTransferObject {

    private long id;
    private String eesnimi, pere_nimi, isikukood, email;


    public void setId(long id) {
        this.id = id;
    }

    public void setEesnimi(String eesnimi) {
        this.eesnimi = eesnimi;
    }

    public void setPere_nimi(String pere_nimi) {
        this.pere_nimi = pere_nimi;
    }

    public void setIsikukood(String isikukood) {
        this.isikukood = isikukood;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEesnimi() {
        return eesnimi;
    }

    public String getPere_nimi() {
        return pere_nimi;
    }

    public String getIsikukood() {
        return isikukood;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Klient{" +
                "id=" + id +
                ", eesnimi='" + eesnimi + '\'' +
                ", pere_nimi='" + pere_nimi + '\'' +
                ", isikukood='" + isikukood + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
