package com.hbs.hotell.controllers.admin;

import com.hbs.hotell.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {

    public Button kliendid_btn;
    public Button broneeringud_ptn;
    public Button hotellitoad_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();

        logout_btn.setOnAction(event -> onLogout());
    }

    // vastavalt nuppule saadab sõnumi AdminController-ile
    private void onBooked() {
        Model.getInstance().getViewFactory().getAdminSelectMenuItem().set("Booked");
    }
    private void onRooms() {
        Model.getInstance().getViewFactory().getAdminSelectMenuItem().set("Rooms");
    }
    private void onClients() {
        Model.getInstance().getViewFactory().getAdminSelectMenuItem().set("Clients");
    }
    // ^^^^^
    private void addListeners() {
        kliendid_btn.setOnAction(event -> onClients());
        broneeringud_ptn.setOnAction(event -> onBooked());
        hotellitoad_btn.setOnAction(event -> onRooms());
    }
    // kui logi välja nuppu vajutad siis paneb selle paneeli kinni ja läheb kliendi broneeringu vaatesse
    private void onLogout() {
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showClientWindow();
    }
}
