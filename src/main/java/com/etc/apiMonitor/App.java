package com.etc.apiMonitor;

import com.etc.apiMonitor.models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.DriverManager;
import java.sql.SQLException;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        if (checkDbDrivers() || checkAllDbTablesExist()) {
            Model.getInstance().getViewFactory().showLoginWindow();
        } else {
            Model.getInstance().getViewFactory().showErrorWindow();
        }

    }

    private boolean checkAllDbTablesExist() {
        boolean exist = false;
        return exist;
    }

    private static boolean checkDbDrivers() {
        try {
            Class.forName("org.sqlite.JDBC");
            DriverManager.registerDriver(new org.sqlite.JDBC());
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return  false;
        }

    }
}
