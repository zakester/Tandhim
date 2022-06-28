package com.example.tandhim;

import com.example.tandhim.Models.*;
import com.example.tandhim.Models.Impression.publish;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EditDialogueController implements Initializable {

    @FXML
    private Button btnLetter,btnDone,btnConvoqued,bntNotDone,btnPublished;
    @FXML
    private HBox hboxStatus;
    @FXML
    private StackPane stackpane;
    @FXML
    private TextField numLetter,numLetter1,numLetter2,numLetter3;

    @FXML
    private Pane pnlDone,pnlConvoqued,pnlLetter,pnlLetterExe,pnlPublished;

    @FXML
    private DatePicker dateDone,dateConvoqued,dateLetter1,dateLetter2,dateLetter3,dateLetter,datePubCommune,datePubTribunal;
    private boolean Execution;
    private String status,num_bon;
    private Obligatoire oblig;
    private EditBonSearch result;
    Button b;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clearContainer(stackpane.getChildren());
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
    public void SaveEdit(){
        System.out.println(b.getText());
        if ((result.getService().equals("bon_apercus")) || (result.getService().equals("bon_apercu_parorders")) || (result.getService().equals("bon_associations")))
        {
            if (b.getText().equals("تم التبليغ")) {
                if (dateDone.getValue() != null) {
                    System.out.println("i'm here my freind"+ dateDone.getValue());
                    if (result.getService().equals("bon_apercu_parorders")) {
                        BonApercuParOrders bon = result.getBonApercuParOrdersData();
                        bon.setStatus("منجزة");
                        bon.setDate_fin(dateDone.getValue().toString());
                        bon.update();
                        }
                    if (result.getService().equals("bon_apercus")) {
                        BonApercus bon = result.getBonApercuData();
                        bon.setStatus("منجزة");
                        bon.setDate_fin(dateDone.getValue().toString());
                        bon.update();
                    }
                    if (result.getService().equals("bon_associations")) {
                        BonAssociations bon = result.getBonAssociation();
                        bon.setStatus("منجزة");
                        bon.setDate_fin(dateDone.getValue().toString());
                        bon.update();
                        bon.update();
                    }
                    Stage stage = (Stage) btnDone.getScene().getWindow();
                    stage.close();
                } else {
                    JOptionPane op = new JOptionPane();
                    op.showMessageDialog(null, "عليك ملئ جميع الخانات");
                }
            }
        }
        else {
                if (b.getText().equals("تم إشعاره(ا)")) {
                    if (dateConvoqued.getValue() != null) {
                        oblig.setStatus("تم إشعاره(ا)");
                        oblig.setDate(dateConvoqued.getValue().toString());
                        oblig.updateObligatoire();
                        Stage stage = (Stage) btnDone.getScene().getWindow();
                        stage.close();
                    } else {
                        JOptionPane op = new JOptionPane();
                        op.showMessageDialog(null, "عليك ملئ جميع الخانات");
                    }

                }
                if (b.getText().equals("تم التبليغ")) {
                    if (dateDone.getValue() != null) {
                        oblig.setStatus("تم التبليغ");
                        oblig.setDate(dateDone.getValue().toString());
                        oblig.updateObligatoire();
                        Stage stage = (Stage) btnDone.getScene().getWindow();
                        stage.close();
                    } else {
                        JOptionPane op = new JOptionPane();
                        op.showMessageDialog(null, "عليك ملئ جميع الخانات");
                    }
                }
                if (b.getText().equals("غير منجزة")) {
                    if (dateDone.getValue() != null) {
                        oblig.setStatus("غير منجزة");
                        oblig.setDate(null);
                        oblig.updateObligatoire();
                        Stage stage = (Stage) btnDone.getScene().getWindow();
                        stage.close();
                    } else {
                        JOptionPane op = new JOptionPane();
                        op.showMessageDialog(null, "عليك ملئ جميع الخانات");
                    }

                }
                if (b.getText().equals("تم إرسال رسالة")) {
                    if (!Execution) {
                        System.out.println("not executif");
                        if (dateLetter.getValue() != null && numLetter.getText() != null && !numLetter.getText().equals("")) {
                            oblig.setStatus("تم إرسال رسالة");
                            oblig.updateObligatoire();
                            try {
                                Letter l = new Letter(oblig.getId_bon(), numLetter.getText(), dateLetter.getValue().toString(), new Service().getService(result.getService(), num_bon), oblig.getObligatoireId(), 0);
                                if (status.equals("تم إرسال رسالة")) l.updateLetter();
                                else l.insert();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            Stage stage = (Stage) btnDone.getScene().getWindow();
                            stage.close();
                        } else {
                            System.out.println(dateLetter.getValue());
                            System.out.println(numLetter.getText());
                            JOptionPane op = new JOptionPane();
                            op.showMessageDialog(null, "عليك ملئ جميع الخانات");
                        }
                    } else {
                        if (dateLetter1.getValue() != null && numLetter1.getText() != null && !numLetter1.getText().equals("") &&
                                dateLetter2.getValue() != null && numLetter2.getText() != null && !numLetter2.getText().equals("") &&
                                dateLetter3.getValue() != null && numLetter3.getText() != null && !numLetter3.getText().equals("")) {
                            oblig.setStatus("تم إرسال رسالة");
                            oblig.updateObligatoire();
                            try {
                                if (status.equals("تم إرسال رسالة")) {
                                    Letter l1 = oblig.getLetter().get(0);
                                    Letter l2 = oblig.getLetter().get(0);
                                    Letter l3 = oblig.getLetter().get(0);
                                    l1.setNumLetter(numLetter1.getText());
                                    l1.setNumLetter(dateLetter1.getValue().toString());
                                    l2.setNumLetter(numLetter2.getText());
                                    l2.setNumLetter(dateLetter2.getValue().toString());
                                    l3.setNumLetter(numLetter3.getText());
                                    l3.setNumLetter(dateLetter3.getValue().toString());

                                } else {
                                    Letter l1 = new Letter(oblig.getId_bon(), numLetter.getText(), dateLetter.getValue().toString(), new Service().getService(result.getService(), num_bon) + " : محضر تكليف", oblig.getObligatoireId(), 0);
                                    Letter l2 = new Letter(oblig.getId_bon(), numLetter.getText(), dateLetter.getValue().toString(), new Service().getService(result.getService(), num_bon) + " : محضر تبليغ التكليف", oblig.getObligatoireId(), 0);
                                    Letter l3 = new Letter(oblig.getId_bon(), numLetter.getText(), dateLetter.getValue().toString(), new Service().getService(result.getService(), num_bon) + " : محضر تبليغ السند", oblig.getObligatoireId(), 0);
                                    l1.insert();
                                    l2.insert();
                                    l3.insert();
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            Stage stage = (Stage) btnDone.getScene().getWindow();
                            stage.close();
                        } else {
                            JOptionPane op = new JOptionPane();
                            op.showMessageDialog(null, "عليك ملئ جميع الخانات");
                        }
                    }
                }
                if (b.getText().equals("معلق")) {
                    if (datePubCommune.getValue() != null && datePubTribunal.getValue() != null) {
                        oblig.setStatus("تم التعليق");
                        publish pub = oblig.getPublish();
                        ((publish) pub).setDateFinCommune(datePubCommune.getValue().toString());
                        ((publish) pub).setDateFineTribunal(datePubTribunal.getValue().toString());
                        pub.update();
                        oblig.updateObligatoire();
                        Stage stage = (Stage) btnDone.getScene().getWindow();
                        stage.close();
                    } else {
                        JOptionPane op = new JOptionPane();
                        op.showMessageDialog(null, "عليك ملئ جميع الخانات");
                    }
                }
        }
    }


    private void showSelectedPane(Pane pane) {
        pane.toFront();
        pane.setVisible(true);
    }
    public void setStatus(String num_bon,String status,boolean Execution,int obligIndex){
        this.num_bon=num_bon;
        this.Execution=Execution;
        this.status=status;
    for (Node n:hboxStatus.getChildren()){
        if (n instanceof Button){
            if (((Button) n).getText().equals(status)) {
                applyPressedStyle((Button) n);
                ActionEvent a = new ActionEvent(((Button) n),null);
                handleClicks(a);
                b=((Button) n);
            }
            if (status.equals("تم التعليق") || status.equals("تعليق (غير مبلغ)")) {
                applyPressedStyle(btnPublished);
                ActionEvent a = new ActionEvent(btnPublished,null);
                handleClicks(a);
                b=btnPublished;
            }
        }
    }
        result = new EditBonSearch(num_bon);
    if ((result.getService().equals("bon_apercus")) || (result.getService().equals("bon_apercu_parorders")) || (result.getService().equals("bon_associations")))
    {
        System.out.println("i'm here dude");
        btnConvoqued.setDisable(true);
        btnLetter.setDisable(true);
    }
    else {
        oblig = result.getObligatoireList().get(obligIndex);
        if (oblig.getStatus().equals("تم التبليغ")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(oblig.getDate(), formatter);
            dateDone.setValue(localDate);
        }
        if (oblig.getStatus().equals("تم إشعاره(ا)")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(oblig.getDate(), formatter);
            dateConvoqued.setValue(localDate);
        }
        if (oblig.getStatus().equals("تم إرسال رسالة")) {
            if (oblig.getLetter().size() == 1) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String date = oblig.getLetter().get(0).getDateLetter();
                LocalDate localDate = LocalDate.parse(date, formatter);
                dateLetter.setValue(localDate);
                numLetter.setText(oblig.getLetter().get(0).getNumLetter());
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String date = oblig.getLetter().get(0).getDateLetter();
                LocalDate localDate = LocalDate.parse(date, formatter);
                dateLetter1.setValue(localDate);
                numLetter1.setText(oblig.getLetter().get(0).getNumLetter());
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                date = oblig.getLetter().get(1).getDateLetter();
                localDate = LocalDate.parse(date, formatter);
                dateLetter2.setValue(localDate);
                numLetter2.setText(oblig.getLetter().get(1).getNumLetter());
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                date = oblig.getLetter().get(2).getDateLetter();
                localDate = LocalDate.parse(date, formatter);
                dateLetter3.setValue(localDate);
                numLetter3.setText(oblig.getLetter().get(2).getNumLetter());
            }
        }
    }
    }

    public void clearContainer(ObservableList<Node> n){
        for (Node c:n) {
            c.setVisible(false);
        }
    }
    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource()==btnDone) {
            clearContainer(stackpane.getChildren());
            applyPressedStyle(btnDone);
            showSelectedPane(pnlDone);
            b=btnDone;
        }
        if (actionEvent.getSource()==btnPublished) {
            if (!status.equals("تم التعليق") && !status.equals("تعليق (غير مبلغ)"))
            {
                JOptionPane.showMessageDialog(null,"عليك إجراء التعليق أولا");
                return;
            }
            clearContainer(stackpane.getChildren());
            applyPressedStyle(btnPublished);
            showSelectedPane(pnlPublished);
            b=btnPublished;
        }
        if (actionEvent.getSource()==btnConvoqued) {
        clearContainer(stackpane.getChildren());
        applyPressedStyle(btnConvoqued);
        showSelectedPane(pnlConvoqued);
        b=btnConvoqued;
        }
        if (actionEvent.getSource()==bntNotDone) {
        clearContainer(stackpane.getChildren());
        applyPressedStyle(bntNotDone);
        b=bntNotDone;
        }
        if (actionEvent.getSource()==btnLetter) {
        if (Execution) {
            clearContainer(stackpane.getChildren());
            applyPressedStyle(btnLetter);
            showSelectedPane(pnlLetterExe);
        }
        else {
        clearContainer(stackpane.getChildren());
        applyPressedStyle(btnLetter);
        showSelectedPane(pnlLetter);
        }
        b=btnLetter;
    }
    }
    }
