package com.mascara.electronicstoremanage.controllers.staff.sale;

import com.mascara.electronicstoremanage.enums.customer.CustomerStatusEnum;
import com.mascara.electronicstoremanage.enums.general.SexEnum;
import com.mascara.electronicstoremanage.services.customer.CustomerServiceImpl;
import com.mascara.electronicstoremanage.utils.AlertUtils;
import com.mascara.electronicstoremanage.utils.MessageUtils;
import com.mascara.electronicstoremanage.utils.Utillities;
import com.mascara.electronicstoremanage.view_model.customer.CustomerCreateRequest;
import com.mascara.electronicstoremanage.view_model.customer.CustomerPagingRequest;
import com.mascara.electronicstoremanage.view_model.customer.CustomerUpdateRequest;
import com.mascara.electronicstoremanage.view_model.customer.CustomerViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private TableView<CustomerViewModel> customerTableView;
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

    private SexEnum sexOfCustomer = SexEnum.MALE;

    private CustomerStatusEnum customerStatus = CustomerStatusEnum.ACTIVE;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retrieveAllCustomer();
        addListener();

    }

    private void addListener() {
//        search filter event
        FilteredList<CustomerViewModel> filteredList = new FilteredList<>(customerViewModels, b -> true);
        txtSearchCustomer.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Hello");
            filteredList.setPredicate(customerViewModel -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null)
                    return true;
                String searchKeyword = newValue.toLowerCase();

                if (customerViewModel.getFullName().toLowerCase().contains(searchKeyword))
                    return true;
                else if (customerViewModel.getPhoneNumber().toLowerCase().contains(searchKeyword))
                    return true;
                else
                    return false;

            });
        });

        SortedList<CustomerViewModel> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(customerTableView.comparatorProperty());
        customerTableView.setItems(sortedList);

//        tabpanel event
        tabPanel.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int selectedIndex = newValue.intValue();
            if (selectedIndex == 0) {
                CustomerPagingRequest customerPagingRequest = new CustomerPagingRequest();
                List<CustomerViewModel> customerList = CustomerServiceImpl.getInstance().retrieveAllCustomer(customerPagingRequest);
                customerViewModels = FXCollections.observableArrayList(customerList);
            } else if (selectedIndex == 1 && customerTableView.getSelectionModel().getSelectedIndex() == -1) {
                clearTextField();
            }
        });

//        Mapping row in table view to all textfield
        customerTableView.setRowFactory(param -> {
            TableRow<CustomerViewModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    int rowIndex = customerTableView.getSelectionModel().getSelectedIndex();
                    CustomerViewModel customerViewModel = customerTableView.getItems().get(rowIndex);
                    txtIdCustomer.setText(customerViewModel.getId().toString());
                    txtNameCustomer.setText(customerViewModel.getFullName());
                    txtPhoneNumber.setText(customerViewModel.getPhoneNumber());
                    txtEmail.setText(customerViewModel.getEmail());
                    textAreaAddress.setText(customerViewModel.getAddress());

                    switch (customerViewModel.getSex()) {
                        case MALE -> rdbMale.setSelected(true);
                        case FEMALE -> rdbFemale.setSelected(true);
                    }

                    switch (customerViewModel.getStatus()) {
                        case ACTIVE -> rdbActive.setSelected(true);
                        case INACTIVE -> rdbInActive.setSelected(true);
                    }
                }
            });
            return row;
        });

