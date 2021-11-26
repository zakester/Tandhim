package com.example.tandhim;

import com.example.tandhim.Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {

    CommissionController ComCtrl = null;
    CitationController CitationCtrl = null;
    JugementController JugementCtrl = null;
    ExcuseController ExcuseCtrl = null;
    OrdersController OrderCtrl = null;
    FormeJudiciereController FormeJudiciereCtrl = null;
    ActeController ActeCtrl = null;
    String bonType = null;
    int hov = 0;
    @FXML
    private VBox pnItems = null;
    @FXML
    private VBox vboxFormePrincipale;
    @FXML
    private ComboBox Type, demList, obligList, comCommission, comType, EditComObligList, comStatus;
    @FXML
    private TextArea typeArea, statusArea;
    @FXML
    private TableView<SearchBon> searchTable;
    @FXML
    private TableView<BonStats.bonStatsRow> bonTable1, bonTable2;
    @FXML
    private TableColumn<SearchBon, String> colNumBon, colNumAffaire, colDemandeur, colObligatoire, colType, colDate, colStatus, colFolder;
    @FXML
    private TableColumn<BonStats.bonStatsRow, String> bonTable1_colNumBon, bonTable1_colDemandeur, bonTable1_colObligatoire, bonTable1_colType, bonTable1_colDate, bonTable1_colStatus;
    @FXML
    private Button btnNotif,btnEnableEdit;
    @FXML
    private StackPane stpnlFormeExe, stpnlStatsBon;
    @FXML
    private HBox hboxOblig;
    @FXML
    private HBox hboxOblig2;
    @FXML
    private DatePicker datePickerFrom, datePickerTo, dateCitation, dateReport, dateReport2;

    @FXML
    private Button btnAdd, btnDem, btnOblig, btnExcuse, btnEditBon, editBtnCreatePV, editBtnPrintPV, editBtnSearch, btnCreatePV, btnFormeJudiciere, btnFormeNonJudiciere;
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
    private TextField obligatoire;
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
    private Pane pnlStatsBons1;
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
        demList.setCellFactory(lv
                -> new ListCell<String>() {
            // This is the node that will display the text and the cross. 
            // I chose a hyperlink, but you can change to button, image, etc. 
            private HBox graphic;

            // this is the constructor for the anonymous class.
            {
                Label label = new Label();
                // Bind the label text to the item property. If your ComboBox items are not Strings you should use a converter. 
                label.textProperty().bind(itemProperty());
                // Set max width to infinity so the cross is all the way to the right. 
                label.setMaxWidth(Double.POSITIVE_INFINITY);
                // We have to modify the hiding behavior of the ComboBox to allow clicking on the hyperlink, 
                // so we need to hide the ComboBox when the label is clicked (item selected). 

                Hyperlink cross = new Hyperlink("X");
                cross.setVisited(true); // So it is black, and not blue. 

                cross.setOnAction(event
                        -> {
                    System.out.println(".initialize()");
                    // Since the ListView reuses cells, we need to get the item first, before making changes.  
                    String item = getItem();
                    System.out.println("Clicked cross on " + item);
                    if (isSelected()) {
                        // Not entirely sure if this is needed. 
                        demList.getSelectionModel().select(null);
                    }
                    // Remove the item from A and add to B. You can add any additional logic in here. 
                    demList.getItems().remove(item);
                }
                );
                label.setOnMouseClicked(event -> demList.hide());
                // Arrange controls in a HBox, and set display to graphic only (the text is included in the graphic in this implementation). 
                graphic = new HBox(label, cross);
                graphic.setHgrow(label, Priority.ALWAYS);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(graphic);
                }
            }
        });
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
            System.out.println(bonStatList.get(0).getDate());
            bonTable1.setItems(bonStatList);
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
        EditBonSearch result = new EditBonSearch(editNumBon.getText());
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

            Obligatoire obl = result.getObligatoireList().get(EditComObligList.getSelectionModel().getSelectedIndex());
            comStatus.getItems().clear();
            comStatus.getItems().addAll("غير منجرة", "تم إرسال رسالة", "منجرة", "ملغاة");
            comStatus.getSelectionModel().select(obl.getStatus());
            statusArea.setText(obl.OblStatus());
        }
    }

    public void UpdateStatus() {
        EditBonSearch result = new EditBonSearch(editNumBon.getText());
        Obligatoire obl = result.getObligatoireList().get(EditComObligList.getSelectionModel().getSelectedIndex());
        comStatus.getItems().clear();
        comStatus.getItems().addAll("غير منجرة", "تم إرسال رسالة", "منجرة", "ملغاة");
        comStatus.getSelectionModel().select(obl.getStatus());
        statusArea.setText(obl.OblStatus());

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
        BonSeances bon = result.getBonData();
        ActionEvent e = new ActionEvent(btnCitation, null);
        try {
            addBonInterface(e);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        ClearAddInterface();
        num_bon.setText(bon.getNum_bon());
        CitationCtrl.setNumCitation(bon.getNum_seance());
        obligList.getItems().addAll(FXCollections.observableArrayList(result.getObligatoireList()));
        demList.getItems().addAll(FXCollections.observableArrayList(result.getDemandeurList()));
        ComCtrl.setCom_NomCommission(bon.getCommission());
        ComCtrl.setComType(bon.getType());
        CitationCtrl.setDateCitation(bon.getDate_seance());
        CitationCtrl.setDateReport(bon.getDate_report());
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
        btnEnableEdit.setDisable(false);
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

            pnlStats.setVisible(true);
            pnlStatsBon.toFront();
            pnlStatsBon.setVisible(true);
            pnlStatsBons1.toFront();
            labelStats.setText("إحصائيات التبليغ - سجل العقود -");
            pnlStatsBons1.setVisible(true);
        }
        if (actionEvent.getSource() == btnStatsBons2) {
            applyPressedStyle(btnStats);

            pnlStats.setVisible(true);
            pnlStatsBon.toFront();
            pnlStatsBon.setVisible(true);
            pnlStatsBons2.toFront();
            labelStats.setText("إحصائيات التبليغ - سجل الوصولات-");
            pnlStatsBons2.setVisible(true);
        }
        if (actionEvent.getSource() == btnStatsFinance) {
            applyPressedStyle(btnStats);

            pnlStats.setVisible(true);
            pnlStatsFinance.toFront();
            pnlStatsFinance.setVisible(true);
            labelStats.setText("إحصائيات المالية");

        }
        if (actionEvent.getSource() == btnStatsExe) {
            applyPressedStyle(btnStats);
            pnlStats.setVisible(true);
        }
        if (actionEvent.getSource() == btnEdit) {
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
