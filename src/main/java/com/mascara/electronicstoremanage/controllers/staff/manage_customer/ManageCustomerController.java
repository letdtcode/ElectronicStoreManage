package com.mascara.electronicstoremanage.controllers.staff.manage_customer;

import com.mascara.electronicstoremanage.enums.customer.CustomerStatusEnum;
import com.mascara.electronicstoremanage.enums.general.SexEnum;
import com.mascara.electronicstoremanage.services.customer.CustomerServiceImpl;
import com.mascara.electronicstoremanage.services.order.OrderServiceImpl;
import com.mascara.electronicstoremanage.utils.AlertUtils;
import com.mascara.electronicstoremanage.utils.MessageUtils;
import com.mascara.electronicstoremanage.utils.Utillities;
import com.mascara.electronicstoremanage.view_model.customer.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 5:59 CH
 * Filename  : ManageCustomerController
 */
public class ManageCustomerController implements Initializable {
    @FXML
    private TextField txtEmailCustomer;
    @FXML
    private TextField txtAddressCustomer;
    @FXML
    private RadioButton rdbMale;
    @FXML
    private RadioButton rdbFemale;
    @FXML
    private ComboBox cbbStatusCustomer;
    @FXML
    private Button btnAddCustomer;
    @FXML
    private Button btnUpdateCustomer;
    @FXML
    private Button btnReloadCustomer;
    @FXML
    private TextField txtIdCustomer;
    @FXML
    private TextField txtFullNameCustomer;
    @FXML
    private TextField txtPhoneNumberCustomer;
    @FXML
    private TabPane tabPanel;
    @FXML
    private TableView<CustomerViewModel> customerTableView;
    @FXML
    private TableColumn idCustomerColumn;
    @FXML
    private TableColumn fullNameCustomerColumn;
    @FXML
    private TableColumn phoneNumberColumn;
    @FXML
    private TableColumn emailColumn;
    @FXML
    private TableColumn addressColumn;
    @FXML
    private TableColumn sexColumn;
    @FXML
    private TableColumn statusCustomerColumn;
    @FXML
    private TableView historyOrderTableView;
    @FXML
    private TableColumn orderIdColumn;
    @FXML
    private TableColumn totalBillColumn;
    @FXML
    private TableColumn totalPayColumn;
    @FXML
    private TableColumn changeMoneyColumn;
    @FXML
    private TableColumn createdDateColumn;
    @FXML
    private TableColumn statusOrderColumn;
    @FXML
    private ComboBox cbbSexFilter;
    @FXML
    private ComboBox cbbStatusCustomerFilter;
    @FXML
    private TextField txtSearchCustomer;

    private ObservableList<CustomerViewModel> customerViewModels;
    private ObservableList<HistoryOrderViewModel> historyOrderViewModels = FXCollections.observableArrayList();

    private ObservableList<String> customerStatusList = FXCollections.observableArrayList(
            CustomerStatusEnum.ACTIVE.getDisplay(),
            CustomerStatusEnum.INACTIVE.getDisplay());
    private ObservableList<String> sexListFilter = FXCollections.observableArrayList(
            "Tất cả",
            SexEnum.MALE.getDisplay(),
            SexEnum.FEMALE.getDisplay());
    private ObservableList<String> customerStatusListFilter = FXCollections.observableArrayList(
            "Tất cả",
            CustomerStatusEnum.ACTIVE.getDisplay(),
            CustomerStatusEnum.INACTIVE.getDisplay());

