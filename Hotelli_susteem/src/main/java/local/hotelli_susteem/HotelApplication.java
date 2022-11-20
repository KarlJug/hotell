package local.hotelli_susteem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class HotelApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HotelApplication.class.getResource("hotel-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //scene.getStylesheets().add(getClass().getResource("Stylesheet.css").toExternalForm());
        String css = this.getClass().getResource("Stylesheet.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setResizable(false);

        stage.setTitle("Hotel");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}