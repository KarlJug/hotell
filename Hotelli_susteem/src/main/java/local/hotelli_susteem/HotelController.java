package local.hotelli_susteem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class HotelController implements Initializable {

    public void connectioneCheck(ActionEvent event) {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "hotell", "postgres", "Passw0rd");
        try {
            Connection connection = dcm.getConnection();
            KlientDAO klientDAO = new KlientDAO(connection);

            Klient klient = new Klient();
            klient.setEes_nimi("Uus");
            klient.setPere_nimi("Inimene");
            klient.setEmail("uini@wh.gov");

            klient = klientDAO.create(klient);
            System.out.println(klient.getEes_nimi() + " " + klient.getPere_nimi());


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    VBox verbox;

    // Pildid
    InputStream stream1 = getClass().getResourceAsStream("icons/rooms.png");
    InputStream stream2 = getClass().getResourceAsStream("icons/login.png");
    InputStream stream3 = getClass().getResourceAsStream("icons/userFile.png");

    // info mis on võetud andmebaasist ja näitab seda
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        double with = verbox.getPrefWidth();
        //.setSpacing(2)

        verbox.getChildren().add(new Button("Ruum 1", new HBox(
                new ImageView(new Image(stream1)),
                new Line(10.0f, 10.0f, 10.0f, 30.0f),
                new ImageView(new Image(stream2)),
                new Line(10.0f, 10.0f, 10.0f, 30.0f),
                new ImageView(new Image(stream3))
        )));

    }

}