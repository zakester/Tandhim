package com.example.tandhim;

import com.example.tandhim.Models.EditBonSearch;
import com.example.tandhim.Models.Impression.publish;
import com.example.tandhim.Models.Letter;
import com.example.tandhim.Models.Obligatoire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
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
    EditBonSearch result;
    String typePv;
    Obligatoire obligatoire;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void setValues(String typePv, Obligatoire obligatoire, EditBonSearch result) {
        this.result=result;
        this.typePv=typePv;
        this.obligatoire=obligatoire;
        obligLetterArea.setText("إسم المطلوب :"+obligatoire.getNom()+" \n "+obligatoire.OblStatus());
        typePV.setText("تعليق محضر: " + typePv);
        if (!typePv.equals("تكليف بالحضور لجلسة")) {
            typeRqst.setVisible(false);
        }
    }
    public void SavePublish() {
        if (adreessing.getText()==null || response.getText()==null || date.getValue()==null || adreessing.getText().equals("")|| response.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"عليك ملء جميع الخانات");
        }
        else {
            publish pub= new publish(result.getId(),obligatoire.getObligatoireId(),typePv);
            pub.setAdressed(adreessing.getText());
            pub.setResponse(response.getText());
            if (typePV.equals("تكليف بالحضور لجلسة")) {
                pub.setTypeRqst(typeRqst.getText());
            }
            pub.insert();
            obligatoire.setStatus("تعليق (غير مبلغ)");
            obligatoire.updateObligatoire();
            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
        }
    }
    public void PrintPublish() {
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }
}