    private SexEnum sexOfCustomer = SexEnum.MALE;
    @FXML
    private ToggleGroup sexGroup;
    @FXML
    private Pane customerPanel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retrieveAllCustomer();
        retrieveAllHistoryOrder();
        addListener();
        setUpUI();
    }

    private void setUpUI() {
        txtIdCustomer.setEditable(false);
        cbbSexFilter.setItems(sexListFilter);
        cbbSexFilter.getSelectionModel().selectFirst();

        cbbStatusCustomer.setItems(customerStatusList);
        cbbStatusCustomer.getSelectionModel().selectFirst();

        cbbStatusCustomerFilter.setItems(customerStatusListFilter);
        cbbStatusCustomerFilter.getSelectionModel().selectFirst();
    }

    private void retrieveAllHistoryOrder() {
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        totalBillColumn.setCellValueFactory(new PropertyValueFactory<>("totalBill"));
        totalPayColumn.setCellValueFactory(new PropertyValueFactory<>("totalPay"));
        changeMoneyColumn.setCellValueFactory(new PropertyValueFactory<>("changeMoney"));
        createdDateColumn.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        statusOrderColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        historyOrderTableView.setItems(historyOrderViewModels);
    }

    private void addListener() {
        tabPanel.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int selectedIndex = newValue.intValue();
            if (selectedIndex == 1) {
                CustomerViewModel customerViewModel = customerTableView.getSelectionModel().getSelectedItem();
                HistoryOrderPagingRequest request = new HistoryOrderPagingRequest();
                request.setCondition(" customerId = " + customerViewModel.getId());
                List<HistoryOrderViewModel> historyOrderList = OrderServiceImpl.getInstance()
                        .retrieveHistoryOrderCustomer(request);
                historyOrderViewModels = FXCollections.observableArrayList(historyOrderList);
            }
        });
        Utillities.getInstance().setEventOnlyAcceptNumber(txtPhoneNumberCustomer);

