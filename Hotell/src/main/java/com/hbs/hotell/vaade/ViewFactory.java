package com.hbs.hotell.vaade;

import com.hbs.hotell.controllers.ClientController;
import com.hbs.hotell.controllers.admin.AdminController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
    // Kliendi vaade
    private AnchorPane bookingView;
    private AnchorPane infoView;
    private final StringProperty clientSelectMenuItem;

    // admin vaade
    private final StringProperty adminSelectMenuItem;
    private AnchorPane clientsView;
    private AnchorPane bookedView;
    private AnchorPane roomsView;


    public ViewFactory() {
        this.clientSelectMenuItem = new SimpleStringProperty("");
        this.adminSelectMenuItem = new SimpleStringProperty("");
    }

    public StringProperty getClientSelectMenuItem() {
        return clientSelectMenuItem;
    }

    public StringProperty getAdminSelectMenuItem() {
        return adminSelectMenuItem;
    }

    // Getter-id vaatavad kas see class on juba olemas ja kui ei ole siis teeb
    // getter-id hoiavad laetud class-e, et ei peaks olemas olevat FXML-i uuesti laadima
    public AnchorPane getBookingView() {
        if (bookingView == null) {
            try {
                bookingView = new FXMLLoader(getClass().getResource("/FXML/kasutaja/toad.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bookingView;
    }

    public AnchorPane getInfoView() {
        if (infoView == null) {
            try {
                infoView = new FXMLLoader(getClass().getResource("/FXML/kasutaja/info-vaade.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return infoView;
    }

    // Admin
    public AnchorPane getClientsView() {
        if (clientsView == null) {
            try {
                clientsView = new FXMLLoader(getClass().getResource("/FXML/admin/kliendid-vaade.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clientsView;
    }

    public AnchorPane getBookedView() {
        if (bookedView == null) {
            try {
                bookedView = new FXMLLoader(getClass().getResource("/FXML/admin/broneeringu-vaade.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bookedView;
    }

    public AnchorPane getRoomsView() {
        if (roomsView == null) {
            try {
                roomsView = new FXMLLoader(getClass().getResource("/FXML/admin/hotellitoad-vaade.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return roomsView;
    }


    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/login.fxml"));
        createStage(loader);
    }

    public void showClientWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/kasutaja/kasutaja.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
        createStage(loader);
    }

    public void showAdminWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/admin/admin.fxml"));
        AdminController adminController = new AdminController();
        loader.setController(adminController);
        createStage(loader);
    }

    public void showAddClient() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/admin/lisa/lisa-klient.fxml"));
        createStage(loader);
    }

    public void showBooking() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/kasutaja/lisa/book-room.fxml"));
        createStage(loader);
    }

    public void showAddRoom() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/admin/lisa/lisa-ruum.fxml"));
        createStage(loader);
    }

    // Teeb stage-i
    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Hotell");
        stage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }
}
