package com.mascara.electronicstoremanage.utils;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.text.Normalizer;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 18/04/2024
 * Time      : 7:20 CH
 * Filename  : SlugUtils
 */
public class Utilities {
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final Pattern STRONG_PASSWORD = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$");


    private static Utilities instance = null;

    public static Utilities getInstance() {
        if (instance == null)
            instance = new Utilities();
        return instance;
    }

    private Utilities() {

    }

    public boolean checkStrongPassword(String password) {
        Matcher matcher = STRONG_PASSWORD.matcher(password);
        return matcher.matches();
    }

    public String toSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    public String removeTrailingZeros(double d) {
        return String.valueOf(d).replaceAll("[0]*$", "").replaceAll(".$", "");
    }

    public void clearAllTextField(Pane pane) {
        List<Node> textFieldList = paneNodes(pane);
        for (Node node : textFieldList) {
            if (node instanceof TextField) {
                ((TextField) node).setText("");
            }
            if (node instanceof TextArea) {
                ((TextArea) node).setText("");
            }
        }
    }

    public void unCheckedAllCheckBox(Pane pane) {
        List<Node> checkBoxList = paneNodes(pane);
        for (Node node : checkBoxList) {
            if (node instanceof CheckBox) {
                ((CheckBox) node).setSelected(false);
            }
        }
    }

    public void unCheckedAllCheckBox(TableView tableView) {
        ObservableList<TableColumn> columns = tableView.getColumns();
        for (Object row : tableView.getItems()) {
            for (TableColumn column : columns) {
                Object object = column.getCellObservableValue(row).getValue();
                if (object instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) object;
                    checkBox.setSelected(false);
                }
            }
        }
    }

    private <T extends Pane> List<Node> paneNodes(T parent) {
        return paneNodes(parent, new LinkedList<>());
    }

    private <T extends Pane> List<Node> paneNodes(T parent, List<Node> nodes) {
        for (Node node : parent.getChildren()) {
            if (node instanceof Pane) {
                paneNodes((Pane) node, nodes);
            } else {
                nodes.add(node);
            }
        }
        return nodes;
    }

    public void setEventOnlyAcceptNumber(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

//    public void setEventCurrencyFormat(TextField textField) {
//        textField.setOnKeyTyped(event -> {
//            String typedCharacter = event.getCharacter();
//            event.consume();
//            if (typedCharacter.matches("\\d*")) {
//                String currentText = textField.getText().replaceAll("\\.", "").replace(",", "");
//                long longVal = Long.parseLong(currentText.concat(typedCharacter));
//                textField.setText(new DecimalFormat("#,##0").format(longVal));
//            }
//        });
//    }
}
