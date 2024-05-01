package com.mascara.electronicstoremanage.controllers.staff.manage_employee;

import com.mascara.electronicstoremanage.enums.general.SexEnum;
import com.mascara.electronicstoremanage.enums.staff.StaffStatusEnum;
import com.mascara.electronicstoremanage.services.role.RoleServiceImpl;
import com.mascara.electronicstoremanage.services.staff.StaffServiceImpl;
import com.mascara.electronicstoremanage.utils.AlertUtils;
import com.mascara.electronicstoremanage.utils.MessageUtils;
import com.mascara.electronicstoremanage.utils.TableViewExporterUtils;
import com.mascara.electronicstoremanage.utils.Utillities;
import com.mascara.electronicstoremanage.view_model.role.RolePagingRequest;
import com.mascara.electronicstoremanage.view_model.role.RoleViewModel;
import com.mascara.electronicstoremanage.view_model.staff.StaffCreateRequest;
import com.mascara.electronicstoremanage.view_model.staff.StaffPagingRequest;
import com.mascara.electronicstoremanage.view_model.staff.StaffUpdateRequest;
import com.mascara.electronicstoremanage.view_model.staff.StaffViewModel;
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
import java.util.stream.Collectors;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 5:59 CH
 * Filename  : ManageEmployeeController
 */
public class ManageEmployeeController implements Initializable {
    @FXML
    private Button btnAddStaff;
    @FXML
    private Button btnUpdateStaff;
    @FXML
    private Button btnDeleteStaff;
    @FXML
    private Button btnReloadStaff;
    @FXML
    private TextField txtIdStaff;
    @FXML
    private TextField txtFullNameStaff;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private ComboBox cbbRole;
    @FXML
    private DatePicker dtpDateOfBirth;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;
    @FXML
    private ComboBox cbbSex;
    @FXML
    private ComboBox cbbStatus;
    @FXML
    private ComboBox cbbSexFilter;
    @FXML
    private TextField txtSearchStaff;
    @FXML
    private TableView<StaffViewModel> staffTableView;
    @FXML
    private TableColumn idStaffColumn;
    @FXML
    private TableColumn fullNameColumn;
    @FXML
    private TableColumn phoneNumberColumn;
    @FXML
    private TableColumn emailColumn;
    @FXML
    private TableColumn roleColumn;
    @FXML
    private TableColumn dateOfBirthColumn;
    @FXML
    private TableColumn userNameColumn;
    @FXML
    private TableColumn sexColumn;
    @FXML
    private TableColumn statusColumn;
    @FXML
    private TextField txtEmail;

    private ObservableList<StaffViewModel> staffViewModels;
    private ObservableList<String> sexList = FXCollections.observableArrayList(
            SexEnum.MALE.getDisplay(),
            SexEnum.FEMALE.getDisplay());

    private ObservableList<String> sexListFilter = FXCollections.observableArrayList(
            "Tất cả",
            SexEnum.MALE.getDisplay(),
            SexEnum.FEMALE.getDisplay());

    private ObservableList<String> roleNameList;

    private ObservableList<String> statusStaffList = FXCollections.observableArrayList(
            StaffStatusEnum.ACTIVE.getDisplay(),
            StaffStatusEnum.INACTIVE.getDisplay());

    private ObservableList<String> statusStaffListFilter = FXCollections.observableArrayList(
            "Tất cả",
            StaffStatusEnum.ACTIVE.getDisplay(),
            StaffStatusEnum.INACTIVE.getDisplay());
    @FXML
    private Pane staffPanel;
    @FXML
    private ComboBox cbbStatusFilter;
    @FXML
    private Button btnExportExcel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retrieveAllStaff();
        setUpUI();
        addListener();
    }

    private void addListener() {
        staffTableView.setRowFactory(param -> {
            TableRow<StaffViewModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    int rowIndex = staffTableView.getSelectionModel().getSelectedIndex();
                    StaffViewModel staffViewModel = staffTableView.getItems().get(rowIndex);
                    txtIdStaff.setText(staffViewModel.getId().toString());
                    txtFullNameStaff.setText(staffViewModel.getFullName());
                    txtPhoneNumber.setText(staffViewModel.getPhoneNumber());
                    txtEmail.setText(staffViewModel.getEmail());
                    cbbRole.setValue(staffViewModel.getRoleName());
                    dtpDateOfBirth.setValue(staffViewModel.getDateOfBirth());
                    txtUserName.setText(staffViewModel.getUserName());
                    txtPassword.setText("");
                    cbbSex.setValue(staffViewModel.getSex().getDisplay());
                    cbbStatus.setValue(staffViewModel.getStatus().getDisplay());
                }
            });
            return row;
        });

