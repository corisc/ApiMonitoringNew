module com.learning.dashboard {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.javafx;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    requires org.kordamp.bootstrapfx.core;

    opens com.etc.apiMonitor to javafx.fxml;
    exports com.etc.apiMonitor;
    exports com.etc.apiMonitor.controllers;
    exports com.etc.apiMonitor.controllers.Admin;
    exports com.etc.apiMonitor.controllers.Client;
    exports com.etc.apiMonitor.views;
    exports com.etc.apiMonitor.models;

}