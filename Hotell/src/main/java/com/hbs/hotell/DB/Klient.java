package com.hbs.hotell.DB;

import com.hbs.hotell.util.DataTransferObject;

public class Klient implements DataTransferObject {

    private long id;

    private String ees_nimi, pere_nimi, email;

    public void setId(long id) {
        this.id = id;
    }

    public void setEes_nimi(String ees_nimi) {
        this.ees_nimi = ees_nimi;
    }

    public void setPere_nimi(String pere_nimi) {
        this.pere_nimi = pere_nimi;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEes_nimi() {
        return ees_nimi;
    }

    public String getPere_nimi() {
        return pere_nimi;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public long getId() {
        return id;
    }
}
