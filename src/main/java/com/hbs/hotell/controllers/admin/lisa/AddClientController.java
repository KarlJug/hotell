package com.hbs.hotell.controllers.admin.lisa;

import com.hbs.hotell.DB.Klient;
import com.hbs.hotell.DB.KlientDAO;
import com.hbs.hotell.DatabaseConnectionManager;
import com.hbs.hotell.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddClientController implements Initializable {
    public TextField eesnimi;
    public TextField perenimi;
    public TextField isikukood;
    public TextField email;
    public Button kinnita_btn;
    public Text error;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        kinnita_btn.setOnAction(event -> onConfirm());
    }

    private void onConfirm() {

        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hotell",
                "postgres", "Passw0rd");

            boolean isvalid = false;
        try {
            Connection connection = dcm.getConnection();
            KlientDAO klientDAO = new KlientDAO(connection);
            Klient klient = new Klient();

            if (validate(email.getText())) {
                klient.setEesnimi(eesnimi.getText());
                klient.setPere_nimi(perenimi.getText());
                klient.setIsikukood(isikukood.getText());
                klient.setEmail(email.getText());
                klientDAO.create(klient);
                System.out.println("Yes");
                isvalid = true;
            } else {
                System.out.println("why");
                error.setText("Error: Email is not valid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (isvalid) {
            Stage stage = (Stage) kinnita_btn.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        }
    }

    private static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
