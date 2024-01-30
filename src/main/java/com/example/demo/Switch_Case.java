package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Switch_Case {
    public static void switchScene(Stage stage, String nameFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(nameFile));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Монарло");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
