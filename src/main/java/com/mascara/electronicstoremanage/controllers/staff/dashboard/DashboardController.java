package com.mascara.electronicstoremanage.controllers.staff.dashboard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 5:58 CH
 * Filename  : DashboardController
 */
public class DashboardController implements Initializable {

    @FXML
    private Pane tabSale;
    @FXML
    private Pane tabProduct;
    @FXML
    private Pane tabProperty;
    @FXML
    private Pane tabOrder;
    @FXML
    private Pane tabPromotion;
    @FXML
    private Pane tabEmployee;
    @FXML
    private Pane tabCustomer;
    @FXML
    private Pane tabStatistic;
    @FXML
    private BorderPane panelMain;

    @FXML
    private void showTabSale(MouseEvent event) {
        loadPage("panel_sale");
    }

    @FXML
    private void showTabProduct(MouseEvent event) {
        loadPage("panel_product");
    }

    @FXML
    private void showTabPropertiesProduct(MouseEvent event) {
        loadPage("panel_properties_product");
    }

    @FXML
    private void showTabOrder(MouseEvent event) {
        loadPage("panel_order");
    }

    @FXML
    private void showTabPromotion(MouseEvent event) {
        loadPage("panel_promotion");
    }

    @FXML
    private void showTabEmployee(MouseEvent event) {
        loadPage("panel_employee");
    }

    @FXML
    private void showTabCustomer(MouseEvent event) {
        loadPage("panel_customer");
    }

    @FXML
    private void showTabStatistic(MouseEvent event) {
        loadPage("panel_statistic");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPage("panel_sale");
    }

    public void loadPage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/form/" + page + ".fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        panelMain.setCenter(root);
    }
}
