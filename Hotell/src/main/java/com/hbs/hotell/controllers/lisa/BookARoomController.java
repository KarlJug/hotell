package com.hbs.hotell.controllers.lisa;

import com.hbs.hotell.DB.*;
import com.hbs.hotell.DatabaseConnectionManager;
import com.hbs.hotell.model.Model;
import com.hbs.hotell.util.Validator;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
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
    public Label type_lbl;
    public Text error_txt;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        date_start.setValue(LocalDate.now());
        date_end.setValue(LocalDate.now().plusDays(1));

        date_end.setOnAction(event -> calculatePrice());

        broneeri_btn.setOnAction(event -> onConfirm());

    }

    private void calculatePrice() {
        LocalDate localDateStart = date_start.getValue();
        LocalDate localDateEnd = date_end.getValue();
        Period period = localDateStart.until(localDateEnd);

        int days = period.getDays();

        if (days > 1) {
            calculate_lbl.setText("1 tuba x " + days + " ööd");
        } else if (days == 1) {
            calculate_lbl.setText("1 tuba x " + days + " öö");
        }

        if (days > 0) {

            String[] strings = price_lbl.getText().split(" ");

            double calcPrice = Integer.parseInt(strings[strings.length - 1]) * days;

            price_night_lbl.setText(price_night_lbl.getText() + calcPrice);
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
            String error = "";

            String[] split = type_lbl.getText().split(" ");
            int type = Integer.parseInt(split[split.length - 1]);

            // kontroll et kas kõik andmed sobivad
            if (nimi_tfd.getText().length() > 30 || nimi_tfd.getText().length() == 0) {
                error += "Nimi on liiga pikk (30) või tühi\n";
            }
            if (pere_nimi_tfd.getText().length() > 30 || pere_nimi_tfd.getText().length() == 0) {
                error += "Perekonnanimi on liiga pikk (30) või tühi\n";
            }
            if (isikukood_tfd.getText().length() != 11 || !Validator.hasNumbers(isikukood_tfd.getText())) {
                error += "Isikukood on vale\n";
                System.out.println(isikukood_tfd.getText().length());
            }
            if (date_end.getValue().isBefore(LocalDate.now())) {
                error += "Aeg on vale.\n";
            }
            if (!Validator.validEmail(email_tfd.getText())) {
                error += "Vale email";
            }

            error_txt.setText(error);
            isValid = (error.length() == 0);

            if (isValid) {
                tuba.setBroneeria_eesnimi(nimi_tfd.getText());
                tuba.setBroneeria_perekonnanimi(pere_nimi_tfd.getText());
                tuba.setToa_num(hotellitubaDAO.checkIfRoomAvailable(type));

                klient.setEesnimi(nimi_tfd.getText());
                klient.setPere_nimi(pere_nimi_tfd.getText());
                klient.setIsikukood(isikukood_tfd.getText());
                klient.setEmail(email_tfd.getText());

                broneering.setKulastaja_eesnimi(nimi_tfd.getText());
                broneering.setKulastaja_perekonnanimi(pere_nimi_tfd.getText());
                broneering.setTuba(tuba.getToa_num());
                broneering.setAlg_aeg(date_start.getValue());
                broneering.setLopp_aeg(date_end.getValue());

            }

            if (isValid && klientDAO.findByPersonalCode(klient.getIsikukood())) {
                exists = true;
            }

            if (isValid && !exists) {
                klientDAO.create(klient);
                broneeringDAO.create(broneering);
                hotellitubaDAO.bookARoom(tuba.getToa_num(), tuba);
            } else if (isValid && exists) {
                broneeringDAO.create(broneering);
                hotellitubaDAO.bookARoom(tuba.getToa_num(), tuba);

            }
            if (isValid) {
                Stage stage = (Stage) broneeri_btn.getScene().getWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
