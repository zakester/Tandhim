/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.example.tandhim.Models.BDConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    public TextField userName;
    @FXML
    public PasswordField password;
    @FXML
    public Button btnLogin;
    @FXML
    public Button bntForget;
    @FXML
    public Button bntCreate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void Login(ActionEvent e) throws IOException {
        Connection bd = BDConnection.getConnection();
        String query = "SELECT * FROM users WHERE username='" + userName.getText() + "' AND password='"+ password.getText() +"'";
        Statement st;
        ResultSet rs;
        try {

            st = bd.createStatement();
            rs = st.executeQuery(query);
            if (rs.next()){
                FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("Home.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }else{
                System.out.println("not know");
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }


        Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
        primaryStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    public void Close() {
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.close();
    }

    public void Forget(ActionEvent e) {

    }

    public void Create(ActionEvent e) {

    }
}
