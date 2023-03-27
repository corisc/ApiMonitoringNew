package com.etc.apiMonitor.views;

import com.etc.apiMonitor.controllers.Client.TransactionsCellController;
import com.etc.apiMonitor.models.TestInstance;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class TransactionCellFactory extends ListCell<TestInstance> {

    @Override
    protected void updateItem(TestInstance testInstance, boolean empty) {
        super.updateItem(testInstance, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/client/transactionCell.fxml"));
            TransactionsCellController controller = new TransactionsCellController(testInstance);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
