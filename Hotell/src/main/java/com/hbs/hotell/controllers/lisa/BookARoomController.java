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
        if (date_start.getValue() != "") {
            date_end.setOnAction(event -> calculatePrice());
        }

        broneeri_btn.setOnAction(event -> onConfirm());

    }

    private void calculatePrice() {
        LocalDate localDateStart = date_start.getValue();
        LocalDate localDateEnd = date_end.getValue();
        Period period = localDateStart.until(localDateEnd);

        int days = period.getDays();

        if (days > 1) {
            calculate_lbl.setText("1 tuba x " + days + " ööd");
        } else {
            calculate_lbl.setText("1 tuba x " + days + " öö");
        }



    }


    private void onConfirm() {

        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hotell",
            "postgres", "Passw0rd");

        boolean isValid = false, exists = false;
        try {
            Connection connection = dcm.getConnection();
            HotellitubaDAO hotellitubaDAO = new HotellitubaDAO(connection);
            KlientDAO klientDAO = new KlientDAO(connection);
            BroneeringDAO broneeringDAO = new BroneeringDAO(connection);

            Hotellituba tuba = new Hotellituba();
            Broneering broneering = new Broneering();
            Klient klient = new Klient();

            klient.setEesnimi(nimi_tfd.getText());
            klient.setPere_nimi(pere_nimi_tfd.getText());
            klient.setIsikukood(isikukood_tfd.getText());
            klient.setEmail(email_tfd.getText());
            
            if (klientDAO.findByPersonalCode(klient.getIsikukood())) {
                exists = true;
            }

            if (isValid && !exists) {
                klientDAO.create(klient);
                broneeringDAO.create(broneering);
            }
            else if (isValid && exists) {
                broneeringDAO.create(broneering);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
