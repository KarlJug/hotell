package com.hbs.hotell.controllers.admin;

import com.hbs.hotell.DB.Broneering;
import com.hbs.hotell.DB.BroneeringDAO;
import com.hbs.hotell.DB.Klient;
import com.hbs.hotell.DB.KlientDAO;
import com.hbs.hotell.DatabaseConnectionManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BookingController implements Initializable {
    public Button lisa_isik_btn;
    public Button refresh_btn;
    public TableView<Broneering> tableView;
    public TableColumn<Broneering, Long> book_col_id;
    public TableColumn<Broneering, String> book_col_eesnimi;
    public TableColumn<Broneering, String> book_col_perenimi;
    public TableColumn<Broneering, Integer> book_col_tuba;
    public TableColumn<Broneering, LocalDate> book_col_alg_aeg;
    public TableColumn<Broneering, LocalDate> book_col_lopp_aeg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Lisab property value mille järgi saad andmeid lisada õigetesse tulpadesse
        book_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        book_col_eesnimi.setCellValueFactory(new PropertyValueFactory<>("kulastaja_eesnimi"));
        book_col_perenimi.setCellValueFactory(new PropertyValueFactory<>("kulastaja_perekonnanimi"));
        book_col_tuba.setCellValueFactory(new PropertyValueFactory<>("tuba"));
        book_col_alg_aeg.setCellValueFactory(new PropertyValueFactory<>("alg_aeg"));
        book_col_lopp_aeg.setCellValueFactory(new PropertyValueFactory<>("lopp_aeg"));

        tableView.setItems(columnData());

        // hettkel ei tee midagi aga peaks laskma vailda tulba ja siis lugeda andmed sealt nagu KlientController-is
        tableView.setEditable(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void addUserView(ActionEvent event) {
    }
    
    // Uuendab andmeid kui vajutad nuppu
    public void refreshTable(ActionEvent event) {
        tableView.setItems(columnData());
    }

    // Võtab andmed andmebaasist
    public ObservableList<Broneering> columnData() {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hotell",
                "postgres", "Passw0rd");

        try {
            Connection connection = dcm.getConnection();
            BroneeringDAO broneeringDAO = new BroneeringDAO(connection);
            return broneeringDAO.findAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
