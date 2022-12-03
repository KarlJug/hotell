package com.hbs.hotell.controllers.admin;

import com.hbs.hotell.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField login_name;
    public PasswordField login_pass;
    public Label logn_err;
    public Button login_btn;
    public Button cancel_login_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        login_btn.setOnAction(event -> onLogin());
        cancel_login_btn.setOnAction(event -> onCancel());
    }

    private void onLogin() {
        Stage stage = (Stage) login_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showAdminWindow();
    }

    private void onCancel() {
        Stage stage = (Stage) login_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showClientWindow();
    }

}
