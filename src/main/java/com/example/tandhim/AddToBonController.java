package com.example.tandhim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AddToBonController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private StackPane stackpane;

    @FXML
    private Pane pnlExe;

    @FXML
    private TextField numBon;

    @FXML
    private Button btnSearchBon;

    @FXML
    private Button btnNoBon;

    @FXML
    void NoBon(ActionEvent event) {

    }

    @FXML
    void Search() {

    }

    public void okPressed (KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            Search();
        }
    }
}
