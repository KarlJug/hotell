package com.hbs.hotell.DB;

import com.hbs.hotell.util.DataTransferObject;

public class Broneering implements DataTransferObject {

    private long id;
    private String kulastaja_eesnimi, kulastaja_perekonnanimi;
    private int tuba;
    private LocalDate alg_aeg, lõpp_aeg;

    public void setId(long id) {
        this.id = id;
    }

    public void setKulastaja_eesnimi(String kulastaja_eesnimi) {
        this.kulastaja_eesnimi = kulastaja_eesnimi;
    }

    public void setKulastaja_perekonnanimi(String kulastaja_perekonnanimi) {
        this.kulastaja_perekonnanimi = kulastaja_perekonnanimi;
    }

    public void setTuba(int tuba) {
        this.tuba = tuba;
    }

    public void setAlg_aeg(LocalDate alg_aeg) {
        this.alg_aeg = alg_aeg;
    }

    public void setLõpp_aeg(LocalDate lõpp_aeg) {
        this.lõpp_aeg = lõpp_aeg;
    }

    @Override
    public long getId() {
        return id;
    }

    public String getKulastaja_eesnimi() {
        return kulastaja_eesnimi;
    }

    public String getKulastaja_perekonnanimi() {
        return kulastaja_perekonnanimi;
    }

    public int getTuba() {
        return tuba;
    }

    public LocalDate getAlg_aeg() {
        return alg_aeg;
    }

    public LocalDate getLõpp_aeg() {
        return lõpp_aeg;
    }
}
