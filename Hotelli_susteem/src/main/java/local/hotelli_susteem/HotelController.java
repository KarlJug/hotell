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
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "test_info", "root", "Passw0rd");
        try {
            Connection connection = dcm.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select UserName from UserAccount");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        }   catch (SQLException e) {
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