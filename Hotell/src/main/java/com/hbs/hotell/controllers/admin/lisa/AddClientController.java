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
import java.util.ResourceBundle;

public class AddClientController implements Initializable {
    public TextField eesnimi;
    public TextField perenimi;
    public TextField isikukood;
    public TextField email;
    public Button kinnita_btn;
    public Text error_txt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        kinnita_btn.setOnAction(event -> onConfirm());
    }

    private void onConfirm() {


        boolean isValid = false;


        String error = "";
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

        error_txt.setText(error);
        isValid = (error.length() == 0);

        if (isValid) {
            try {
                DatabaseConnectionManager dcm = new DatabaseConnectionManager();
                Connection connection = dcm.getConnection();
                KlientDAO klientDAO = new KlientDAO(connection);
                Klient klient = new Klient();

                klient.setEesnimi(eesnimi.getText());
                klient.setPere_nimi(perenimi.getText());
                klient.setIsikukood(isikukood.getText());
                klient.setEmail(email.getText());

                // Kui kõik õige siis tekitab uue kliendi
                klientDAO.create(klient);


            } catch (Exception e) {
                e.printStackTrace();
            }

            // kui kõik õige siis sulgeb akna

            Stage stage = (Stage) kinnita_btn.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        }
    }


}
