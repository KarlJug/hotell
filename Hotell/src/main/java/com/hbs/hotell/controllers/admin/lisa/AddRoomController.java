package com.hbs.hotell.controllers.admin.lisa;

import com.hbs.hotell.DB.Hotellituba;
import com.hbs.hotell.util.Validator;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;


public class AddRoomController implements Initializable {
    public TextField ruum_num_tfd;
    public ComboBox<Integer> type_cbb;
    public TextField hind_tfd;
    public Button kinnita_btn;
    public Text error;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type_cbb.setItems(FXCollections.observableArrayList(1, 2, 3, 4));

    }

    public void onConfirm() {
        Hotellituba tuba = new Hotellituba();
        tuba.setToa_num(Validator.isNumber(ruum_num_tfd));
        tuba.setToa_type(type_cbb.getValue());
        tuba.setHind(Validator.isNumber(hind_tfd));


    }

}
