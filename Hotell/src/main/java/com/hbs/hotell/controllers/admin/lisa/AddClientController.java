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
import java.util.ResourceBundle;

public class AddClientController implements Initializable {
    public TextField eesnimi;
    public TextField perenimi;
    public TextField isikukood;
    public TextField email;
    public Button kinnita_btn;
    public Text error;
    // eraldi klass vaja teha


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        kinnita_btn.setOnAction(event -> onConfirm());
    }

    private void onConfirm() {

        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hotell",
                "postgres", "Passw0rd");

        boolean isValid = false;
        try {
            Connection connection = dcm.getConnection();
            KlientDAO klientDAO = new KlientDAO(connection);
            Klient klient = new Klient();

            // vaja rohkem valideerimisi
            if (eesnimi.getText().length() <= 30) {
                klient.setEesnimi(eesnimi.getText());
                isValid = true;
            } else { error.setText("Error: Nimi on pikkem kui 30 tähte"); }
            if (perenimi.getText().length() <= 30) {
                klient.setPere_nimi(perenimi.getText());
            } else {
                error.setText("Error: perekonnanimi on pikkem kui 30 tähte");
                isValid = false;
            }
            if (isikukood.getText().length() <= 11) {
                klient.setIsikukood(isikukood.getText());
                isValid = true;
            } else {
                error.setText("Error: isikukood on pikkem kui 11 tähte");
                isValid = false;
            }
            if (Validator.validate(email.getText())) {
                klient.setEmail(email.getText());
                isValid = true;
            } else {
                error.setText("Error: Email is not valid");
                isValid = false;
            }
            if (isValid) { klientDAO.create(klient); }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (isValid) {
            Stage stage = (Stage) kinnita_btn.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        }
    }


}
