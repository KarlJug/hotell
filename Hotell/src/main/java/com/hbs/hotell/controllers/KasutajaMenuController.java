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

        login_btn.setOnAction(event -> loginView());

    }

    private void loginView() {
        Stage stage = (Stage) login_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
    }

}
