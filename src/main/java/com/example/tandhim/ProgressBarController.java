package com.example.tandhim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ProgressBarController implements Initializable {
    ProgressIndicator PI=new ProgressIndicator();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainVbox.getChildren().add(0,PI);
    }

    @FXML
    private Label progressInfo;
    @FXML
    private VBox mainVbox;

    @FXML
    private Button btnClose;

    @FXML
    public void closeStage() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

}
