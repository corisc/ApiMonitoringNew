package com.etc.apiMonitor.controllers.Client;

import com.etc.apiMonitor.models.TestInstance;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsCellController implements Initializable {

    public Label trans_date_lbl;
    public Label sender_lbl;
    public Label receiver_lbl;
    public Label amount_lbl;
    private final TestInstance testInstance;

    public TransactionsCellController(TestInstance testInstance) {
        this.testInstance = testInstance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
