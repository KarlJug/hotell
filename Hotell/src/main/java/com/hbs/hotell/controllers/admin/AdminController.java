package com.hbs.hotell.controllers.admin;

import com.hbs.hotell.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    public BorderPane admin_fxml_id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // kuulab getAdminSelectMenuItem() mis nuppu ma seal vajutan ja vastavalt nuppule näitab seda pilti
        Model.getInstance().getViewFactory().getAdminSelectMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case "Booked" -> admin_fxml_id.setCenter(Model.getInstance().getViewFactory().getBookedView());
                case "Rooms" -> admin_fxml_id.setCenter(Model.getInstance().getViewFactory().getRoomsView());
                case "Clients" -> admin_fxml_id.setCenter(Model.getInstance().getViewFactory().getClientsView());
                default -> admin_fxml_id.setCenter(Model.getInstance().getViewFactory().getBookedView());
            }
        });
    }
}
