package com.hbs.hotell.controllers.admin;

import com.hbs.hotell.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class KlientController implements Initializable {
    public Button lisa_isik_btn;
    public Button refresh_btn;
    public TreeTableColumn client_col_id;
    public TreeTableColumn client_col_nimi;
    public TreeTableColumn client_col_pere_nimi;
    public TreeTableColumn client_col_isikukood;
    public TreeTableColumn client_col_email;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lisa_isik_btn.setOnAction(event -> onAdd());
    }

    private void onAdd() {
        Model.getInstance().getViewFactory().showAddClient();
    }


}
