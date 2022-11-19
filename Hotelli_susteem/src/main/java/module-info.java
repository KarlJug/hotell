module local.hotelli_susteem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens local.hotelli_susteem to javafx.fxml;
    exports local.hotelli_susteem;
}