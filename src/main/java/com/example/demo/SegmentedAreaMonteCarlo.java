package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.UUID;

public class SegmentedAreaMonteCarlo {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back_button;

    @FXML
    private Button run_Programm;
    @FXML
    private TextField total_points;
    @FXML
    private Label answer;

    @FXML
    private Pane field;
    @FXML
    private Label coordinateInfo;

    @FXML
    private Canvas graph;

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final double RADIUS = 150;
    private static final double CENTER_X = 300;
    private static final double CENTER_Y = 300;
    private static final double DISTANCE = 150;
    @FXML
    private int POINTS;
    private int pointsInside = 0;
    private int attemptsCount = 0;
    private int allPoints = 0;
    private BarChart<String, Number> barChart;
    private XYChart.Series<String, Number> series;

    public SegmentedAreaMonteCarlo() throws IOException {
    }

    private void drawCircle(GraphicsContext gc) {
        gc.strokeOval(CENTER_X - RADIUS, CENTER_Y - RADIUS, 2 * RADIUS, 2 * RADIUS);
    }

    private void drawLine(GraphicsContext gc) {
        gc.strokeLine(0, CENTER_Y - DISTANCE, WIDTH, CENTER_Y - DISTANCE);  // горизонтальная линия
    }
    private boolean isInside(double x, double y) {
        double distanceFromCenter = Math.sqrt(Math.pow(x - CENTER_X, 2) + Math.pow(y - CENTER_Y, 2));
        return distanceFromCenter <= RADIUS;
    }

    private double calculateEstimatedArea() {
        double areaRatio = (double) pointsInside / allPoints;
        double totalArea = Math.PI * RADIUS * RADIUS;
        return totalArea * areaRatio;
    }
    private void addColumn(String string, float area){
            series.getData().add(new XYChart.Data<>(string + " - " + attemptsCount, area));
    }



    @FXML
    void initialize() throws IOException {




            double segmentArea = Math.pow(RADIUS, 2) * Math.acos((RADIUS - DISTANCE) / RADIUS) - (RADIUS - DISTANCE) * Math.sqrt(2 * RADIUS * DISTANCE - Math.pow(DISTANCE, 2));
            System.out.println("Площадь горизонтального сегмента: " + segmentArea);

        total_points.setTextFormatter(MyFormatter.addFormatter());

        Stage stage = new Stage();
        stage.setTitle("Анализ");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Анализ попыток моделирования");
        series = new XYChart.Series<>();
        series.setName("Количество точек");
        barChart.getData().add(series);
        VBox root = new VBox(barChart, run_Programm);
        Scene scene = new Scene(root, 800, 600);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

back_button.setOnAction(e -> {
    stage.close();
        try {
                Switch_Case.switchScene((Stage) back_button.getScene().getWindow(),"hello-view.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
GraphicsContext gc = graph.getGraphicsContext2D();
drawCircle(gc);
drawLine(gc);







run_Programm.setOnAction(e -> {
    attemptsCount++;
            Random rand = new Random();
            POINTS = Integer.parseInt(total_points.getText());
            allPoints += POINTS;
            for (int i = 0; i < POINTS; i++) {
                double x = CENTER_X - RADIUS + rand.nextDouble() * 2 * RADIUS;
                double y = CENTER_Y - RADIUS + rand.nextDouble() * 2 * RADIUS;
                gc.fillOval(x, y, 2, 2);
                if (isInside(x, y)) {
                    pointsInside++;
                    gc.setFill(javafx.scene.paint.Color.GREEN);
                } else {
                    gc.setFill(javafx.scene.paint.Color.BLUE);
                }
                gc.fillOval(x, y, 2, 2);
            }
            float estimatedArea = (float) calculateEstimatedArea();
            MyFIleWriter.writeFile(POINTS, pointsInside, estimatedArea);
            addColumn(String.valueOf(Integer.parseInt(total_points.getText())),estimatedArea);
            answer.setText("Площадь: " + estimatedArea);
            System.out.println("Площадь: " + estimatedArea);
    });


        }

    }




