// 
// Decompiled by Procyon v0.5.36
// 

package com.example.tandhim;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.example.tandhim.Models.BonProvisions;
import com.example.tandhim.Models.Demandeur;
import com.example.tandhim.Models.Impression.DOCXModels;
import com.example.tandhim.Models.Impression.Print2Word;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.event.ActionEvent;
import com.example.tandhim.Models.PriceArabicSpell;
import javafx.stage.Stage;
import com.example.tandhim.Models.EditBonSearch;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;

public class PrintPvController implements Initializable
{
    @FXML
    private Label typePv;
    @FXML
    private Label labelOblig;
    @FXML
    private TextField avocat;
    @FXML
    private CheckBox checkTypePhysique;
    @FXML
    private CheckBox checkTypeMorale;
    @FXML
    private StackPane stackpane;
    @FXML
    private VBox vboxCitation;
    @FXML
    private TextField typeReq;
    @FXML
    private CheckBox checkCitationPv1;
    @FXML
    private VBox vboxJugement;
    @FXML
    private ComboBox<String> comTypeJug;
    @FXML
    private HBox hboxTypeJugement;
    @FXML
    private TextField typeJug;
    @FXML
    private CheckBox arcticle329_336;
    @FXML
    private CheckBox arcticle336;
    @FXML
    private CheckBox arcticle349_354;
    @FXML
    private CheckBox arcticle950;
    @FXML
    private CheckBox arcticle329_349_354;
    @FXML
    private VBox vboxOrder;
    @FXML
    private ComboBox<String> comTypeOrder;
    @FXML
    private HBox hboxTypeOrder;
    @FXML
    private TextField typeOrder;
    @FXML
    private HBox hboxDecisionArticle1;
    @FXML
    private CheckBox sansDelai;
    @FXML
    private CheckBox avecDelai,checkAttached;
    @FXML
    private VBox vboxDecision;
    @FXML
    private ComboBox<String> comTypeDecision;
    @FXML
    private HBox hboxTypeJugement1;
    @FXML
    private TextField typeDecision;
    @FXML
    private HBox hboxDecisionArticle;
    @FXML
    private CheckBox article354;
    @FXML
    private CheckBox article329_355_354;
    @FXML
    private VBox vboxExcuse;
    @FXML
    private HBox hboxTypeJugement11;
    @FXML
    private TextField langueExcuse;
    @FXML
    private TextField droitPropo;
    @FXML
    private TextField totale;
    @FXML
    private CheckBox article3541;
    @FXML
    private HBox hboxTypeJugement111;
    @FXML
    private TextField nbrPages;
    @FXML
    private VBox vboxConstat;
    @FXML
    private TextArea contentArea;
    @FXML
    private VBox vboxObligation;
    @FXML
    private TextArea contentExeArea;
    @FXML
    private CheckBox checkContent;
    @FXML
    private CheckBox checkMontant;
    @FXML
    private TextField montant;
    @FXML
    private TextField charge;
    @FXML
    private TextArea ObligationArea;
    @FXML
    private HBox hboxStatus1;
    @FXML
    private HBox hboxArticleJug;
    @FXML
    private HBox hboxOrderArticle;
    @FXML
    private Button btnSave;
    public String numBon;
    
    public void bonData( String numBon,  String typePv,  String oblig) {
        this.numBon = numBon;
        this.typePv.setText(typePv);
        this.labelOblig.setText(typePv);
        this.labelOblig.setText(oblig);
        if (typePv.equals("محضر تكليف بالوفاء بموجب سند قضائي")) {
            this.vboxObligation.setVisible(true);
        }
        if (typePv.equals("محضر تكليف بالحضور لجلسة")) {
            this.vboxCitation.setVisible(true);
        }
        if (typePv.equals("محضر تبليغ أمر")) {
            this.vboxOrder.setVisible(true);
        }
        if (typePv.equals("محضر تبليغ حكم")) {
            this.vboxJugement.setVisible(true);
        }
        if (typePv.equals("محضر تبليغ قرار")) {
            this.vboxDecision.setVisible(true);
        }
        if (typePv.equals("محضر تبليغ إرسالية / طلب / إعذار")) {
            this.vboxExcuse.setVisible(true);
        }
        if (typePv.equals("\u0645\u062d\u0636\u0631 \u0645\u0639\u0627\u064a\u0646\u0629") || typePv.equals("\u0645\u062d\u0636\u0631 \u0645\u0639\u0627\u064a\u0646\u0629 \u0628\u0623\u0645\u0631") || typePv.equals("\u0645\u062d\u0636\u0631 \u062d\u0636\u0648\u0631 \u062c\u0645\u0639\u064a\u0629 \u0639\u0627\u0645\u0629")) {
            this.vboxConstat.setVisible(true);
            this.checkTypeMorale.setDisable(true);
            this.checkTypePhysique.setDisable(true);
        }
    }
    
