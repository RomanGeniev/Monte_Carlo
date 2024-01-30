package com.example.demo;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;



public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Button_Start;

    @FXML
    private Label Label_FIO;

    @FXML
    private Button file_button;
    @FXML
    private Button aboutProgramm;

    @FXML
    private Label Label_Variant;

    private void showInfoDialog() {
        Alert infoDialog = new Alert(Alert.AlertType.INFORMATION);
        infoDialog.setTitle("Информация");
        infoDialog.setHeaderText("Версия приложения: 1.0\nФИО : Гениевский Роман Игоревич");
        infoDialog.showAndWait();
    }

    @FXML
    void initialize() throws IOException {

        aboutProgramm.setOnAction(event -> showInfoDialog());

        file_button.setOnAction(exex -> {
            File pdfFile = new File("C:\\Users\\genie\\OneDrive\\Рабочий стол\\Instrukcia1.pdf");
            if (pdfFile.exists()) {
                try {
                    if (Desktop.isDesktopSupported()) {
                        Desktop desktop = Desktop.getDesktop();
                        desktop.open(pdfFile);
                    } else {
                        System.out.println("Рабочий стол не поддерживается");
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else {
                System.out.println("Файл не найден");
            }
        });



        Button_Start.setOnAction(exp -> {
            try {
                Switch_Case.switchScene((Stage) Button_Start.getScene().getWindow(),"Cartesian Plane Graph.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });




}
    }


