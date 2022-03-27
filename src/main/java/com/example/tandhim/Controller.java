package com.example.tandhim;

import com.example.tandhim.Models.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {

    CommissionController ComCtrl = null;
    CitationController CitationCtrl = null;
    MandatController MandatCtrl = null;
    RqstController RqstCtrl = null;
    JugementController JugementCtrl = null;
    ExcuseController ExcuseCtrl = null;
    OrdersController OrderCtrl = null;
    FormeJudiciereController FormeJudiciereCtrl = null;
    ActeController ActeCtrl = null;
    boolean searchResultExist;
    String bonType = null;
    int hov = 0;
    @FXML
    private VBox pnItems = null;
    @FXML
    private VBox vboxFormePrincipale;
    @FXML
    private ComboBox Type, demList, obligList, comCommission, comType, EditComObligList;
    @FXML
    private TextArea typeArea, statusArea;
    @FXML
    private TableView<SearchBon> searchTable;
    @FXML
    private TableView<BonStats.bonStatsRow> bonTable1;
    @FXML
    private TableView<BonStats.bonStatsRow> bonTable2;
    @FXML
    private TableView<BonStats.bonStatsExeRow> bonTable3;
    @FXML
    private TableColumn<SearchBon, String> colNumBon, colNumAffaire, colDemandeur, colObligatoire, colType, colDate, colStatus, colFolder;
    @FXML
    private TableColumn<BonStats.bonStatsRow, String> bonTable1_colNumBon, bonTable1_colDemandeur, bonTable1_colObligatoire, bonTable1_colType, bonTable1_colDate, bonTable1_colStatus;
    @FXML
    private TableColumn<BonStats.bonStatsRow, String> colNumBon1, colDemandeur1, colObligatoire1, colType1, colDate1, colStatus1;
    @FXML
    private TableColumn<BonStats.bonStatsExeRow, String> colNumBonExe, colCommissionExe, colObligatoireExe, colTypeExe, colCreatedAtExe, colStatusExe, colDateExe;
    @FXML
    private Button btnNotif,btnEnableEdit;
    @FXML
    private StackPane stpnlFormeExe, stpnlStatsBon,StackPaneStats;
    @FXML
    private HBox hboxOblig;
    @FXML
    private HBox hboxOblig2;
    @FXML
    private DatePicker datePickerFrom, datePickerTo, dateCitation, dateReport, dateReport2;

    @FXML
    private Button btnAdd, btnDem, btnOblig, btnExcuse, btnEditBon, editBtnCreatePV, editBtnPrintPV, editBtnSearch, btnCreatePV, btnFormeJudiciere, btnFormeNonJudiciere,btnMandat,btnRqst;
    @FXML
    private Button btnStatsBons2;
    @FXML
    private Button btnStatsBons1;
    @FXML
    private Button btnStatsBons;
    @FXML
    private Button btnStatsFinance;
    @FXML
    private Pane pnlStatsFinance;
    @FXML
    private Button btnStatsExe;
    @FXML
    private TextField demandeur, nomDem, adrDem, nomOblig, adrOblig, comNomCommission, num_bon, taxe_fixe, taxe_supp, prix, numCitation, editNumBon;
    @FXML
    private TextField obligatoire,editBonStatus;
    @FXML
    private TextField numBon;

    @FXML
    private Button btnEdit;
    @FXML
    private Button btnCitation;
    @FXML
    private Button btnJugement;
    @FXML
    private Button btnJugement1;
    @FXML
    private Button btnFormeExecutif;
    @FXML
    private Button btnInspection, btnOrder, btnAssociation, btnJard, btnInspectionOrder;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnStats;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnExit;
    @FXML
    private PieChart pieChartCount;
    @FXML
    private PieChart pieChartPrice;

    @FXML
    private Pane pnlStatsBons2;
    @FXML
    private Pane pnlStatsBons1,pnlStatsExe;
    @FXML
    private Pane pnlNotif;
    @FXML
    private Pane pnlStats;

    @FXML
    private Pane pnlSearch;

    @FXML
    private Pane pnlAdd, pnlEdit, pnlSettings;

    @FXML
    private Label labelStats, bonText, lbSommeExpected, lbSommeBon;
    @FXML
    private Pane pnlAdd2;
    @FXML
    private Pane pnlStatsBon;

    /** Dark/Light Mode RadioButtons */
    @FXML
    private RadioButton darkMode, lightMode;

    /** Create new user TextField */
    @FXML
    private TextField firstName, lastName, address, phone, username, password;

    /** Home StackPane */
    @FXML
    private StackPane stackPane;

    /** Right menu */
    @FXML
    private VBox rightMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pnlNotif.setVisible(true);
        btnNotif.getStyleClass().clear();
        btnNotif.getStyleClass().add("button1-pressed");
        pnlAdd.setVisible(false);
        pnlAdd2.setVisible(false);
        pnlSearch.setVisible(false);
        pnlStats.setVisible(false);
        pnlStatsFinance.setVisible(false);
        pnlStatsBon.setVisible(false);
        pnlStatsFinance.setVisible(false);
        pnlStatsBons2.setVisible(false);
        pnlStatsBons1.setVisible(false);
        pnlEdit.setVisible(false);
        BDConnection.findMySqlServer();
        Connection bd = BDConnection.getConnection();
        colDate.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
        colDemandeur.setCellValueFactory(cellData -> cellData.getValue().getDemandeurProperty());
        colObligatoire.setCellValueFactory(cellData -> cellData.getValue().getObligatoireProperty());
        colFolder.setCellValueFactory(cellData -> cellData.getValue().getDate_finProperty());
        colNumBon.setCellValueFactory(cellData -> cellData.getValue().getNum_bonProperty());
        colNumAffaire.setCellValueFactory(cellData -> cellData.getValue().getNum_caseProperty());
        colStatus.setCellValueFactory(cellData -> cellData.getValue().getStatusProperty());
        colType.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        bonTable1_colNumBon.setCellValueFactory(cellData -> cellData.getValue().getNum_bonProperty());
        bonTable1_colDate.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
        bonTable1_colDemandeur.setCellValueFactory(cellData -> cellData.getValue().getDemandeurProperty());
        bonTable1_colObligatoire.setCellValueFactory(cellData -> cellData.getValue().getObligatoireProperty());
        bonTable1_colStatus.setCellValueFactory(cellData -> cellData.getValue().getStatusProperty());
        bonTable1_colType.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        colNumBon1.setCellValueFactory(cellData -> cellData.getValue().getNum_bonProperty());
        colDate1.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
        colDemandeur1.setCellValueFactory(cellData -> cellData.getValue().getDemandeurProperty());
        colObligatoire1.setCellValueFactory(cellData -> cellData.getValue().getObligatoireProperty());
        colStatus1.setCellValueFactory(cellData -> cellData.getValue().getStatusProperty());
        colType1.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        colNumBonExe.setCellValueFactory(cellData -> cellData.getValue().getNum_bonProperty());
        colCommissionExe.setCellValueFactory(cellData -> cellData.getValue().getCommissionProperty());
        colObligatoireExe.setCellValueFactory(cellData -> cellData.getValue().getObligatoireProperty());
        colTypeExe.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        colCreatedAtExe.setCellValueFactory(cellData -> cellData.getValue().getCreatedAtProperty());
        colStatusExe.setCellValueFactory(cellData -> cellData.getValue().getStatusProperty());
        colDateExe.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
        colDateExe.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
        ArrayList<SearchNotification> notificationListe = new ArrayList<SearchNotification>();
        Statement st;
        ResultSet rs;
        String query = SearchNotification.SearchQuery();
        try {
            st = bd.createStatement();
            rs = st.executeQuery(query);

            SearchNotification notification;
            while (rs.next()) {
                String procedure = "";
                if (rs.getString("type_service").equals("تكليف بالوفاء")) {
                    procedure = "تنفيذ / عدم امتثال";
                } else {
                    procedure = "تنفيذ / عدم امتثال";
                }
                notification = new SearchNotification(rs.getString("num_bon"), rs.getString("type_service"), rs.getString("nom"), rs.getString("type"), procedure);
                notificationListe.add(notification);
            }
            for (int i = 0, n = notificationListe.size(); i < n; i++) {
                final int j = i;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Item.fxml"));
                Parent p = (Parent) loader.load();
                NotifCtrl controller1 = loader.getController();
                controller1.initData(notificationListe.get(i).getNum_bon(), notificationListe.get(i).getType_service(), notificationListe.get(i).getObligatoire(), notificationListe.get(i).getType(), notificationListe.get(i).getProcedure());

                pnItems.getChildren().add(p);
            }
            bd.close();
        } catch (SQLException ex) {
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Type.getItems().addAll("الكل", "تبليغ جلسة", "تكليف بالوفاء", "الأوامر", "الأحكام/ القرارات", "الاعذارات", "وصل جمعية", "وصل معاينة", "وصل معاينة بأمر", "أخرى");
        Type.getSelectionModel().select("الكل");
        //comChambre.getItems().addAll("القسم المدني","قسم الجنح" ,"قسم المخالفات","القسم الاستعجالي" ,"قسم شؤون الأسرة" ,"قسم الأحداث","القسم الاجتماعي","القسم العقاري" ,"القسم البحري" ,"القسم التجاري");
        //comCommission.getItems().addAll("محكمة","مجلس قضاء" ,"المحكمة الإدارية");

    }

    public void Search(ActionEvent e) {
        try {
            ObservableList<SearchBon> bons = FXCollections.observableArrayList();
            System.out.println("search");
            Connection bd = BDConnection.getConnection();
            String query = "", type = "";
            if (Type.getValue().toString().equals("تبليغ جلسة")) {
                type = "تبليغ جلسة";
                query = SearchBon.QuerySearch(demandeur.getText().toString(), obligatoire.getText().toString(), numBon.getText().toString(), "bon_seances", "num_seance", type);
            } else if (Type.getValue().toString().equals("تكليف بالوفاء")) {
                type = "تكليف بالوفاء";
                /*TODO A REVOIR*/

                query = SearchBon.QuerySearch(demandeur.getText().toString(), obligatoire.getText().toString(), numBon.getText().toString(), "bon_provisions", "num_indice", type);
                if (query.contains("WHERE")) {
                    query += " AND num_bon IN (SELECT id_provision FROM notification_fidelité)";
                } else {
                    query += " WHERE num_bon IN (SELECT id_provision FROM notification_fidelité)";
                }

            } else if (Type.getValue().toString().equals("الأحكام/ القرارات")) {
                Service s = new Service();
                type = "حكم/قرار";
                query = SearchBon.QuerySearch(demandeur.getText().toString(), obligatoire.getText().toString(), numBon.getText().toString(), "bon_provisions", "num_indice", type);
                if (query.contains("WHERE")) {
                    query += " AND num_bon NOT IN (SELECT id_provision FROM notification_fidelité)";
                } else {
                    query += " WHERE num_bon NOT IN (SELECT id_provision FROM notification_fidelité)";
                }
            } else if (Type.getValue().toString().equals("الأوامر")) {
                type = "الأوامر";
                query = SearchBon.QuerySearch(demandeur.getText().toString(), obligatoire.getText().toString(), numBon.getText().toString(), "bon_orders", "num_order", type);
            } else if (Type.getValue().toString().equals("الاعذارات")) {
                type = "الاعذارات";
                query = SearchBon.QuerySearch(demandeur.getText().toString(), obligatoire.getText().toString(), numBon.getText().toString(), "bon_excuses", "num_bon", type);
            } else if (Type.getValue().toString().equals("وصل جمعية")) {
                type = "وصل جمعية";
                query = SearchBon.QuerySearch(demandeur.getText().toString(), obligatoire.getText().toString(), numBon.getText().toString(), "bon_associations", "num_bon", type);
            } else if (Type.getValue().toString().equals("وصل معاينة")) {
                type = "وصل معاينة";
                query = SearchBon.QuerySearch(demandeur.getText().toString(), numBon.getText().toString(), "bon_apercus", "num_bon", type);
            } else if (Type.getValue().toString().equals("وصل معاينة بأمر")) {
                type = "وصل معاينة بأمر";
                query = SearchBon.QuerySearch(demandeur.getText().toString(), numBon.getText().toString(), "bon_apercu_parorders", "num_order", type);
            } else if (Type.getValue().toString().equals("أخرى")) {
                type = "أخرى";
                query = SearchBon.QuerySearch(demandeur.getText().toString(), numBon.getText().toString(), "bon_autres", "num_bon", type);
            } else {
                query = SearchBon.QuerySearch(demandeur.getText().toString(), obligatoire.getText().toString(), numBon.getText().toString(), "bon_seances", "num_seance", "تبليغ جلسة") + " \n"
                        + "UNION " + SearchBon.QuerySearch(demandeur.getText().toString(), obligatoire.getText().toString(), numBon.getText().toString(), "bon_orders", "num_order", "الأوامر") + " \n"
                        + "UNION " + SearchBon.QuerySearch(demandeur.getText().toString(), obligatoire.getText().toString(), numBon.getText().toString(), "bon_excuses", "num_bon", "الاعذارات") + " \n"
                        + "UNION " + SearchBon.QuerySearch(demandeur.getText().toString(), numBon.getText().toString(), "bon_associations", "num_bon", "وصل جمعية") + " \n"
                        + "UNION " + SearchBon.QuerySearch(demandeur.getText().toString(), numBon.getText().toString(), "bon_apercus", "num_bon", "وصل معاينة") + " \n"
                        + "UNION " + SearchBon.QuerySearch(demandeur.getText().toString(), numBon.getText().toString(), "bon_apercu_parorders", "num_order", "وصل معاينة بأمر") + " \n"
                        + "UNION " + SearchBon.QuerySearch(demandeur.getText().toString(), obligatoire.getText().toString(), numBon.getText().toString(), "bon_autres", "num_bon", "أخرى") + " \n";
                String q = "";
                Service s = new Service();
                type = "حكم/قرار";
                q = SearchBon.QuerySearch(demandeur.getText().toString(), obligatoire.getText().toString(), numBon.getText().toString(), "bon_provisions", "num_indice", type);
                if (q.contains("WHERE")) {
                    q += " AND num_bon NOT IN (SELECT id_provision FROM notification_fidelité)";
                } else {
                    q += " WHERE num_bon NOT IN (SELECT id_provision FROM notification_fidelité)";
                }
                query += "UNION " + q + " \n";
                type = "تكليف بالوفاء";
                q = SearchBon.QuerySearch(demandeur.getText().toString(), obligatoire.getText().toString(), numBon.getText().toString(), "bon_provisions", "num_indice", type);
                if (q.contains("WHERE")) {
                    q += " AND num_bon IN (SELECT id_provision FROM notification_fidelité)";
                } else {
                    q += " WHERE num_bon IN (SELECT id_provision FROM notification_fidelité)";
                }
                query += "UNION " + q;
            }
            Statement st;
            ResultSet rs;
            st = bd.createStatement();
            rs = st.executeQuery(query);
            SearchBon client;
            while (rs.next()) {
                client = new SearchBon(rs.getString("num_bon"), rs.getString("num_case"), rs.getString("status"), rs.getDate("created_at").toString(), rs.getString("type"), rs.getString("date_fin"), rs.getString("nom_demandeur"), rs.getString("nom_obligatoire"));
                //client = new SearchBon(rs.getString("num_bon"), rs.getString("num_case"), rs.getString("status"), rs.getDate("created_at").toString(), rs.getString("type"),rs.getString("date_fin"));
                bons.add(client);
            }
            System.out.println(searchTable);
            searchTable.setItems(bons);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    identif id = new identif();

    public void addBonInterface(ActionEvent e) throws IOException {
        num_bon.setText(id.getidentif());
        num_bon.setEditable(false);
        bonType = ((Button) e.getSource()).getText();
        vboxFormePrincipale.getChildren().clear();
        if (e.getSource() == btnCitation) {
            bonText.setText(btnCitation.getText());
            pnlAdd2.toFront();
            pnlAdd2.setVisible(true);
            hboxOblig.setVisible(true);
            hboxOblig2.setVisible(true);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("citation.fxml"));
            Parent p = (Parent) loader.load();
            CitationCtrl = loader.getController();
            vboxFormePrincipale.getChildren().add(p);
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("commission.fxml"));
            Parent p1 = (Parent) loader1.load();
            vboxFormePrincipale.getChildren().add(p1);
            ComCtrl = loader1.getController();
        }
        if (e.getSource() == btnMandat) {
            bonText.setText(btnMandat.getText());
            pnlAdd2.toFront();
            pnlAdd2.setVisible(true);
            hboxOblig.setVisible(true);
            hboxOblig2.setVisible(true);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mandat.fxml"));
            Parent p = (Parent) loader.load();
            MandatCtrl = loader.getController();
            vboxFormePrincipale.getChildren().add(p);
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("commission.fxml"));
            Parent p1 = (Parent) loader1.load();
            vboxFormePrincipale.getChildren().add(p1);
            ComCtrl = loader1.getController();
        }if (e.getSource() == btnRqst) {
            bonText.setText(btnRqst.getText());
            pnlAdd2.toFront();
            pnlAdd2.setVisible(true);
            hboxOblig.setVisible(true);
            hboxOblig2.setVisible(true);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("request.fxml"));
            Parent p = (Parent) loader.load();
            RqstCtrl = loader.getController();
            vboxFormePrincipale.getChildren().add(p);
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("commission.fxml"));
            Parent p1 = (Parent) loader1.load();
            vboxFormePrincipale.getChildren().add(p1);
            ComCtrl = loader1.getController();
        }
        if (e.getSource() == btnFormeJudiciere) {
            bonText.setText(btnFormeExecutif.getText() + " " + btnFormeJudiciere.getText());
            bonType = bonText.getText();
            System.out.println(bonText.getText());
            pnlAdd2.toFront();
            pnlAdd2.setVisible(true);
            hboxOblig.setVisible(true);
            hboxOblig2.setVisible(true);
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("formeExe.fxml"));
            Parent p = (Parent) loader1.load();
            FormeJudiciereCtrl = loader1.getController();
            FormeJudiciereCtrl.setParentCtrl(this);
            vboxFormePrincipale.getChildren().add(p);
        }
        if (e.getSource() == btnFormeNonJudiciere) {
            bonText.setText(btnFormeExecutif.getText() + " " + btnFormeNonJudiciere.getText());
            bonType = bonText.getText();
            System.out.println(bonText.getText());
            pnlAdd2.toFront();
            pnlAdd2.setVisible(true);
            hboxOblig.setVisible(true);
            hboxOblig2.setVisible(true);
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("acte.fxml"));
            Parent p = (Parent) loader1.load();
            ActeCtrl = loader1.getController();
            vboxFormePrincipale.getChildren().add(p);
        }
        if (e.getSource() == btnOrder) {
            bonText.setText(btnOrder.getText());
            pnlAdd2.toFront();
            pnlAdd2.setVisible(true);
            hboxOblig.setVisible(true);
            hboxOblig2.setVisible(true);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("orders.fxml"));
            Parent p = (Parent) loader.load();
            OrderCtrl = loader.getController();
            vboxFormePrincipale.getChildren().add(p);
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("commission.fxml"));
            Parent p1 = (Parent) loader1.load();
            ComCtrl = loader1.getController();
            vboxFormePrincipale.getChildren().add(p1);
        }
        if (e.getSource() == btnJugement) {
            bonText.setText(btnJugement.getText());
            pnlAdd2.toFront();
            pnlAdd2.setVisible(true);
            hboxOblig.setVisible(true);
            hboxOblig2.setVisible(true);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("jugement.fxml"));
            Parent p = (Parent) loader.load();
            JugementCtrl = loader.getController();
            JugementCtrl.setComTypeForme("حكم");
            vboxFormePrincipale.getChildren().add(p);
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("commission.fxml"));
            Parent p1 = (Parent) loader1.load();
            ComCtrl = loader1.getController();
            vboxFormePrincipale.getChildren().add(p1);
        }
        if (e.getSource() == btnJugement1) {
            bonText.setText(btnJugement1.getText());
            pnlAdd2.toFront();
            pnlAdd2.setVisible(true);
            hboxOblig.setVisible(true);
            hboxOblig2.setVisible(true);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("jugement.fxml"));
            Parent p = (Parent) loader.load();
            JugementCtrl = loader.getController();
            JugementCtrl.setComTypeForme("قرار");
            vboxFormePrincipale.getChildren().add(p);
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("commission.fxml"));
            Parent p1 = (Parent) loader1.load();
            ComCtrl = loader1.getController();
            vboxFormePrincipale.getChildren().add(p1);
        }
        if (e.getSource() == btnExcuse) {
            bonText.setText(btnExcuse.getText());
            pnlAdd2.toFront();
            pnlAdd2.setVisible(true);
            hboxOblig.setVisible(true);
            hboxOblig2.setVisible(true);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("excuses.fxml"));
            Parent p = (Parent) loader.load();
            ExcuseCtrl = loader.getController();
            vboxFormePrincipale.getChildren().add(p);

        }
        if (e.getSource() == btnInspection) {
            bonText.setText(btnInspection.getText());
            pnlAdd2.toFront();
            pnlAdd2.setVisible(true);
            hboxOblig.setVisible(false);
            hboxOblig2.setVisible(false);
        }
        if (e.getSource() == btnInspectionOrder) {
            bonText.setText(btnInspectionOrder.getText());
            pnlAdd2.toFront();
            pnlAdd2.setVisible(true);
            hboxOblig.setVisible(false);
            hboxOblig2.setVisible(false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("orders.fxml"));
            Parent p = (Parent) loader.load();
            OrderCtrl = loader.getController();
            vboxFormePrincipale.getChildren().add(p);
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("commission.fxml"));
            Parent p1 = (Parent) loader1.load();
            ComCtrl = loader1.getController();
            vboxFormePrincipale.getChildren().add(p1);
        }
        if (e.getSource() == btnAssociation) {
            bonText.setText(btnAssociation.getText());
            pnlAdd2.toFront();
            pnlAdd2.setVisible(true);
            hboxOblig.setVisible(false);
            hboxOblig2.setVisible(false);
        }
        if (e.getSource() == btnJard) {
            bonText.setText(btnJard.getText());
            pnlAdd2.toFront();
            pnlAdd2.setVisible(true);
            hboxOblig.setVisible(false);
            hboxOblig2.setVisible(false);
        }

    }

    public void typeFormeExeItemChanged1(String s) throws IOException {
        if (vboxFormePrincipale.getChildren().size() == 3) {
            vboxFormePrincipale.getChildren().remove(1);
            vboxFormePrincipale.getChildren().remove(1);
        }
        if (s.equals("حكم") || s.equals("قرار")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("jugement.fxml"));
            Parent p = (Parent) loader.load();
            JugementCtrl = loader.getController();
            JugementCtrl.vboxJugement.getChildren().remove(JugementCtrl.hboxTypeJugement);
            vboxFormePrincipale.getChildren().add(p);
            System.out.println(vboxFormePrincipale.getChildren().size());
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("commission.fxml"));
            Parent p1 = (Parent) loader1.load();
            ComCtrl = loader1.getController();
            vboxFormePrincipale.getChildren().add(p1);
            System.out.println(vboxFormePrincipale.getChildren().size());
        }
        if (s.equals("أمر")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("orders.fxml"));
            Parent p = (Parent) loader.load();
            OrderCtrl = loader.getController();
            OrderCtrl.vboxOrders.getChildren().remove(OrderCtrl.hboxTypeOrder);
            vboxFormePrincipale.getChildren().add(p);
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("commission.fxml"));
            Parent p1 = (Parent) loader1.load();
            ComCtrl = loader1.getController();
            vboxFormePrincipale.getChildren().add(p1);

        }

    }

    public void hover() {
        btnStatsBons.setVisible(false);
        btnStatsBons1.setVisible(true);
        btnStatsBons2.setVisible(true);
    }

    public void hover1() {
        btnFormeExecutif.setVisible(false);
        btnFormeJudiciere.setVisible(true);
        btnFormeNonJudiciere.setVisible(true);
    }

    public void exitHover() {
        btnStatsBons.setVisible(true);
        btnStatsBons1.setVisible(false);
        btnStatsBons2.setVisible(false);
    }

    public void exitHover1() {
        btnFormeExecutif.setVisible(true);
        btnFormeJudiciere.setVisible(false);
        btnFormeNonJudiciere.setVisible(false);
    }

    public void addToComboBoxList(ActionEvent e) {
        String nomAdr;
        if (e.getSource() == btnDem) {
            nomAdr = nomDem.getText() + " العنوان: " + adrDem.getText();
            demList.getItems().add(nomAdr);
        } else {
            nomAdr = nomOblig.getText() + " العنوان: " + adrOblig.getText();
            obligList.getItems().add(nomAdr);
        }
    }

    public void addBon() {
        if (Numeric(taxe_fixe.getText()) <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("style.css").toExternalForm());
            dialogPane.getStyleClass().add("dialog-pane");
            alert.setTitle("خطأ في الإدخال");
            alert.setContentText(
                    "الأتعاب غير مدرجة بطريقة صحيحة ");
            alert.showAndWait();
            return;
        }
        if ((Numeric(taxe_supp.getText()) < 0) && (Numeric(this.prix.getText()) < 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("style.css").toExternalForm());
            dialogPane.getStyleClass().add("dialog-pane");
            alert.setTitle("خطأ في الإدخال");
            alert.setContentText(
                    "الأتعاب غير مدرجة بطريقة صحيحة ");
            alert.showAndWait();
            return;
        }
        int somme = Numeric(taxe_fixe.getText()) + Numeric(taxe_supp.getText());
        int prix = Numeric(this.prix.getText());
        if (demList.getItems().size() < 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("style.css").toExternalForm());
            dialogPane.getStyleClass().add("dialog-pane");
            alert.setTitle("خطأ في الإدخال");
            alert.setContentText(
                    "قائمة الطالبين فارغة");
            alert.showAndWait();
            return;
        }
        if (bonType.equals("تكليف بالحضور لجلسة")) {
            if (obligList.getItems().size() < 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                        getClass().getResource("style.css").toExternalForm());
                dialogPane.getStyleClass().add("dialog-pane");
                alert.setTitle("خطأ في الإدخال");
                alert.setContentText(
                        "قائمة المطلوبين فارغة");
                alert.showAndWait();
                return;
            }

            BonSeances bon = new BonSeances(CitationCtrl.getNumCitation(), ComCtrl.getComType(), ComCtrl.getComCommission() + " : " + ComCtrl.getComNomCommission(), CitationCtrl.getDateCitation(), CitationCtrl.getDateReport(), CitationCtrl.getDateReport2(), num_bon.getText(), prix, somme);
            if (bon.validate()) {
                System.out.println(bon.validate());
                bon.insert();
                id.increment();
                for (int i = 0; i < demList.getItems().size(); i++) {
                    String[] a = demList.getItems().get(i).toString().split(" العنوان: ");
                    Demandeur d = new Demandeur(a[0], a[1], num_bon.getText());
                    d.insert();
                    btnCreatePV.setDisable(false);

                }

                for (int i = 0; i < obligList.getItems().size(); i++) {
                    String[] a = obligList.getItems().get(i).toString().split(" العنوان: ");
                    Obligatoire d = new Obligatoire(a[0], a[1], num_bon.getText(), null, null, 0);
                    d.insert();
                }
            }
        }
        if (bonType.equals("تبليغ مذكرة")) {
            if (obligList.getItems().size() < 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                        getClass().getResource("style.css").toExternalForm());
                dialogPane.getStyleClass().add("dialog-pane");
                alert.setTitle("خطأ في الإدخال");
                alert.setContentText(
                        "قائمة المطلوبين فارغة");
                alert.showAndWait();
                return;
            }

            BonMandat bon = new BonMandat(MandatCtrl.getNumMandat(), MandatCtrl.getTypeMandat(), MandatCtrl.getService(),ComCtrl.getComCommission() + " : " + ComCtrl.getComNomCommission(), MandatCtrl.getDateMandat(), num_bon.getText(), prix, somme);
            if (bon.validate()) {
                System.out.println(bon.validate());
                bon.insert();
                id.increment();
                for (int i = 0; i < demList.getItems().size(); i++) {
                    String[] a = demList.getItems().get(i).toString().split(" العنوان: ");
                    Demandeur d = new Demandeur(a[0], a[1], num_bon.getText());
                    d.insert();
                    btnCreatePV.setDisable(false);

                }

                for (int i = 0; i < obligList.getItems().size(); i++) {
                    String[] a = obligList.getItems().get(i).toString().split(" العنوان: ");
                    Obligatoire d = new Obligatoire(a[0], a[1], num_bon.getText(), null, null, 0);
                    d.insert();
                }
            }
        }
        if (bonType.equals("تبليغ عريضة")) {
            if (obligList.getItems().size() < 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                        getClass().getResource("style.css").toExternalForm());
                dialogPane.getStyleClass().add("dialog-pane");
                alert.setTitle("خطأ في الإدخال");
                alert.setContentText(
                        "قائمة المطلوبين فارغة");
                alert.showAndWait();
                return;
            }

            BonRqst bon = new BonRqst(RqstCtrl.getNumRqst(), RqstCtrl.getTypeRqst(), ComCtrl.getComCommission() + " : " + ComCtrl.getComNomCommission(),RqstCtrl.getDateRqst(), num_bon.getText(), prix, somme);
            if (bon.validate()) {
                System.out.println(bon.validate());
                bon.insert();
                id.increment();
                for (int i = 0; i < demList.getItems().size(); i++) {
                    String[] a = demList.getItems().get(i).toString().split(" العنوان: ");
                    Demandeur d = new Demandeur(a[0], a[1], num_bon.getText());
                    d.insert();
                    btnCreatePV.setDisable(false);

                }

                for (int i = 0; i < obligList.getItems().size(); i++) {
                    String[] a = obligList.getItems().get(i).toString().split(" العنوان: ");
                    Obligatoire d = new Obligatoire(a[0], a[1], num_bon.getText(), null, null, 0);
                    d.insert();
                }
            }
        }
        if (bonType.equals("تبليغ حكم") || bonType.equals("تبليغ قرار")) {
            if (obligList.getItems().size() < 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                        getClass().getResource("style.css").toExternalForm());
                dialogPane.getStyleClass().add("dialog-pane");
                alert.setTitle("خطأ في الإدخال");
                alert.setContentText(
                        "قائمة المطلوبين فارغة");
                alert.showAndWait();
                return;
            }

            BonProvisions bon = new BonProvisions(JugementCtrl.getNumIndice(), JugementCtrl.getNumTable(), JugementCtrl.getDateForme(), ComCtrl.getComCommission() + " : " + ComCtrl.getComNomCommission(), JugementCtrl.getComTypeForme(), ComCtrl.getComType(), num_bon.getText(), prix, somme);
            if (bon.validate()) {
                System.out.println(bon.validate());
                bon.insert();
                id.increment();
                for (int i = 0; i < demList.getItems().size(); i++) {
                    String[] a = demList.getItems().get(i).toString().split(" العنوان: ");
                    Demandeur d = new Demandeur(a[0], a[1], num_bon.getText());
                    d.insert();
                }

                for (int i = 0; i < obligList.getItems().size(); i++) {
                    String[] a = obligList.getItems().get(i).toString().split(" العنوان: ");
                    Obligatoire d = new Obligatoire(a[0], a[1], num_bon.getText(), null, null, 0);
                    d.insert();
                }
            }
        }
        if (bonType.equals("تبليغ أمر")) {
            if (obligList.getItems().size() < 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                        getClass().getResource("style.css").toExternalForm());
                dialogPane.getStyleClass().add("dialog-pane");
                alert.setTitle("خطأ في الإدخال");
                alert.setContentText(
                        "قائمة المطلوبين فارغة");
                alert.showAndWait();
                return;
            }

            BonOrders bon = new BonOrders(OrderCtrl.getNumOrder(), OrderCtrl.getDateOrder(), ComCtrl.getComCommission() + " : " + ComCtrl.getComNomCommission(), OrderCtrl.getComTypeOrder(), num_bon.getText(), prix, somme);
            if (bon.validate()) {
                System.out.println(bon.validate());
                bon.insert();
                id.increment();
                for (int i = 0; i < demList.getItems().size(); i++) {
                    String[] a = demList.getItems().get(i).toString().split(" العنوان: ");
                    Demandeur d = new Demandeur(a[0], a[1], num_bon.getText());
                    d.insert();
                    btnCreatePV.setDisable(false);

                }

                for (int i = 0; i < obligList.getItems().size(); i++) {
                    String[] a = obligList.getItems().get(i).toString().split(" العنوان: ");
                    Obligatoire d = new Obligatoire(a[0], a[1], num_bon.getText(), null, null, 0);
                    d.insert();
                }
            }
        }
        if (bonType.equals("تبليغ إرسالية / طلب / إعذار")) {
            if (obligList.getItems().size() < 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                        getClass().getResource("style.css").toExternalForm());
                dialogPane.getStyleClass().add("dialog-pane");
                alert.setTitle("خطأ في الإدخال");
                alert.setContentText(
                        "قائمة المطلوبين فارغة");
                alert.showAndWait();
                return;
            }

            BonExcuses bon = new BonExcuses(ExcuseCtrl.getDateMarquage(), num_bon.getText(), ExcuseCtrl.getComTypeExcuse(), prix, somme);
            if (bon.validate()) {
                System.out.println(bon.validate());
                bon.insert();
                id.increment();
                for (int i = 0; i < demList.getItems().size(); i++) {
                    String[] a = demList.getItems().get(i).toString().split(" العنوان: ");
                    Demandeur d = new Demandeur(a[0], a[1], num_bon.getText());
                    d.insert();
                    btnCreatePV.setDisable(false);

                }

                for (int i = 0; i < obligList.getItems().size(); i++) {
                    String[] a = obligList.getItems().get(i).toString().split(" العنوان: ");
                    Obligatoire d = new Obligatoire(a[0], a[1], num_bon.getText(), null, null, 0);
                    d.insert();
                }
            }
        }
        if (bonType.equals("تكليف بالوفاء بموجب سند قضائي")) {
            System.out.println(FormeJudiciereCtrl.getType());
            System.out.println("sdmkfn");
            if (obligList.getItems().size() < 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                        getClass().getResource("style.css").toExternalForm());
                dialogPane.getStyleClass().add("dialog-pane");
                alert.setTitle("خطأ في الإدخال");
                alert.setContentText(
                        "قائمة المطلوبين فارغة");
                alert.showAndWait();
                return;
            }
            if (FormeJudiciereCtrl.getType().equals("أمر")) {
                BonOrders bon = new BonOrders(OrderCtrl.getNumOrder(), OrderCtrl.getDateOrder(), ComCtrl.getComCommission() + " : " + ComCtrl.getComNomCommission(), OrderCtrl.getComTypeOrder(), num_bon.getText(), prix, somme);
                if (bon.validate()) {
                    System.out.println(bon.validate());
                    bon.insert();
                    bon.addNotificationFidelité(FormeJudiciereCtrl.getNumForme(), FormeJudiciereCtrl.getDateFomeExe());
                    id.increment();

                }
            } else {
                BonProvisions bon = new BonProvisions(JugementCtrl.getNumIndice(), JugementCtrl.getNumTable(), JugementCtrl.getDateForme(), ComCtrl.getComCommission() + " : " + ComCtrl.getComNomCommission(), JugementCtrl.getComTypeForme(), ComCtrl.getComType(), num_bon.getText(), prix, somme);
                if (bon.validate()) {
                    System.out.println(bon.validate());
                    bon.insert();
                    bon.addNotificationFidelité(FormeJudiciereCtrl.getNumForme(), FormeJudiciereCtrl.getDateFomeExe());
                    id.increment();
                }
            }
            for (int i = 0; i < demList.getItems().size(); i++) {
                String[] a = demList.getItems().get(i).toString().split(" العنوان: ");
                Demandeur d = new Demandeur(a[0], a[1], num_bon.getText());
                d.insert();
                btnCreatePV.setDisable(false);

            }

            for (int i = 0; i < obligList.getItems().size(); i++) {
                String[] a = obligList.getItems().get(i).toString().split(" العنوان: ");
                Obligatoire d = new Obligatoire(a[0], a[1], num_bon.getText(), null, null, 0);
                d.insert();
            }

        }
        if (bonType.equals("تكليف بالوفاء بموجب سند غير قضائي")) {
            if (obligList.getItems().size() < 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                        getClass().getResource("style.css").toExternalForm());
                dialogPane.getStyleClass().add("dialog-pane");
                alert.setTitle("خطأ في الإدخال");
                alert.setContentText(
                        "قائمة المطلوبين فارغة");
                alert.showAndWait();
                return;
            }
            BonActe bon = new BonActe(num_bon.getText(), ActeCtrl.getNumActe(), ActeCtrl.getNomNotaire(), ActeCtrl.getTypeActe(), ActeCtrl.getDateActe(), prix, somme);
            if (bon.validate()) {
                System.out.println(bon.validate());
                bon.insert();
                id.increment();
                for (int i = 0; i < demList.getItems().size(); i++) {
                    String[] a = demList.getItems().get(i).toString().split(" العنوان: ");
                    Demandeur d = new Demandeur(a[0], a[1], num_bon.getText());
                    d.insert();
                    btnCreatePV.setDisable(false);

                }

                for (int i = 0; i < obligList.getItems().size(); i++) {
                    String[] a = obligList.getItems().get(i).toString().split(" العنوان: ");
                    Obligatoire d = new Obligatoire(a[0], a[1], num_bon.getText(), null, null, 0);
                    d.insert();
                }

            }

        }

    }

    public static int Numeric(String strNum) {
        strNum.replaceAll("دج", "");
        strNum.replaceAll(" ", "");
        if (strNum == null) {
            return 0;
        }
        try {
            int d = Integer.parseInt(strNum);
            return d;
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    public void datePickerAction(ActionEvent e) throws SQLException {
        if ((datePickerFrom.getValue() != null) && (datePickerTo.getValue() != null)) {
            BonStats b = new BonStats();
            ObservableList<BonStats.bonStatsRow> bonStatList = b.remp(datePickerFrom.getValue().toString(), datePickerTo.getValue().toString(), "id");
            ObservableList<BonStats.bonStatsRow> bonStatList1 = b.remp1(datePickerFrom.getValue().toString(), datePickerTo.getValue().toString(), "id");
            ObservableList<BonStats.bonStatsExeRow> bonStatExeList = b.rempStatsExe(datePickerFrom.getValue().toString(), datePickerTo.getValue().toString(), "id");
            System.out.println(bonStatList.get(0).getDate());
            bonTable1.setItems(bonStatList);
            bonTable3.setItems(bonStatExeList);
            System.out.println("ksqdjbfjlqsdbf"+bonStatList1.get(0).getNum_bon());
            bonTable2.setItems(bonStatList1);
            ArrayList CountPrice = b.remp2(datePickerFrom.getValue().toString(), datePickerTo.getValue().toString());
            int paye = (int) CountPrice.get(0), nonPaye = (int) CountPrice.get(1), verse = (int) CountPrice.get(2), sommeTotale = (int) CountPrice.get(4), sommeExpected = (int) CountPrice.get(3);
            //lbSommeBon.setText("إجمالي القضايا = "+(paye+nonPaye+verse));
            //lbSommeExpected.setText("إجمالي القضايا = "+(sommeTotale+sommeExpected));
            ObservableList<PieChart.Data> pieChartData
                    = FXCollections.observableArrayList(
                            new PieChart.Data("المسددة كاملة = " + paye, Math.ceil(paye * 100 / (paye + nonPaye + verse))),
                            new PieChart.Data("الغير مسددة = " + nonPaye, Math.ceil(nonPaye * 100 / (paye + nonPaye + verse))),
                            new PieChart.Data("المسددة جزئيا = " + verse, Math.ceil(verse * 100 / (paye + nonPaye + verse))));
            ObservableList<PieChart.Data> pieChartData1
                    = FXCollections.observableArrayList(
                            new PieChart.Data("المداخيل المقبوضة = " + sommeTotale, Math.ceil(sommeTotale * 100 / sommeExpected)),
                            new PieChart.Data("الغير مقبوضة = " + (sommeExpected - sommeTotale), Math.ceil((sommeExpected - sommeTotale) * 100 / sommeExpected)));
            pieChartCount.setData(pieChartData);
            pieChartPrice.setData(pieChartData1);
        }
    }

    public void EditSearch() {
        //clearText(pnlEdit.getChildren());

        ClearEditInterface();
        EditBonSearch result = new EditBonSearch(editNumBon.getText());
        if (!result.getService().equals("")){
            searchResultExist=true;
            if (result.getObligatoireList() != null) {
                for (Obligatoire obl : result.getObligatoireList()) {
                    String s = obl.getNom() + " العنوان : " + obl.getAddr();
                    EditComObligList.getItems().add(s);
                    EditComObligList.getSelectionModel().selectFirst();
                }
            } else {
                for (Demandeur dem : result.getDemandeurList()) {
                    String s = dem.getNom() + " العنوان : " + dem.getAddr();
                    EditComObligList.getItems().add(s);
                    EditComObligList.getSelectionModel().selectFirst();
                }
                EditComObligList.setPromptText("قائمة الطالبين");
            }
            if (result.getService().equals("bon_seances")) {
                System.out.println(result.getBonData());
                BonSeances bon = result.getBonData();
                System.out.println(bon);
                if (bon.getDate_report().equals("")) {
                    typeArea.setText("تكليف بالحضور لجلسة يوم " + bon.getDate_seance() + " رقم: " + bon.getNum_seance() + " والتي ستعقد أمام: " + bon.getCommission());
                } else if (bon.getDate_report2().equals("")) {
                    typeArea.setText("تكليف بالحضور لجلسة يوم " + bon.getDate_seance() + " والمؤجلة ليوم: " + bon.getDate_report() + " رقم: " + bon.getNum_seance() + " والتي ستعقد أمام: " + bon.getCommission());
                } else {
                    typeArea.setText("تكليف بالحضور لجلسة يوم " + bon.getDate_seance() + " والمؤجلة ليوم: " + bon.getDate_report() + " والمؤجلة ليوم: " + bon.getDate_report2() + " رقم: " + bon.getNum_seance() + " والتي ستعقد أمام: " + bon.getCommission());
                }
            }
            if (result.getService().equals("bon_orders")) {
                if (result.isNotificationFidelité()) {
                    BonOrders bon = result.getBonOrdersData();
                    NotificationFidelite notif = result.getNotificationFidelité();
                    typeArea.setText("تكليف بالوفاء بموجب : " + bon.getType() + " رقم: " + bon.getNum_order() + " الصادر عن: " + bon.getCommission() + " بتاريخ: " + bon.getDate_order() + " والممهور بالصيغة التنفيذية رقم : " + notif.getNum() + " الصادرة بتاريخ : " + notif.getDate());
                } else {
                    System.out.println(result.getBonData());
                    BonOrders bon = result.getBonOrdersData();
                    System.out.println(bon);
                    typeArea.setText("تبليغ " + bon.getType() + " رقم: " + bon.getNum_order() + " الصادر عن: " + bon.getCommission() + " بتاريخ: " + bon.getDate_order());
                }
            }
            if (result.getService().equals("bon_provisions")) {
                if (result.isNotificationFidelité()) {
                    BonProvisions bon = result.getBonProvisionsData();
                    NotificationFidelite notif = result.getNotificationFidelité();
                    System.out.println(notif);
                    typeArea.setText("تكليف بالوفاء بموجب : " + bon.getType() + " رقم الفهرس: " + bon.getNum_indice() + " رقم الجدول: " + bon.getNum_table() + " الصادر عن: " + bon.getCommission() + " بتاريخ: " + bon.getDate() + " والممهور بالصيغة التنفيذية رقم : " + notif.getNum() + " الصادرة بتاريخ : " + notif.getDate());
                } else {
                    System.out.println(result.getBonData());
                    BonProvisions bon = result.getBonProvisionsData();
                    System.out.println(bon);
                    typeArea.setText("تبليغ " + bon.getType() + " رقم الفهرس: " + bon.getNum_indice() + " رقم الجدول: " + bon.getNum_table() + " الصادر عن: " + bon.getCommission() + " بتاريخ: " + bon.getDate());
                }
            }
            if (result.getService().equals("bon_excuses")) {
                BonExcuses bon = result.getBonExcusesData();
                typeArea.setText("تبليغ" + bon.getType() + " المؤشر بتاريخ: " + bon.getDate_marquage());
            }
            if (result.getService().equals("bon_mandat")) {
                BonMandat bon = result.getBonMandatData();
                typeArea.setText("تبليغ مذكرة " + bon.getType() + " رقم : " + bon.getNum_mandat() + " المسجلة بتاريخ: " + bon.getDate() + " لدى: " + bon.getService());
            }
            if ((!result.getService().equals("bon_apercus")) && (!result.getService().equals("bon_apercu_parorders")) && (!result.getService().equals("bon_associationd"))) {
                Obligatoire obl = result.getObligatoireList().get(EditComObligList.getSelectionModel().getSelectedIndex());
                editBonStatus.setText(obl.getStatus());
                statusArea.setText(obl.OblStatus());

            }
        }
    }
    public void okPressed (KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            EditSearch();
        }
    }
    public void clearText(ObservableList<Node> c){
        for (Node n:c){
            if (n instanceof TextField) {
                ((TextField) n).clear();
            }
            if (n instanceof ComboBox) {
                ((ComboBox) n).getItems().removeAll();
            }
            if (n instanceof TextArea) {
                ((TextArea) n).clear();
            }
            if (n instanceof DatePicker) {
                ((DatePicker) n).getEditor().clear();
            }
        }
    }

    public void UpdateStatus() {
        EditBonSearch result = new EditBonSearch(editNumBon.getText());
        Obligatoire obl = result.getObligatoireList().get(EditComObligList.getSelectionModel().getSelectedIndex());
        editBonStatus.setText(obl.getStatus());
        statusArea.setText(obl.OblStatus());
    }
    public void ChangeStatus() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditDialogue.fxml"));
        try {
            Parent p = (Parent) loader.load();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setContent(p);
            alert.setTitle("خطأ في الإدخال");
            alert.setContentText(
                    "اسم الهيئة خاطئ");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void UpdateBon() {

    }

    public void ClearAddInterface() {
        demList.getItems().clear();
        obligList.getItems().clear();
        prix.clear();
        taxe_fixe.clear();
        taxe_supp.clear();
        nomDem.clear();
        nomOblig.clear();
    }

    public void FillEditBon() {
        EditBonSearch result = new EditBonSearch(editNumBon.getText());
        ActionEvent e = null;
        if (result.getService().equals("bon_seances"))
        {
            ActionEvent actionEvent = new ActionEvent(btnCitation, null);
            e=actionEvent;
            try {
                addBonInterface(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            fillBonSceance(result);
        }
        if (result.getService().equals("bon_provisions") && result.isNotificationFidelité())
        {
            e = new ActionEvent(btnFormeJudiciere, null);
            try {
                addBonInterface(e);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            fillBonProvisionExe(result);

        }



    }
    public void fillBonSceance(EditBonSearch result) {
        ClearAddInterface();
        BonSeances bon =result.getBonData();
        num_bon.setText(bon.getNum_bon());
        CitationCtrl.setNumCitation(bon.getNum_seance());
        obligList.getItems().addAll(FXCollections.observableArrayList(result.getObligatoireList()));
        demList.getItems().addAll(FXCollections.observableArrayList(result.getDemandeurList()));
        ComCtrl.setCom_NomCommission(bon.getCommission());
        ComCtrl.setComType(bon.getType());
        CitationCtrl.setDateCitation(bon.getDate_seance());
        CitationCtrl.setDateReport(bon.getDate_report());
        CitationCtrl.setDateReport2(bon.getDate_report2());
        prix.setText("2000");
        taxe_supp.setText("0");
        taxe_fixe.setText("3000");
    }
    public void fillBonProvisionExe(EditBonSearch result) {
        ClearAddInterface();
        BonProvisions bon =result.getBonProvisionsData();
        num_bon.setText(bon.getNum_bon());
        System.out.println("there is notif here"+result.getNotificationFidelité().getNum());
        System.out.println("there is ctrl here"+FormeJudiciereCtrl);
        FormeJudiciereCtrl.setNumFomeExe(result.getNotificationFidelité().getNum());
        FormeJudiciereCtrl.setDateFomeExe(result.getNotificationFidelité().getDate());
        FormeJudiciereCtrl.setTypeFormeExe(bon.getType());
        JugementCtrl.setNumTable(bon.getNum_table());
        JugementCtrl.setDateForme(bon.getDate());
        JugementCtrl.setNumIndice(bon.getNum_indice());
        JugementCtrl.vboxJugement.getChildren().remove(JugementCtrl.hboxTypeJugement);
        ComCtrl.setCom_NomCommission(bon.getCommission());
        ComCtrl.setComType(bon.getSpec());
        obligList.getItems().addAll(FXCollections.observableArrayList(result.getObligatoireList()));
        demList.getItems().addAll(FXCollections.observableArrayList(result.getDemandeurList()));
        ComCtrl.setCom_NomCommission(bon.getCommission());
        ComCtrl.setComType(bon.getType());
       //CitationCtrl.setDateCitation(bon.getDate_seance());
        //CitationCtrl.setDateReport(bon.getDate_report());
        //CitationCtrl.setDateReport2(bon.getDate_report2());
        prix.setText("2000");
        taxe_supp.setText("0");
        taxe_fixe.setText("3000");
    }
    public void PrintBon() {
        int somme = Numeric(taxe_fixe.getText()) + Numeric(taxe_supp.getText());
        int prix = Numeric(this.prix.getText());
        BonSeances bon = new BonSeances(CitationCtrl.getNumCitation(), ComCtrl.getComType(), ComCtrl.getComCommission() + " : " + ComCtrl.getComNomCommission(), CitationCtrl.getDateCitation(), CitationCtrl.getDateReport(), CitationCtrl.getDateReport2(), num_bon.getText(), prix, somme);
        bon.PrintBon();

    }
    public void EnableEdit(){
    if (searchResultExist) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditDialogue.fxml"));
        try {
            Parent p = (Parent) loader.load();
            EditDialogueController ctrl = loader.getController();
            System.out.println("num="+editNumBon.getText());
            if (typeArea.getText().contains("بالوفاء"))
            ctrl.setStatus(editNumBon.getText(), editBonStatus.getText(), true,EditComObligList.getSelectionModel().getSelectedIndex());
            else ctrl.setStatus(editNumBon.getText(), editBonStatus.getText(), false,EditComObligList.getSelectionModel().getSelectedIndex());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("css/style.css").toExternalForm());
            dialogPane.getStyleClass().add("dialog-pane");
            dialogPane.setContent(p);
            alert.setTitle("خطأ في الإدخال");
            alert.setContentText(
                    "اسم الهيئة خاطئ");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
    public void ClearEditInterface() {
        searchResultExist=false;
        EditComObligList.getItems().removeAll(EditComObligList.getItems());
        statusArea.clear();
        typeArea.clear();
        editBonStatus.clear();
        //editNumBon.clear();
        editBtnCreatePV.setDisable(true);
        //ُEditComObligList.setPromptText("قائمة المطلوبين");
    }

    public void PrintPV() {
        PrintPV newPV = new PrintPV();
        String oblAdr[] = obligList.getSelectionModel().getSelectedItem().toString().split(" العنوان: ");
        newPV.PrintPVSeance(demList.getItems(), oblAdr[0], oblAdr[1], "افتتاحية", "////", CitationCtrl.getNumCitation(), num_bon.getText(), CitationCtrl.getDateCitation(), CitationCtrl.getDateReport(), CitationCtrl.getDateReport2(), ComCtrl.getComCommission() + " : " + ComCtrl.getComNomCommission(), ComCtrl.getComType(), "03", "01", "09:00", obligList.getSelectionModel().getSelectedIndex() + 1, 1);
    }

    /** Add new account to the database */
    public void addAccount(ActionEvent actionEvent) {
        /* TODO: */
    }

    private void applyPressedStyle(Button button) {
        button.getStyleClass().clear();
        button.getStyleClass().add("button1-pressed");
    }

    private void showSelectedPane(Pane pane) {
        pane.toFront();
        pane.setVisible(true);
    }
    public void clearContainer(ObservableList<Node> c) {
        for (Node b:c){
            b.setVisible(false);
        }
    }

    public void handleClicks(ActionEvent actionEvent) {
        /** hide all panes */
        stackPane.getChildren().forEach((pane) -> pane.setVisible(false));

        /** add {@button1} style to all {@Button} inside {@rightMenu}*/
        rightMenu.getChildren().forEach((child) -> {
            if (child instanceof Button) {
                child.getStyleClass().clear();
                child.getStyleClass().add("button1");
            }
        });

        bonType = null;

        if (actionEvent.getSource() == btnNotif) {
            applyPressedStyle(btnNotif);
            showSelectedPane(pnlNotif);
        }
        if (actionEvent.getSource() == btnSearch) {
            applyPressedStyle(btnSearch);
            showSelectedPane(pnlSearch);
        }
        if (actionEvent.getSource() == btnAdd) {
            applyPressedStyle(btnAdd);
            showSelectedPane(pnlAdd);
            ClearAddInterface();
        }
        if (actionEvent.getSource() == btnStats) {
            applyPressedStyle(btnStats);
            showSelectedPane(pnlStats);
        }
        if (actionEvent.getSource() == btnStatsBons1) {
            applyPressedStyle(btnStats);
            clearContainer(StackPaneStats.getChildren());
            pnlStats.setVisible(true);
            pnlStatsBon.toFront();
            pnlStatsBon.setVisible(true);
            pnlStatsBons1.toFront();
            labelStats.setText("إحصائيات التبليغ - سجل العقود -");
            pnlStatsBons1.setVisible(true);
        }
        if (actionEvent.getSource() == btnStatsBons2) {
            applyPressedStyle(btnStats);
            clearContainer(StackPaneStats.getChildren());
            pnlStats.setVisible(true);
            pnlStatsBon.toFront();
            pnlStatsBon.setVisible(true);
            pnlStatsBons2.toFront();
            labelStats.setText("إحصائيات التبليغ - سجل الوصولات-");
            pnlStatsBons2.setVisible(true);
        }
        if (actionEvent.getSource() == btnStatsFinance) {
            applyPressedStyle(btnStats);
            clearContainer(StackPaneStats.getChildren());
            pnlStats.setVisible(true);
            pnlStatsFinance.toFront();
            pnlStatsFinance.setVisible(true);
            labelStats.setText("إحصائيات المالية");

        }
        if (actionEvent.getSource() == btnStatsExe) {
            applyPressedStyle(btnStats);
            clearContainer(StackPaneStats.getChildren());
            pnlStatsExe.setVisible(true);
            pnlStats.setVisible(true);
        }
        if (actionEvent.getSource() == btnEdit) {
            ClearEditInterface();
            searchResultExist = false;
            applyPressedStyle(btnEdit);
            showSelectedPane(pnlEdit);
        }

        /** Settings */
        if (actionEvent.getSource() == btnSettings) {
            applyPressedStyle(btnSettings);
            showSelectedPane(pnlSettings);
        }

        /** Close Application */
        if (actionEvent.getSource() == btnExit) {
            Stage stage = (Stage) btnExit.getScene().getWindow();
            stage.close();
        }
    }

    public void Close() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

}
