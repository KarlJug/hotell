package com.hbs.hotell.DB;

import com.hbs.hotell.util.DataTransferObject;

public class Hotellituba implements DataTransferObject {

    private long id;
    private String toa_number, voodikohtade_arv, hind;

    public void setId(long id) {
        this.id = id;
    }

    public void setToa_number(String toa_number) {
        this.toa_number = toa_number;
    }

    public void setVoodikohtade_arv(String voodikohtade_arv) {
        this.voodikohtade_arv = voodikohtade_arv;
    }

    public void setHind(String hind) {
        this.hind = hind;
    }

    @Override
    public long getId() {
        return id;
    }

    public String getToa_number() {
        return toa_number;
    }

    public String getVoodikohtade_arv() {
        return voodikohtade_arv;
    }

    public String getHind() {
        return hind;
    }
}
