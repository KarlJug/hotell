package com.hbs.hotell.controllers.admin;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeTableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class BookingController implements Initializable {
    public Button lisa_isik_btn;
    public Button refresh_btn;
    public TreeTableColumn client_col_id;
    public TreeTableColumn client_col_nimi;
    public TreeTableColumn client_col_pere_nimi;
    public TreeTableColumn client_col_isikukood;
    public TreeTableColumn client_col_email;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addUserView(ActionEvent event) {
    }

    public void refreshTable(ActionEvent event) {
    }

}
