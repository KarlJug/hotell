package local.hotelli_susteem;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;

public class HotelApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /*
        FXMLLoader fxmlLoader = new FXMLLoader(HotelApplication.class.getResource("kasutaja-vaade.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //scene.getStylesheets().add(getClass().getResource("Stylesheet.css").toExternalForm());
        String css = this.getClass().getResource("Stylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
        */

        Parent root = FXMLLoader.load(getClass().getResource("kasutaja-vaade.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


        /*
        Parent root = FXMLLoader.load(getClass().getResource("kasutaja-vaade.fxml"));
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        */

        stage.setResizable(false);
        stage.setTitle("Hotel");


    }

    public static void main(String[] args) {
        launch();
    }
}