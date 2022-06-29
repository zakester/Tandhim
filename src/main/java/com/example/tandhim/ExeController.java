package com.example.tandhim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ExeController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private HBox hboxStatus;

    @FXML
    private Button btnExe;

    @FXML
    private Button btnNonExe;

    @FXML
    private StackPane stackpane;

    @FXML
    private Pane pnlExe;

    @FXML
    private DatePicker dateExe;

    @FXML
    private Button editBtnPrintPV;

    @FXML
    private Button btnSave;

    @FXML
    void SaveEdit(ActionEvent event) {

    }

    @FXML
    public void handleClicks(ActionEvent actionEvent) {
        applyPressedStyle((Button) actionEvent.getSource());
    }
    public void resetStyle() {
        for (Node n:hboxStatus.getChildren()){
            if (n instanceof Button) {
                n.getStyleClass().clear();
                n.getStyleClass().add("button2");
            }
        }
    }
    private void applyPressedStyle(Button button) {
        resetStyle();
        button.getStyleClass().clear();
        button.getStyleClass().add("button1-pressed");
    }
}
