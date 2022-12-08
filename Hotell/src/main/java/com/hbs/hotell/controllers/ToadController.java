package com.hbs.hotell.controllers;

import com.hbs.hotell.DB.HotellitubaDAO;
import com.hbs.hotell.DB.Klient;
import com.hbs.hotell.DB.KlientDAO;
import com.hbs.hotell.DatabaseConnectionManager;
import com.hbs.hotell.model.Model;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    public Label free_rooms_num;
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
        book_btn3.setOnAction(event -> onShowBooking());


    }

    public void getFreeRooms() {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hotell",
                "postgres", "Passw0rd");

        try {
            Connection connection = dcm.getConnection();
            HotellitubaDAO hotellitubaDAO = new HotellitubaDAO(connection);

            free_rooms_num.setText(free_rooms_num.getText() + " " + hotellitubaDAO.findAllAvailable(1).size());
            free_rooms_num2.setText(free_rooms_num2.getText() + " " + hotellitubaDAO.findAllAvailable(2).size());
            free_rooms_num3.setText(free_rooms_num3.getText() + " " + hotellitubaDAO.findAllAvailable(3).size());
            free_rooms_num4.setText(free_rooms_num4.getText() + " " + hotellitubaDAO.findAllAvailable(4).size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   // poolik
    public void getPrice() {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hotell",
                "postgres", "Passw0rd");

        try {
            Connection connection = dcm.getConnection();
            HotellitubaDAO hotellitubaDAO = new HotellitubaDAO(connection);

            price_lbl1.setText(price_lbl1.getText() + " ");

        } catch (SQLException e) {
            e.printStackTrace(230);
        }
    }
    public void book1(ActionEvent actionEvent) {
        onShowBooking(230);
    }
    public void book2(ActionEvent actionEvent) {onShowBooking();}
    public void book3(ActionEvent actionEvent) {onShowBooking();}
    public void book4(ActionEvent actionEvent) {onShowBooking();}

    private void onShowBooking(int price) {
        FXMLLoader loader = Model.getInstance().getViewFactory().showBooking();
        BookARoomController bookARoomController = loader.getController();
        bookARoomController.price_lbl.setText(Integer.toString(price));
    }
}
