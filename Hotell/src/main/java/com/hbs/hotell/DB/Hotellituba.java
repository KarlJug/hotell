package com.hbs.hotell.DB;

import com.hbs.hotell.util.DataTransferObject;

public class Hotellituba implements DataTransferObject {

    private long id;
    private int toa_num, toa_type, voodikohtade_arv, hind;
    private boolean broneeritud;
    private String broneeria_eesnimi, broneeria_perekonnanimi;

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

    public int getToa_num() {
        return toa_num;
    }

    public void setToa_num(int toa_num) {
        this.toa_num = toa_num;
    }

    public int getVoodikohtade_arv() {
        return voodikohtade_arv;
    }

    public void setVoodikohtade_arv(int voodikohtade_arv) {
        this.voodikohtade_arv = voodikohtade_arv;
    }

    public int getHind() {
        return hind;
    }

    public void setHind(int hind) {
        this.hind = hind;
    }

    public boolean isBroneeritud() {
        return broneeritud;
    }

    public void setBroneeritud(boolean broneeritud) {
        this.broneeritud = broneeritud;
    }

    public String getBroneeria_eesnimi() {
        return broneeria_eesnimi;
    }

    public void setBroneeria_eesnimi(String broneeria_eesnimi) {
        this.broneeria_eesnimi = broneeria_eesnimi;
    }

    public String getBroneeria_perekonnanimi() {
        return broneeria_perekonnanimi;
    }

    public void setBroneeria_perekonnanimi(String broneeria_perekonnanimi) {
        this.broneeria_perekonnanimi = broneeria_perekonnanimi;
    }

    public int getToa_type() {
        return toa_type;
    }

    public void setToa_type(int toa_type) {
        this.toa_type = toa_type;
    }
}
