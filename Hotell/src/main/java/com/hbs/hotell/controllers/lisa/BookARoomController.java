package com.hbs.hotell.controllers.lisa;

import com.hbs.hotell.DB.Klient;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class BookARoomController implements Initializable {

    public TextField nimi_tfd;
    public TextField pere_nimi_tfd;
    public TextField isikukood_tfd;
    public TextField email_tfd;
    public DatePicker date_start;
    public DatePicker date_end;
    public Label price_lbl;
    public Label beds_num_lbl;
    public Label calculate_lbl;
    public Label price_night_lbl;
    public Button broneeri_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (date_start.getValue() != null) {
            date_end.setOnAction(event -> calculatePrice());
        }

    }

    private void calculatePrice() {
        LocalDate localDateStart = date_start.getValue();
        LocalDate localDateEnd = date_end.getValue();
        Period period = localDateStart.until(localDateEnd);

        int days = period.getDays();

        if (days > 1) {
            calculate_lbl.setText("1 room x " + days + " nights");
        } else {
            calculate_lbl.setText("1 room x " + days + " night");
        }

    }


    private void onConfirm() {
        Klient klient = new Klient();
        klient.setEesnimi(nimi_tfd.getText());
        klient.setPere_nimi(pere_nimi_tfd.getText());
        klient.setIsikukood(isikukood_tfd.getText());
        klient.setEmail(email_tfd.getText());

    }


}
