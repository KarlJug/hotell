module com.hbs.hotell {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens com.hbs.hotell to javafx.fxml;
    exports com.hbs.hotell;
    exports com.hbs.hotell.controllers;
    exports com.hbs.hotell.controllers.admin;
    exports com.hbs.hotell.DB;
    exports com.hbs.hotell.util;
    exports com.hbs.hotell.vaade;
    opens com.hbs.hotell.controllers to javafx.fxml;
    exports com.hbs.hotell.controllers.admin.lisa;

}