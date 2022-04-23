package com.example.tandhim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class PublishController implements Initializable {
    @FXML
    private Label typePV;

    @FXML
    private TextArea obligLetterArea;

    @FXML
    private StackPane stackpane;

    @FXML
    private Pane pnlCitation;

    @FXML
    private TextField adreessing;

    @FXML
    private TextField response;

    @FXML
    private DatePicker date;

    @FXML
    private TextField typeRqst;

    @FXML
    private HBox hboxStatus1;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSave1;

    @FXML
    void SaveEdit(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void setValues(String typePv, String obligatoire, String letter) {
        obligLetterArea.setText("إسم المطلوب :"+obligatoire+" \n "+letter);
        typePV.setText("تعليق محضر: " + typePv);
    }
}
