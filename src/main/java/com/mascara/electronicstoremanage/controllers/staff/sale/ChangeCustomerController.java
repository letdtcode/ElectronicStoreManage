package com.mascara.electronicstoremanage.controllers.staff.sale;

import com.mascara.electronicstoremanage.services.customer.CustomerServiceImpl;
import com.mascara.electronicstoremanage.view_model.customer.CustomerPagingRequest;
import com.mascara.electronicstoremanage.view_model.customer.CustomerViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 20/04/2024
 * Time      : 10:49 CH
 * Filename  : ChangeCustomerController
 */
public class ChangeCustomerController implements Initializable {
    @FXML
    private TabPane tabPanel;
    @FXML
    private TextField txtSearchCustomer;
    @FXML
    private TableView customerTableView;
    @FXML
    private Button btnAddCustomer;
    @FXML
    private Button btnUpdateCustomer;
    @FXML
    private Button btnReload;
    @FXML
    private TextField txtIdCustomer;
    @FXML
    private TextField txtNameCustomer;
    @FXML
    private RadioButton rdbMale;
    @FXML
    private ToggleGroup sexGroup;
    @FXML
    private RadioButton rdbFemale;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtEmail;
    @FXML
    private RadioButton rdbActive;
    @FXML
    private ToggleGroup statusGroup;
    @FXML
    private RadioButton rdbInActive;
    @FXML
    private TextArea textAreaAddress;
    @FXML
    private Button btnChooseCustomer;

    private ObservableList<CustomerViewModel> customerViewModels;
    @FXML
    private TableColumn idCustomerColumn;
    @FXML
    private TableColumn fullNameColumn;
    @FXML
    private TableColumn sexColumn;
    @FXML
    private TableColumn phoneNumberColumn;
    @FXML
    private TableColumn emailColumn;
    @FXML
    private TableColumn addressColumn;
    @FXML
    private TableColumn statusColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerPagingRequest request = new CustomerPagingRequest();
        List<CustomerViewModel> customerList = CustomerServiceImpl.getInstance().retrieveAllCustomer(request);
        customerViewModels = FXCollections.observableList(customerList);
        idCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        customerTableView.setItems(customerViewModels);
    }

    @FXML
    public void setOnActionCreateCustomer(ActionEvent actionEvent) {
    }

    @FXML
    public void setOnActionUpdateCustomer(ActionEvent actionEvent) {
    }

    @FXML
    public void setOnActionReload(ActionEvent actionEvent) {
    }
}
