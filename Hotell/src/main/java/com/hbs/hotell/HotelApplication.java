package com.hbs.hotell;

import com.hbs.hotell.model.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class HotelApplication extends Application {

    @Override
    public void start(Stage stage) {
        Model.getInstance().getViewFactory().showClientWindow();
    }
}
