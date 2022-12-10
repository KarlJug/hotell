package com.hbs.hotell.controllers.admin;

import com.hbs.hotell.DB.Hotellituba;
import com.hbs.hotell.DB.HotellitubaDAO;
import com.hbs.hotell.DatabaseConnectionManager;
import com.hbs.hotell.model.Model;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RoomsController implements Initializable {
    public Button lisa_isik_btn;
    public Button refresh_btn;
    public TableView<Hotellituba> tableView;
    public TableColumn<Hotellituba, Integer> room_col_id;
    public TableColumn<Hotellituba, Integer> room_col_num;
    public TableColumn<Hotellituba, Integer> room_col_type;
    public TableColumn<Hotellituba, Integer> room_col_voodi_arv;
    public TableColumn<Hotellituba, Integer> room_col_hind;
    public TableColumn<Hotellituba, Boolean> room_col_bron;
    public TableColumn<Hotellituba, String> room_col_bronia_nimi;
    public TableColumn<Hotellituba, String> room_col_bronia_perenimi;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // seob väljale vastavalt väärtuse mille järgi saab õigele väljale õige andme anda
        room_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        room_col_num.setCellValueFactory(new PropertyValueFactory<>("toa_num"));
        room_col_type.setCellValueFactory(new PropertyValueFactory<>("toa_type"));
        room_col_voodi_arv.setCellValueFactory(new PropertyValueFactory<>("voodikohtade_arv"));
        room_col_hind.setCellValueFactory(new PropertyValueFactory<>("hind"));
        room_col_bron.setCellValueFactory(new PropertyValueFactory<>("broneeritud"));
        room_col_bronia_nimi.setCellValueFactory(new PropertyValueFactory<>("broneeria_eesnimi"));
        room_col_bronia_perenimi.setCellValueFactory(new PropertyValueFactory<>("broneeria_perekonnanimi"));

        tableView.setItems(columnData());

        tableView.setEditable(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


        // teeb akna lahti kus saab uue ruumi lisada
        lisa_isik_btn.setOnAction(event -> Model.getInstance().getViewFactory().showAddRoom());
    }

    // Võtab andmed andmebaasist
    private ObservableList<Hotellituba> columnData() {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hotell",
                "postgres", "Passw0rd");

        try {
            Connection connection = dcm.getConnection();
            HotellitubaDAO HotellitubaDAO = new HotellitubaDAO(connection);
            return HotellitubaDAO.findAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Ei tee midagi
    // Peaks ruumi saama lisada
    public void addUserView(ActionEvent event) {
    }
    public void refreshTable(ActionEvent event) {
        columnData();
    }
}