//        Set event for each row
        customerTableView.setRowFactory(param -> {
            TableRow<CustomerViewModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    int rowIndex = customerTableView.getSelectionModel().getSelectedIndex();
                    CustomerViewModel customerViewModel = customerTableView.getItems().get(rowIndex);
                    txtIdCustomer.setText(customerViewModel.getId().toString());
                    txtFullNameCustomer.setText(customerViewModel.getFullName());
                    txtPhoneNumberCustomer.setText(customerViewModel.getPhoneNumber());
                    txtEmailCustomer.setText(customerViewModel.getEmail());
                    txtAddressCustomer.setText(customerViewModel.getAddress());
                    if (customerViewModel.getSex().equals(SexEnum.MALE))
                        rdbMale.setSelected(true);
                    else
                        rdbFemale.setSelected(true);
                    cbbStatusCustomer.setValue(customerViewModel.getStatus().getDisplay());
                }
            });
            return row;
        });

        //        search filter event
        FilteredList<CustomerViewModel> filteredList = new FilteredList<>(customerViewModels, b -> true);
        txtSearchCustomer.textProperty().addListener((observable, oldValue, newValue) -> {
            searchAndFilterCustomer(newValue, cbbSexFilter.getValue().toString(), cbbStatusCustomerFilter.getValue().toString(), filteredList);
        });

        //        filter sex and status
        cbbSexFilter.valueProperty().addListener((observable, oldValue, newValue) -> {
                    String sexSelected = (String) cbbSexFilter.getSelectionModel().getSelectedItem();
                    searchAndFilterCustomer(txtSearchCustomer.getText().trim(), sexSelected, cbbStatusCustomerFilter.getValue().toString(), filteredList);
                }
        );
        cbbStatusCustomerFilter.valueProperty().addListener((observable, oldValue, newValue) -> {
                    String statusSelected = (String) cbbStatusCustomerFilter.getSelectionModel().getSelectedItem();
                    searchAndFilterCustomer(txtSearchCustomer.getText().trim(), cbbSexFilter.getValue().toString(), statusSelected, filteredList);
                }
        );

        SortedList<CustomerViewModel> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(customerTableView.comparatorProperty());
        customerTableView.setItems(sortedList);
    }

    private boolean searchByFullNameOrPhoneNumber(String newValueTextField, CustomerViewModel customerViewModel) {
        if (newValueTextField.isEmpty() || newValueTextField.isBlank() || newValueTextField == null)
            return true;
        String searchKeyword = newValueTextField.toLowerCase();

        if (customerViewModel.getFullName().toLowerCase().contains(searchKeyword))
            return true;
        else if (customerViewModel.getPhoneNumber().toLowerCase().contains(searchKeyword))
            return true;
        else
            return false;
    }

    private boolean filterBySex(String sex, CustomerViewModel customerViewModel) {
        boolean result = false;
        switch (sex) {
            case "Tất cả":
                result = true;
                break;
            case "Nam":
                if (customerViewModel.getSex().equals(SexEnum.MALE))
                    result = true;
                break;
            case "Nữ":
                if (customerViewModel.getSex().equals(SexEnum.FEMALE))
                    result = true;
                break;
        }
        return result;
    }

    private boolean filterByStatus(String statusStaff, CustomerViewModel customerViewModel) {
        boolean result = false;
        switch (statusStaff) {
            case "Tất cả":
                result = true;
                break;
            case "Hoạt động":
                if (customerViewModel.getStatus().equals(CustomerStatusEnum.ACTIVE))
                    result = true;
                break;
            case "Ngừng hoạt động":
                if (customerViewModel.getStatus().equals(CustomerStatusEnum.INACTIVE))
                    result = true;
                break;
        }
        return result;
    }

    private void searchAndFilterCustomer(String newValueTextField, String sexFilter, String statusCustomerFilter,
                                         FilteredList<CustomerViewModel> filteredList) {
        filteredList.setPredicate(customerViewModel -> {
            boolean resultSearch = searchByFullNameOrPhoneNumber(newValueTextField, customerViewModel);
            boolean resultFilterSex = filterBySex(sexFilter, customerViewModel);
            boolean resultFilterStatus = filterByStatus(statusCustomerFilter, customerViewModel);
            return resultSearch && resultFilterSex && resultFilterStatus;
        });
    }

    private void retrieveAllCustomer() {
        CustomerPagingRequest request = new CustomerPagingRequest();
        List<CustomerViewModel> customerList = CustomerServiceImpl.getInstance().retrieveAllCustomer(request);
        customerViewModels = FXCollections.observableList(customerList);
        idCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        fullNameCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        statusCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        customerTableView.setItems(customerViewModels);
    }

    @FXML
    public void setOnActionCreateCustomer(ActionEvent actionEvent) {
        boolean isValid = validateDataCustomer(
                txtFullNameCustomer.getText(),
                txtPhoneNumberCustomer.getText(),
                txtEmailCustomer.getText(),
                txtAddressCustomer.getText());
        if (isValid) {
            CustomerCreateRequest request = CustomerCreateRequest.builder()
                    .fullName(txtFullNameCustomer.getText())
                    .phoneNumber(txtPhoneNumberCustomer.getText())
                    .email(txtEmailCustomer.getText())
                    .address(txtAddressCustomer.getText())
                    .sex(sexOfCustomer)
                    .status(CustomerStatusEnum.getEnumByDisplay(cbbStatusCustomer.getValue().toString()))
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
                txtFullNameCustomer.getText(),
                txtPhoneNumberCustomer.getText(),
                txtEmailCustomer.getText(),
                txtAddressCustomer.getText());
        if (isValid && !customerTableView.getSelectionModel().isEmpty()) {
            CustomerViewModel customerViewModel = customerTableView.getSelectionModel().getSelectedItem();
            CustomerUpdateRequest request = CustomerUpdateRequest.builder()
                    .id(customerViewModel.getId())
                    .fullName(txtFullNameCustomer.getText())
                    .phoneNumber(txtPhoneNumberCustomer.getText())
                    .email(txtEmailCustomer.getText())
                    .address(txtAddressCustomer.getText())
                    .sex(sexOfCustomer)
                    .status(CustomerStatusEnum.getEnumByDisplay(cbbStatusCustomer.getValue().toString()))
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
    public void setOnActionReloadCustomer(ActionEvent actionEvent) {
        Utillities.getInstance().clearAllTextField(customerPanel);
        retrieveAllCustomer();
    }

    @FXML
    public void setOnActionChangeSex(ActionEvent actionEvent) {
        if (rdbMale.isSelected()) {
            sexOfCustomer = SexEnum.MALE;
        } else if (rdbFemale.isSelected()) {
            sexOfCustomer = SexEnum.FEMALE;
        }
    }
}
