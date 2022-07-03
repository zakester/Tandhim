package com.example.tandhim;


import com.example.tandhim.Models.Impression.DOCXModels;
import com.example.tandhim.Models.Impression.Print2Word;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.util.HashMap;

public class Main extends Application {
    private double x, y;
    //
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);

        stage.initStyle(StageStyle.UNDECORATED);

        Parent root = scene.getRoot();

        //drag it here
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {

            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

        });

        stage.show();
    }

    public static void main(String[] args) throws IOException {

        HashMap<String, String> marginInformation = new HashMap<>() {{
           put("@huiss", "حمزة حاج عمار");
           put("@wilaya", "البليدة");
           put("@adrHuiss", "غير لتم");
        }};
        HashMap<String, String> modelInformation = new HashMap<>() {{
            put("@demandeur", "كريم رميد");
            put("@commission", "عن مجلس كاش عفسة");
            put("@table", "02");
            put("@indice", "02");
            put("@obligatoire", "مراد بن ثامر");
            put("@address", "كاش بلاصة");
            put("@num_bon", "18/21");
            put("@date", "2000-12-12");
        }};
        Print2Word print2Word = new Print2Word(DOCXModels.decision_1, marginInformation, modelInformation, 0);
        print2Word.replaceParameters();
        launch(args);
    }


}
