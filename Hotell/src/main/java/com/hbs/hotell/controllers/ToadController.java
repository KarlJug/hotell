package com.hbs.hotell.controllers;

import com.hbs.hotell.DB.HotellitubaDAO;
import com.hbs.hotell.DB.Klient;
import com.hbs.hotell.DB.KlientDAO;
import com.hbs.hotell.DatabaseConnectionManager;
import com.hbs.hotell.controllers.lisa.BookARoomController;
import com.hbs.hotell.model.Model;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ToadController implements Initializable {
    public Label bed_num_lbl1;
    public Label price_lbl1;
    public Button book_btn1;
    public Label bed_num_lbl2;
    public Label price_lbl2;
    public Button book_btn2;
    public Label free_rooms_num1;
    public Label free_rooms_num2;
    public Label bed_num_lbl3;
    public Label price_lbl3;
    public Label free_rooms_num3;
    public Button book_btn3;
    public Label bed_num_lbl4;
    public Label price_lbl4;
    public Label free_rooms_num4;
    public Button book_btn4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getFreeRooms();

    }

    public void getFreeRooms() {

        try {
            DatabaseConnectionManager dcm = new DatabaseConnectionManager();
            Connection connection = dcm.getConnection();
            HotellitubaDAO hotellitubaDAO = new HotellitubaDAO(connection);
            // leiab vastavalt toale kui paljud nendest on vabad
            free_rooms_num1.setText(free_rooms_num1.getText() + " " + hotellitubaDAO.findAllAvailable(1).size());
            free_rooms_num2.setText(free_rooms_num2.getText() + " " + hotellitubaDAO.findAllAvailable(2).size());
            free_rooms_num3.setText(free_rooms_num3.getText() + " " + hotellitubaDAO.findAllAvailable(3).size());
            free_rooms_num4.setText(free_rooms_num4.getText() + " " + hotellitubaDAO.findAllAvailable(4).size());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // poolik ei kasuta peaks andme baasist hiinad võtma ja näitama
    public void getPrice() {

        try {
            DatabaseConnectionManager dcm = new DatabaseConnectionManager();
            Connection connection = dcm.getConnection();
            HotellitubaDAO hotellitubaDAO = new HotellitubaDAO(connection);

            price_lbl1.setText(price_lbl1.getText() + " ");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // kui vajutad nuppu aktiveerib (book1 - book4) mis annab edasi info funktsioonile onShowBooking()
    public void book1(ActionEvent actionEvent) {
        String[] parts = price_lbl1.getText().split(" ");
        String price = parts[parts.length - 1];
        // voodite arv
        String[] bedsArr = bed_num_lbl1.getText().split("");
        String bed = bedsArr[bedsArr.length - 1];
        onShowBooking(price, "1", bed);
    }

    public void book2(ActionEvent actionEvent) {
        String[] parts = price_lbl2.getText().split(" ");
        String price = parts[parts.length - 1];
        // voodite arv
        String[] bedsArr = bed_num_lbl2.getText().split("");
        String bed = bedsArr[bedsArr.length - 1];
        onShowBooking(price, "2", bed);
    }

    public void book3(ActionEvent actionEvent) {
        String[] parts = price_lbl3.getText().split(" ");
        String price = parts[parts.length - 1];
        // voodite arv
        String[] bedsArr = bed_num_lbl3.getText().split("");
        String bed = bedsArr[bedsArr.length - 1];
        onShowBooking(price, "3", bed);
    }

    public void book4(ActionEvent actionEvent) {
        // hind
        String[] parts = price_lbl4.getText().split(" ");
        String price = parts[parts.length - 1];
        // voodite arv
        String[] bedsArr = bed_num_lbl4.getText().split("");
        String bed = bedsArr[bedsArr.length - 1];

        onShowBooking(price, "4", bed);
    }

    // näitab broneerimis akent ja viib selle controllerisse vajalikud andmed nagu hind ja toa tüüp
    private void onShowBooking(String price, String type, String bed) {
        FXMLLoader loader = Model.getInstance().getViewFactory().showBooking();
        BookARoomController bookARoomController = loader.getController();

        // toa hind
        bookARoomController.price_lbl.setText(bookARoomController.price_lbl.getText() + price);
        // toa tüüp
        bookARoomController.type_lbl.setText(bookARoomController.type_lbl.getText() + type);
        // voodite arv
        bookARoomController.beds_num_lbl.setText(bookARoomController.beds_num_lbl.getText() + bed);
    }
}
