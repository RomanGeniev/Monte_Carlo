package com.example.demo;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class MyFormatter {
    public static TextFormatter<String> addFormatter(){
        UnaryOperator<TextFormatter.Change> positiveIntegerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("([1-9][0-9]{0,4})?") || newText.isEmpty()) {
                return change;
            }
            return null;
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(positiveIntegerFilter);
        return textFormatter;
    }
}
