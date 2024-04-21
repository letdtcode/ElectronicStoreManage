package com.mascara.electronicstoremanage.controllers.staff.manage_employee;

import com.mascara.electronicstoremanage.enums.customer.CustomerStatusEnum;
import com.mascara.electronicstoremanage.enums.general.SexEnum;
import com.mascara.electronicstoremanage.services.role.RoleServiceImpl;
import com.mascara.electronicstoremanage.services.staff.StaffServiceImpl;
import com.mascara.electronicstoremanage.utils.Utillities;
import com.mascara.electronicstoremanage.view_model.role.RolePagingRequest;
import com.mascara.electronicstoremanage.view_model.role.RoleViewModel;
import com.mascara.electronicstoremanage.view_model.staff.StaffPagingRequest;
import com.mascara.electronicstoremanage.view_model.staff.StaffViewModel;
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
    private ComboBox cbbRoleFilter;
    @FXML
    private TextField txtSearchStaff;
    @FXML
    private TableView staffTableView;
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
    private TableColumn passwordColumn;
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

    private ObservableList<String> roleNameList;

    private ObservableList<String> statusCustomerList = FXCollections.observableArrayList(
            CustomerStatusEnum.ACTIVE.getDisplay(),
            CustomerStatusEnum.INACTIVE.getDisplay());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retrieveAllStaff();
        setUpUI();
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
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        staffTableView.setItems(staffViewModels);
    }

    private void setUpUI() {
        txtIdStaff.setEditable(false);
//       Assign cbb role name
        RolePagingRequest request = new RolePagingRequest();
        List<RoleViewModel> roleList = RoleServiceImpl.getInstance().retrieveAllRole(request);
        roleNameList = FXCollections.observableArrayList(roleList.stream()
                .map(roleViewModel -> roleViewModel.getRoleName()).collect(Collectors.toList()));
        cbbRole.setItems(roleNameList);
        cbbRole.getSelectionModel().selectFirst();

        cbbRoleFilter.setItems(roleNameList);
        cbbRoleFilter.getSelectionModel().selectFirst();

//        Assign cbb sex
        cbbSex.setItems(sexList);
        cbbSexFilter.setItems(sexList);
        cbbSex.getSelectionModel().selectFirst();
        cbbSexFilter.getSelectionModel().selectFirst();

//        Assign cbb status customer
        cbbStatus.setItems(statusCustomerList);
        cbbStatus.getSelectionModel().selectFirst();
    }

    @FXML
    public void setOnActionCreateStaff(ActionEvent actionEvent) {
    }

    @FXML
    public void setOnActionUpdateStaff(ActionEvent actionEvent) {
    }

    @FXML
    public void setOnActionDeleteStaff(ActionEvent actionEvent) {
    }

    @FXML
    public void setOnActionReloadStaff(ActionEvent actionEvent) {
    }

    private boolean validateDataStaff(String fullName, String phoneNumber, String email, String userName, String password) {
        boolean isValid = true;
        if (fullName == null || fullName.trim().isBlank() || phoneNumber == null
                || phoneNumber.trim().isBlank() || email == null
                || email.trim().isBlank() || userName == null
                || userName.trim().isBlank() || Utillities.getInstance().checkStrongPassword(password)) {
            isValid = false;
        }
        return isValid;
    }
}
