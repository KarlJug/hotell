package com.hbs.hotell.controllers;

import com.hbs.hotell.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class KasutajaMenuController implements Initializable {
    public Button booking_btn;
    public Button info_ptn;
    public Button login_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addListeners();

        login_btn.setOnAction(event -> loginView());

    }

    private void onBooking() {
        Model.getInstance().getViewFactory().getClientSelectMenuItem().set("Booking");
    }
    private void onInfo() {
        Model.getInstance().getViewFactory().getClientSelectMenuItem().set("Info");
    }

    private void addListeners() {
        booking_btn.setOnAction(event -> onBooking());
        info_ptn.setOnAction(event -> onInfo());
    }

    // avab akna kust sisse logida ja sulgeb hetkese akna
    private void loginView() {
        Stage stage = (Stage) login_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
    }

}
