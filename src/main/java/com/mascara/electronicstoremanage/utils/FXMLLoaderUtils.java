package com.mascara.electronicstoremanage.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:02 CH
 * Filename  : FXMLLoaderUtils
 */
public class FXMLLoaderUtils {
    private static FXMLLoaderUtils instance = null;

    private FXMLLoaderUtils() {

    }

    public static FXMLLoaderUtils getInstance() {
        if (instance == null)
            instance = new FXMLLoaderUtils();
        return instance;
    }

    public void showFormChild(StageRequestUtils request) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(request.getUrl()));
        try {
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initOwner(request.getNodeOwner().getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(new Scene(root, request.getWidth(), request.getHeight()));
            stage.setTitle(request.getTitle());
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
