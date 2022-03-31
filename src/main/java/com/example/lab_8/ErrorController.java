package com.example.lab_8;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class ErrorController {

    @FXML private Button ok;

    @FXML
    void initialize() {
        ok.setOnAction(event ->{
            Stage stage1 = (Stage) ok.getScene().getWindow();
            stage1.close();
        });
    }
    // метод, вызывающий окно об ошибке
    public void errorWindow(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("error.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

}
