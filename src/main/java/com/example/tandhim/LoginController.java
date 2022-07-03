/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import javax.imageio.ImageIO;
import javax.swing.*;

import static com.example.tandhim.Models.impression.getQRCode;

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
        try {
            generateQRCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void generateQRCode() throws IOException {
        String path = String.format("src/main/resources/com/example/tandhim/img/Qrcode.png");
        File outQRCode = new File(path);
        BufferedImage bufferedQRCode = getQRCode(BDConnection.getMySqlServerAddress(), 150, 150);
        ImageIO.write(bufferedQRCode, "png", outQRCode);
    }
    public void Login() throws IOException {
        if (userName.getText().equals("") || password.getText().equals("")){
            JOptionPane op = new JOptionPane();
            op.showMessageDialog(null, "إسم المستخدم أو كلمة المرور خاطئة2222");
            return ;
        }
        BDConnection.findMySqlServer();
        Connection bd = BDConnection.getConnection();
        String query = "SELECT * FROM users WHERE username='" + userName.getText() + "' LIMIT 1";

        Statement st;
        ResultSet rs;
        try {

            st = bd.createStatement();
            rs = st.executeQuery(query);
            if (rs.next() && new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A,12).matches(password.getText(),rs.getString("password"))){

                FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("Home.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Controller controller2 = fxmlLoader.getController();
                controller2.setUserID(rs.getInt("id"));
                controller2.setUserType(rs.getString("type"));
                controller2.setUserName(rs.getString("nom")+" "+rs.getString("prenom"));
                String logQuery="CALL log_login("+ Controller.getUserID()+",' ');";

                PreparedStatement preparedStmt = bd.prepareStatement(logQuery);
                int idn = preparedStmt.executeUpdate();

                Stage stage = (Stage) btnLogin.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }else{
                JOptionPane op = new JOptionPane();
                op.showMessageDialog(null, "إسم المستخدم أو كلمة المرور خاطئة");
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }




    }
    public void okPressed (KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            try {
                Login();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
