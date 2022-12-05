package com.hbs.hotell.DB;

import com.hbs.hotell.util.DataTransferObject;

public class Broneering implements DataTransferObject {

    private long id;
    private String kulastaja;
    private String tuba;
    private String aeg;

    public void setId(long id) {
        this.id = id;
    }

    public void setKulastaja(String kulastaja) {
        this.kulastaja = kulastaja;
    }

    public void setTuba(String tuba) {
        this.tuba = tuba;
    }

    public void setAeg(String aeg) {
        this.aeg = aeg;
    }

    @Override
    public long getId() {
        return id;
    }

    public String getKulastaja() {
        return kulastaja;
    }

    public String getTuba() {
        return tuba;
    }

    public String getAeg() {
        return aeg;
    }
}
