package com.example.demo;

import java.io.FileWriter;
import java.io.IOException;

public class MyFIleWriter {
    public static void writeFile(int POINTS,int pointsInside, double estimatedArea){
        FileWriter writer = null;
        try {
            writer = new FileWriter("answers.txt",true);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
            writer.write( "Кол-во точек всего: " + POINTS + ". Кол-во точек в нужной площаде: " + pointsInside + ". Ответ: " + estimatedArea + " \n");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
            writer.flush();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
            writer.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
