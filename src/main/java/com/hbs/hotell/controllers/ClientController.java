package com.hbs.hotell.controllers;

import com.hbs.hotell.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    public BorderPane kasutaja_fxml_id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getClientSelectMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case "Booking" -> kasutaja_fxml_id.setCenter(Model.getInstance().getViewFactory().getBookingView());
                case "Info" -> kasutaja_fxml_id.setCenter(Model.getInstance().getViewFactory().getInfoView());
                default -> kasutaja_fxml_id.setCenter(Model.getInstance().getViewFactory().getBookingView());
            }
        });
    }
}
