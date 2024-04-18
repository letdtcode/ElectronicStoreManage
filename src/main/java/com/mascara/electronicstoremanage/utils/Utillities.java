package com.mascara.electronicstoremanage.utils;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.text.Normalizer;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 18/04/2024
 * Time      : 7:20 CH
 * Filename  : SlugUtils
 */
public class Utillities {
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    private static Utillities instance = null;

    public static Utillities getInstance() {
        if (instance == null)
            instance = new Utillities();
        return instance;
    }

    private Utillities() {

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
}
