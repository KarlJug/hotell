package com.hbs.hotell.controllers.admin.lisa;

import com.hbs.hotell.DB.Klient;
import com.hbs.hotell.DB.KlientDAO;
import com.hbs.hotell.DatabaseConnectionManager;
import com.hbs.hotell.model.Model;
import com.hbs.hotell.util.Validator;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditClientControlle implements Initializable {
    public TextField eesnimi;
    public TextField perenimi;
    public TextField isikukood;
    public TextField email;
    public TextField id_fld;
    public Button kinnita_btn;
    public Text error_txt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        kinnita_btn.setOnAction(event -> onConfirm());
    }

    public void onConfirm() {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hotell",
                "postgres", "Passw0rd");


        String error = "";
        boolean isValid = false;

        // kontroll et kas kõik andmed sobivad
        if (eesnimi.getText().length() > 30 || eesnimi.getText().length() == 0) {
            error += "Nimi on liiga pikk (30) või tühi\n";
        }
        if (perenimi.getText().length() > 30 || perenimi.getText().length() == 0) {
            error += "Perekonnanimi on liiga pikk (30) või tühi\n";
        }
        if (isikukood.getText().length() != 11 || !Validator.hasNumbers(isikukood.getText())) {
            error += "Isikukood on vale\n";
            System.out.println(isikukood.getText().length());
        }
        if (!Validator.validEmail(email.getText())) {
            error += "Vale email";
        }
        if (!Validator.hasNumbers(id_fld.getText())) {
            error += "ID ei ole number";
        }
        
        // näitab errorit
        error_txt.setText(error);
        // kui errorit pole siis on kõik õige / isValid = true
        isValid = (error.length() == 0);

        if (isValid) {
            Klient klient = new Klient();

            klient.setEesnimi(eesnimi.getText());
            klient.setPere_nimi(perenimi.getText());
            klient.setIsikukood(isikukood.getText());
            klient.setEmail(email.getText());
            klient.setId(Long.parseLong(id_fld.getText()));

            try {
                Connection connection = dcm.getConnection();
                KlientDAO klientDAO = new KlientDAO(connection);
                // uuendab infot vastavalt ID-le (setID)
                klientDAO.update(klient);
                
                // sulgeb akna
                Stage stage = (Stage) kinnita_btn.getScene().getWindow();
                Model.getInstance().getViewFactory().closeStage(stage);


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