    @FXML
    void wordView() {

        final EditBonSearch result = new EditBonSearch(this.numBon);
        if (typePv.getText().equals("محضر تكليف بالوفاء بموجب سند قضائي")) {
            this.vboxObligation.setVisible(true);
        }
        if (typePv.getText().equals("محضر تكليف بالحضور لجلسة")) {
            this.vboxCitation.setVisible(true);
        }
        if (typePv.getText().equals("محضر تبليغ أمر")) {
            this.vboxOrder.setVisible(true);
        }
        if (typePv.getText().equals("محضر تبليغ حكم")) {
            System.out.println("yeah im here");
            String oblig =labelOblig.getText().replace("المطلوب ضده :","");
            String [] obl = oblig.split(" العنوان: ");
            HashMap<String, String> marginInformation = new HashMap<>() {{
                put("@huiss", "بن ثامر دحمان");
                put("@wilaya", "البليدة");
                put("@adrHuiss", "شارع 11 ديسمبر 1960 البليدة )مقابل مجلس قضاء البليدة(");
            }};
            BonProvisions bon = result.getBonProvisionsData();
            ArrayList<Demandeur> dem = result.getDemandeurList();
            String demandeur = "";
            int i=1;
            for (Demandeur d:dem) {
                demandeur +=i+"/ "+ d.getNom()+" العنوان: "+d.getAddr()+" ";
            }
            String finalDemandeur = demandeur;
            HashMap<String, String> modelInformation = new HashMap<>() {{
                put("@demandeur", finalDemandeur);
                put("@commission", bon.getCommission());
                put("@spec", bon.getSpec());
                put("@year", "اثنان وعشرون");
                put("@type", comTypeJug.getSelectionModel().getSelectedItem());
                put("@table", bon.getNum_table());
                put("@indice", bon.getNum_indice());
                put("@obligatoire", obl[0]);
                put("@address", obl[1]);
                put("@num_bon", numBon);
                put("@date", bon.getDate());
            }};
            Print2Word print2Word = null;
            try {
                print2Word = new Print2Word(DOCXModels.jugement_1, marginInformation, modelInformation, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            print2Word.replaceParameters();
        }
        if (typePv.getText().equals("محضر تبليغ قرار")) {
            this.vboxDecision.setVisible(true);
        }
        if (typePv.getText().equals("محضر تبليغ إرسالية / طلب / إعذار")) {
            this.vboxExcuse.setVisible(true);
        }
        ((Stage)this.btnSave.getScene().getWindow()).close();
    }
    
    @FXML
    void calculePropo() {
        if (this.montant.getText() != null) {
            this.droitPropo.setText(PriceArabicSpell.droitProportionelle(this.montant.getText()));
        }
        else {
            this.droitPropo.setText("");
        }
        Double somme = 0.0;
        somme = Double.parseDouble(PriceArabicSpell.Numeric(this.montant.getText())) + Double.parseDouble(PriceArabicSpell.Numeric(this.charge.getText())) + Double.parseDouble(PriceArabicSpell.Numeric(this.droitPropo.getText()));
        this.totale.setText(String.valueOf(somme));
    }
    
    @FXML
    void changeType(final ActionEvent event) {
        if (event.getSource() == this.comTypeJug) {
            if (((String)this.comTypeJug.getSelectionModel().getSelectedItem()).equals("\u0635\u0646\u0641 \u0622\u062e\u0631")) {
                this.hboxArticleJug.setVisible(true);
                this.typeJug.setVisible(true);
            }
            else {
                this.hboxArticleJug.setVisible(false);
                this.typeJug.setVisible(false);
            }
        }
        if (event.getSource() == this.comTypeDecision) {
            if (((String)this.comTypeDecision.getSelectionModel().getSelectedItem()).equals("\u0635\u0646\u0641 \u0622\u062e\u0631")) {
                this.hboxDecisionArticle.setVisible(true);
                this.typeDecision.setVisible(true);
            }
            else {
                this.hboxDecisionArticle.setVisible(false);
                this.typeDecision.setVisible(false);
            }
        }
        if (event.getSource() == this.comTypeOrder) {
            if (((String)this.comTypeOrder.getSelectionModel().getSelectedItem()).equals("\u0635\u0646\u0641 \u0622\u062e\u0631")) {
                this.hboxOrderArticle.setVisible(true);
                this.typeOrder.setVisible(true);
            }
            else {
                this.hboxOrderArticle.setVisible(false);
                this.typeOrder.setVisible(false);
            }
        }
    }
    
    @FXML
    void checkSelection(final ActionEvent event) {
        if (event.getSource() == this.checkMontant) {
            if (this.checkMontant.isSelected()) {
                this.montant.setDisable(false);
                this.charge.setDisable(false);
                this.droitPropo.setDisable(false);
                this.totale.setDisable(false);
            }
            else {
                this.montant.setDisable(true);
                this.charge.setDisable(true);
                this.droitPropo.setDisable(true);
                this.totale.setDisable(true);
            }
        }
        if (event.getSource() == this.checkContent) {
            if (this.checkContent.isSelected()) {
                this.ObligationArea.setDisable(false);
            }
            else {
                this.ObligationArea.setDisable(true);
            }
        }
        String personne = "";
        if (event.getSource() == this.checkTypeMorale) {
            if (this.checkContent.isSelected()) {
                personne += "-1";
            }
            else {
                this.ObligationArea.setDisable(true);
            }
        }
    }
    
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        this.comTypeJug.getItems().addAll("حكم حضوري", "حكم غيابي", "حكم حضوري إبتدائي نهائي", "حكم غيابي إبتدائي نهائي", "حكم غيابي نهائي", "حكم حضوري نهائي", "حكم إداري", "حكم حضوري اعتباري", "صنف آخر");
        this.comTypeDecision.getItems().addAll("قرار حضوري", "قرار غيابي","قرار إداري","صنف آخر");
        this.comTypeOrder.getItems().addAll("أمر إستعجالي","أمر بإثبات حالة" ,"أمر بحجز تنفيذي على منقول","أمر بحجز تنفيذي على عقار","أمر بحجز تنفيذي على ما للمدين لدى الغير","أمر باستبدال خبير","أمر بالزيارة المؤقتة","أمر باستجواب" ,"أمر أداء","صنف آخر");
        for (final Node n : this.stackpane.getChildren()) {
            n.setVisible(false);
        }
    }
}