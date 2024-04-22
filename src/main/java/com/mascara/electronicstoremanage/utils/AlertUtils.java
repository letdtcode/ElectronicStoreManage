package com.mascara.electronicstoremanage.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 11:03 CH
 * Filename  : AlertUtils
 */
public class AlertUtils {
    public static void showMessageInfo(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hộp thoại thông báo");
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void showMessageWarning(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Hộp thoại thông báo");
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static boolean confirmationDialog(String title, String content) {
        ButtonType btnCancel = new ButtonType("Hủy", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType btnOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hộp thoại thông báo");
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(btnOk, btnCancel);
        Optional<ButtonType> choose = alert.showAndWait();
        return choose.get() == btnOk;
    }

    public static Optional<String> textInputDialog(String title, String content) {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle(title);
        textInputDialog.getDialogPane().setContentText(content);
        Utillities.getInstance().setEventOnlyAcceptNumber(textInputDialog.getEditor());
        return textInputDialog.showAndWait();
    }
}
