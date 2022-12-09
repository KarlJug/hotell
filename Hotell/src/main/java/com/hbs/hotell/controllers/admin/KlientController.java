package com.hbs.hotell.controllers.admin;

import com.hbs.hotell.DB.Klient;
import com.hbs.hotell.DB.KlientDAO;
import com.hbs.hotell.DatabaseConnectionManager;
import com.hbs.hotell.controllers.admin.lisa.EditClientControlle;
import com.hbs.hotell.model.Model;
import com.hbs.hotell.util.Validator;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class KlientController implements Initializable {
    public Button lisa_isik_btn;
    public Button muuda_isik_btn;
    public Button refresh_btn;
    public Button delete_btn;

    public TableView<Klient> tableView;
    public TableColumn<Klient, Integer> client_col_id;
    public TableColumn<Klient, String> client_col_nimi;
    public TableColumn<Klient, String> client_col_pere_nimi;
    public TableColumn<Klient, String> client_col_isikukood;
    public TableColumn<Klient, String> client_col_email;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // seob väljale vastavalt väärtuse mille järgi saab õigele väljale õige andme anda
        client_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        client_col_nimi.setCellValueFactory(new PropertyValueFactory<>("eesnimi"));
        client_col_pere_nimi.setCellValueFactory(new PropertyValueFactory<>("pere_nimi"));
        client_col_isikukood.setCellValueFactory(new PropertyValueFactory<>("isikukood"));
        client_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableView.setItems(columnData());
        // teeb tulbad muudetavaks
        tableView.setEditable(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        client_col_nimi.setCellFactory(TextFieldTableCell.forTableColumn());
        client_col_pere_nimi.setCellFactory(TextFieldTableCell.forTableColumn());
        client_col_isikukood.setCellFactory(TextFieldTableCell.forTableColumn());
        client_col_email.setCellFactory(TextFieldTableCell.forTableColumn());

        refresh_btn.setOnAction(event -> tableView.setItems(columnData()));
        lisa_isik_btn.setOnAction(event -> onShowAddClient());
        muuda_isik_btn.setOnAction(event -> editClient());
    }
    
    // siin on vist viga ja kustuab. pean ülevaatama
    // Peaks nuppu vajutusel akna avama kus saad uue kliendi lisada
    private void onShowAddClient() {

        ObservableList<Klient> klient, row;
        klient = tableView.getItems();
        row = tableView.getSelectionModel().getSelectedItems();

        for (Klient r : row) {
            klient.remove(r);
        }

        Model.getInstance().getViewFactory().showAddClient();
    }
    
    // Saab andmebaasist info kõigi klientide peale
    public ObservableList<Klient> columnData() {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hotell",
                "postgres", "Passw0rd");

        try {
            Connection connection = dcm.getConnection();
            KlientDAO klientDAO = new KlientDAO(connection);
            return klientDAO.findAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // muuda nuppule saadab info
    public void editClient() {
        FXMLLoader loader = Model.getInstance().getViewFactory().showEditClient();
        EditClientControlle editClientControlle = loader.getController();

        ObservableList<Klient> row;
        row = tableView.getSelectionModel().getSelectedItems();

        for (Klient r : row) {
            editClientControlle.eesnimi.setText(r.getEesnimi());
            editClientControlle.perenimi.setText(r.getPere_nimi());
            editClientControlle.isikukood.setText(r.getIsikukood());
            editClientControlle.email.setText(r.getEmail());
            editClientControlle.id_fld.setText(Long.toString(r.getId()));

        }
    }
    
    // kui topelt klikid nime peale saad seda muuta
    public void editFirsName(TableColumn.CellEditEvent cellEditEvent) {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hotell",
                "postgres", "Passw0rd");

        try {
            Connection connection = dcm.getConnection();
            KlientDAO klientDAO = new KlientDAO(connection);

            Klient clientSelected = tableView.getSelectionModel().getSelectedItem();
            clientSelected.setEesnimi(cellEditEvent.getNewValue().toString());
            klientDAO.update(clientSelected);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // kui topelt klikid perekonnanime peale saad seda muuta
    public void editLastName(TableColumn.CellEditEvent cellEditEvent) {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hotell",
                "postgres", "Passw0rd");

        try {
            Connection connection = dcm.getConnection();
            KlientDAO klientDAO = new KlientDAO(connection);

            Klient clientSelected = tableView.getSelectionModel().getSelectedItem();
            clientSelected.setPere_nimi(cellEditEvent.getNewValue().toString());
            klientDAO.update(clientSelected);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // kui topelt klikid isikukoodi peale saad seda muuta
    public void editCode(TableColumn.CellEditEvent cellEditEvent) {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hotell",
                "postgres", "Passw0rd");

        try {
            Connection connection = dcm.getConnection();
            KlientDAO klientDAO = new KlientDAO(connection);

            Klient clientSelected = tableView.getSelectionModel().getSelectedItem();
            clientSelected.setIsikukood(cellEditEvent.getNewValue().toString());

            if (clientSelected.getIsikukood().length() <= 11) {
                klientDAO.update(clientSelected);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // kui topelt klikid emaili peale saad seda muuta
    public void editEmail(TableColumn.CellEditEvent cellEditEvent) {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hotell",
                "postgres", "Passw0rd");

        try {
            String temp;
            Connection connection = dcm.getConnection();
            KlientDAO klientDAO = new KlientDAO(connection);

            Klient clientSelected = tableView.getSelectionModel().getSelectedItem();
            temp = clientSelected.getEmail();

            clientSelected.setEmail(cellEditEvent.getNewValue().toString());

            if (Validator.validEmail(clientSelected.getEmail())) {
                klientDAO.update(clientSelected);
            } else {
                clientSelected.setEmail(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // kui vajutad kustuta nuppu ja oled valinud kasutaja
    // siis see kasutaja kustutakse andmebaasist
    public void deleteClient() {

        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hotell",
                "postgres", "Passw0rd");

        try {
            Connection connection = dcm.getConnection();
            KlientDAO klientDAO = new KlientDAO(connection);

            ObservableList<Klient> klient, row;
            klient = tableView.getItems();
            row = tableView.getSelectionModel().getSelectedItems();

            for (Klient r : row) {
                klient.remove(r);
                klientDAO.delete(r.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
