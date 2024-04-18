package com.mascara.electronicstoremanage.controllers.staff.sale;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 18/04/2024
 * Time      : 11:43 CH
 * Filename  : ManageSaleController
 */
public class ManageSaleController implements Initializable {
    @FXML
    private TableView orderWaitingTableView;
    @FXML
    private TextField btnSearchProduct;
    @FXML
    private Button btnAddToCard;
    @FXML
    private ComboBox cbbCategoryName;
    @FXML
    private TableView productTableView;
    @FXML
    private Pane panelCamera;
    @FXML
    private Text lblIdOrder;
    @FXML
    private Text lblTotalBill;
    @FXML
    private Text lblDiscountMoney;
    @FXML
    private Text lblTotalPay;
    @FXML
    private TextField txtCustomerGive;
    @FXML
    private Text lblChangeMoney;
    @FXML
    private Text lblIdCustomer;
    @FXML
    private Text lblNameCustomer;
    @FXML
    private Button btnChangeCustomer;
    @FXML
    private Button btnCheckOut;
    @FXML
    private TextArea textareaNote;
    @FXML
    private Button btnCreateOrder;
    @FXML
    private ComboBox cbbPayment;
    @FXML
    private ComboBox cbbDelivery;
    @FXML
    private Button btnCancelOrder;
    @FXML
    private Button btnReloadOrder;
    @FXML
    private TableView cardItemsTableView;
    @FXML
    private Button btnDeleteCardItem;
    @FXML
    private Button btnDelteAllCardItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