//        search filter event
        FilteredList<StaffViewModel> filteredList = new FilteredList<>(staffViewModels, b -> true);
        txtSearchStaff.textProperty().addListener((observable, oldValue, newValue) -> {
            searchAndFilterStaff(newValue, cbbSexFilter.getValue().toString(), cbbStatusFilter.getValue().toString(), filteredList);
        });

        //        filter sex and status
        cbbSexFilter.valueProperty().addListener((observable, oldValue, newValue) -> {
                    String sexSelected = (String) cbbSexFilter.getSelectionModel().getSelectedItem();
                    searchAndFilterStaff(txtSearchStaff.getText().trim(), sexSelected, cbbStatusFilter.getValue().toString(), filteredList);
                }
        );
        cbbStatusFilter.valueProperty().addListener((observable, oldValue, newValue) -> {
                    String statusSelected = (String) cbbStatusFilter.getSelectionModel().getSelectedItem();
                    searchAndFilterStaff(txtSearchStaff.getText().trim(), cbbSexFilter.getValue().toString(), statusSelected, filteredList);
                }
        );

        SortedList<StaffViewModel> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(staffTableView.comparatorProperty());
        staffTableView.setItems(sortedList);
    }

    private boolean searchByFullNameOrPhoneNumber(String newValueTextField, StaffViewModel staffViewModel) {
        if (newValueTextField.isEmpty() || newValueTextField.isBlank() || newValueTextField == null)
            return true;
        String searchKeyword = newValueTextField.toLowerCase();

        if (staffViewModel.getFullName().toLowerCase().contains(searchKeyword))
            return true;
        else if (staffViewModel.getPhoneNumber().toLowerCase().contains(searchKeyword))
            return true;
        else
            return false;
    }

    private boolean filterBySex(String sex, StaffViewModel staffViewModel) {
        boolean result = false;
        switch (sex) {
            case "Tất cả":
                result = true;
                break;
            case "Nam":
                if (staffViewModel.getSex().equals(SexEnum.MALE))
                    result = true;
                break;
            case "Nữ":
                if (staffViewModel.getSex().equals(SexEnum.FEMALE))
                    result = true;
                break;
        }
        return result;
    }

    private boolean filterByStatus(String statusStaff, StaffViewModel staffViewModel) {
        boolean result = false;
        switch (statusStaff) {
            case "Tất cả":
                result = true;
                break;
            case "Đang làm việc":
                if (staffViewModel.getStatus().equals(StaffStatusEnum.ACTIVE))
                    result = true;
                break;
            case "Đã nghỉ việc":
                if (staffViewModel.getStatus().equals(StaffStatusEnum.INACTIVE))
                    result = true;
                break;
        }
        return result;
    }

    private void searchAndFilterStaff(String newValueTextField, String sexFilter, String statusStaffFilter,
                                      FilteredList<StaffViewModel> filteredList) {
        filteredList.setPredicate(staffViewModel -> {
            boolean resultSearch = searchByFullNameOrPhoneNumber(newValueTextField, staffViewModel);
            boolean resultFilterSex = filterBySex(sexFilter, staffViewModel);
            boolean resultFilterStatus = filterByStatus(statusStaffFilter, staffViewModel);
            return resultSearch && resultFilterSex && resultFilterStatus;
        });
    }

    private void retrieveAllStaff() {
        StaffPagingRequest request = new StaffPagingRequest();
        List<StaffViewModel> staffList = StaffServiceImpl.getInstance().retrieveAllStaff(request);
        staffViewModels = FXCollections.observableList(staffList);
        idStaffColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("roleName"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        staffTableView.setItems(staffViewModels);
    }

    private void setUpUI() {
        txtIdStaff.setEditable(false);
        Utillities.getInstance().setEventOnlyAcceptNumber(txtPhoneNumber);
//       Assign cbb role name
        RolePagingRequest request = new RolePagingRequest();
        List<RoleViewModel> roleList = RoleServiceImpl.getInstance().retrieveAllRole(request);
        roleNameList = FXCollections.observableArrayList(roleList.stream()
                .map(roleViewModel -> roleViewModel.getRoleName()).collect(Collectors.toList()));
        cbbRole.setItems(roleNameList);
        cbbRole.getSelectionModel().selectFirst();

        cbbStatusFilter.setItems(statusStaffListFilter);
        cbbStatusFilter.getSelectionModel().selectFirst();

//        Assign cbb sex
        cbbSex.setItems(sexList);
        cbbSexFilter.setItems(sexListFilter);
        cbbSex.getSelectionModel().selectFirst();
        cbbSexFilter.getSelectionModel().selectFirst();

//        Assign cbb status customer
        cbbStatus.setItems(statusStaffList);
        cbbStatus.getSelectionModel().selectFirst();
    }

    @FXML
    public void setOnActionCreateStaff(ActionEvent actionEvent) {
        boolean isValid = validateDataStaff(
                txtFullNameStaff.getText(),
                txtPhoneNumber.getText(),
                txtEmail.getText(),
                txtUserName.getText(),
                txtPassword.getText());
        if (isValid) {
            StaffCreateRequest request = StaffCreateRequest.builder()
                    .fullName(txtFullNameStaff.getText().trim())
                    .phoneNumber(txtPhoneNumber.getText().trim())
                    .email(txtEmail.getText().trim())
                    .dateOfBirth(dtpDateOfBirth.getValue())
                    .sex(SexEnum.getEnumByDisplay(cbbSex.getValue().toString()))
                    .userName(txtUserName.getText().trim())
                    .password(txtPassword.getText().trim())
                    .status(StaffStatusEnum.getEnumByDisplay(cbbStatus.getValue().toString()))
                    .roleName(cbbRole.getValue().toString())
                    .build();
            Long staffId = StaffServiceImpl.getInstance().insertStaff(request);
            if (staffId == -1) {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_USER_NAME_OR_PHONE_NUMBER_DUPLICATED);
            } else {
                AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_CREATE_STAFF_SUCCESS);
            }
            retrieveAllStaff();
        }
    }

    @FXML
    public void setOnActionUpdateStaff(ActionEvent actionEvent) {
        boolean isValid = validateDataStaff(
                txtFullNameStaff.getText(),
                txtPhoneNumber.getText(),
                txtEmail.getText(),
                txtUserName.getText(),
                txtPassword.getText());
        if (isValid && !staffTableView.getSelectionModel().isEmpty()) {
            StaffViewModel staffViewModel = staffTableView.getSelectionModel().getSelectedItem();
            StaffUpdateRequest request = StaffUpdateRequest.builder()
                    .id(staffViewModel.getId())
                    .fullName(txtFullNameStaff.getText().trim())
                    .phoneNumber(txtPhoneNumber.getText().trim())
                    .email(txtEmail.getText().trim())
                    .dateOfBirth(dtpDateOfBirth.getValue())
                    .sex(SexEnum.getEnumByDisplay(cbbSex.getValue().toString()))
                    .userName(txtUserName.getText().trim())
                    .password(txtPassword.getText().trim())
                    .status(StaffStatusEnum.getEnumByDisplay(cbbStatus.getValue().toString()))
                    .roleName(cbbRole.getValue().toString())
                    .build();
            boolean success = StaffServiceImpl.getInstance().updateStaff(request);
            if (success) {
                AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_UPDATE_STAFF_SUCCESS);
            } else {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_USER_NAME_OR_PHONE_NUMBER_DUPLICATED);
            }
            retrieveAllStaff();
        } else if (staffTableView.getSelectionModel().isEmpty()) {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_SELECT_ROW);
        }
    }

    @FXML
    public void setOnActionDeleteStaff(ActionEvent actionEvent) {
        StaffViewModel staffViewModel = staffTableView.getSelectionModel().getSelectedItem();
        boolean deleteSuccess = StaffServiceImpl.getInstance().deleteStaff(staffViewModel.getId());
        if (deleteSuccess) {
            AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_DELETE_STAFF_SUCCESS);
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_HAS_ERROR_OCCURRED);
        }
        Utillities.getInstance().clearAllTextField(staffPanel);
        retrieveAllStaff();
    }

    @FXML
    public void setOnActionReloadStaff(ActionEvent actionEvent) {
        Utillities.getInstance().clearAllTextField(staffPanel);
        retrieveAllStaff();
    }

    private boolean validateDataStaff(String fullName, String phoneNumber, String email, String userName, String password) {
        boolean isValid = true;
        if (fullName == null || fullName.trim().isBlank() || phoneNumber == null
                || phoneNumber.trim().isBlank() || email == null
                || email.trim().isBlank() || userName == null
                || userName.trim().isBlank()) {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_DATA_NOT_VALID);
            isValid = false;
        } else if (!Utillities.getInstance().checkStrongPassword(password)) {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_PASSWORD_NOT_ENOUGH_STRONG);
            isValid = false;
        }
        return isValid;
    }

    @FXML
    public void setOnActionExportExcel(ActionEvent actionEvent) {
        boolean exportExcel = TableViewExporterUtils.getInstance().exportExcel(staffTableView);
        if (exportExcel)
            AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.EXPORT_EXCEL_SUCCESS);
        else
            AlertUtils.showMessageInfo(MessageUtils.TITLE_FAILED, MessageUtils.EXPORT_EXCEL_FAILED);
    }
}
