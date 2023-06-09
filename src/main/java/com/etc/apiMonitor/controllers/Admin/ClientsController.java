package com.etc.apiMonitor.controllers.Admin;

import com.etc.apiMonitor.models.Model;
import com.etc.apiMonitor.views.ClientCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;


public class ClientsController implements Initializable {
    public ListView clients_listview;
    public ClientCellFactory cellFactory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initClientsList();
        clients_listview.setItems(Model.getInstance().getUsers());
        clients_listview.setCellFactory(e -> new ClientCellFactory());
    }

    private void initClientsList() {
        if (Model.getInstance().getUsers().isEmpty()) {
            Model.getInstance().setUsers();
        }
    }
}