//        phonerNumber must be number input
        Utillities.getInstance().setEventOnlyAcceptNumber(txtPhoneNumber);
    }

    private void retrieveAllCustomer() {
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
        txtIdCustomer.setEditable(false);
    }

    @FXML
    public void setOnActionCreateCustomer(ActionEvent actionEvent) {
        boolean isValid = validateDataCustomer(
                txtNameCustomer.getText(),
                txtPhoneNumber.getText(),
                txtEmail.getText(),
                textAreaAddress.getText());
        if (isValid) {
            CustomerCreateRequest request = CustomerCreateRequest.builder()
                    .fullName(txtNameCustomer.getText())
                    .phoneNumber(txtPhoneNumber.getText())
                    .email(txtEmail.getText())
                    .address(textAreaAddress.getText())
                    .sex(sexOfCustomer)
                    .status(customerStatus)
                    .build();
            Long customerId = CustomerServiceImpl.getInstance().insertCustomer(request);
            if (customerId == -1) {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_PHONE_NUMBER_DUPLICATED);
            } else {
                AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_CREATE_CUSTOMER_SUCCESS);
            }
            retrieveAllCustomer();
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_DATA_NOT_VALID);
        }
    }

    @FXML
    public void setOnActionUpdateCustomer(ActionEvent actionEvent) {
        boolean isValid = validateDataCustomer(
                txtNameCustomer.getText(),
                txtPhoneNumber.getText(),
                txtEmail.getText(),
                textAreaAddress.getText());
        if (isValid && !customerTableView.getSelectionModel().isEmpty()) {
            CustomerViewModel customerViewModel = customerTableView.getSelectionModel().getSelectedItem();
            CustomerUpdateRequest request = CustomerUpdateRequest.builder()
                    .id(customerViewModel.getId())
                    .fullName(txtNameCustomer.getText())
                    .phoneNumber(txtPhoneNumber.getText())
                    .email(txtEmail.getText())
                    .address(textAreaAddress.getText())
                    .sex(sexOfCustomer)
                    .status(customerStatus)
                    .build();
            boolean success = CustomerServiceImpl.getInstance().updateCustomer(request);
            if (success) {
                AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_UPDATE_CUSTOMER_SUCCESS);
            } else {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_PHONE_NUMBER_DUPLICATED);
            }
            retrieveAllCustomer();
        } else if (customerTableView.getSelectionModel().isEmpty()) {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_SELECT_ROW);
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_DATA_NOT_VALID);
        }
    }

    public void clearTextField() {
        txtIdCustomer.setText("");
        txtNameCustomer.setText("");
        rdbMale.setSelected(true);
        txtPhoneNumber.setText("");
        txtEmail.setText("");
        rdbActive.setSelected(true);
        textAreaAddress.setText("");
    }


    @FXML
    public void setOnActionReload(ActionEvent actionEvent) {
        clearTextField();
    }

    private boolean validateDataCustomer(String fullName, String phoneNumber, String email, String address) {
        boolean isValid = true;
        if (fullName == null || fullName.trim().isBlank() || phoneNumber == null
                || phoneNumber.trim().isBlank() || email == null
                || email.trim().isBlank() || address == null
                || address.trim().isBlank()) {
            isValid = false;
        }
        return isValid;
    }

    @FXML
    public void setOnChangeSex(ActionEvent actionEvent) {
        if (rdbMale.isSelected()) {
            sexOfCustomer = SexEnum.MALE;
        } else if (rdbFemale.isSelected()) {
            sexOfCustomer = SexEnum.FEMALE;
        }
    }


    @FXML
    public void setOnChangeStatus(ActionEvent actionEvent) {
        if (rdbActive.isSelected()) {
            customerStatus = CustomerStatusEnum.ACTIVE;
        } else if (rdbInActive.isSelected()) {
            customerStatus = CustomerStatusEnum.INACTIVE;
        }
    }

    @FXML
    public void setOnActionSubmitChangeCustomer(ActionEvent actionEvent) {
        if (customerTableView.getSelectionModel().getSelectedIndex() > -1) {
            int rowIndex = customerTableView.getSelectionModel().getSelectedIndex();
            CustomerViewModel customerViewModel = customerTableView.getItems().get(rowIndex);
            SharedCustomer.getInstance().setCustomerId(customerViewModel.getId());
            SharedCustomer.getInstance().setNameCustomer(customerViewModel.getFullName());
            ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_SELECT_ROW);
        }
    }
}
