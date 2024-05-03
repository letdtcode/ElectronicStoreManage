package com.mascara.electronicstoremanage.controllers.staff.dashboard;

import com.mascara.electronicstoremanage.entities.Staff;
import com.mascara.electronicstoremanage.services.staff.StaffServiceImpl;
import com.mascara.electronicstoremanage.utils.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 03/05/2024
 * Time      : 5:44 CH
 * Filename  : ProfileController
 */
public class ProfileController implements Initializable {
    @FXML
    private Text lblIdStaff;
    @FXML
    private Text lblFullNameStaff;
    @FXML
    private Text lblSexStaff;
    @FXML
    private Text lblDateOfBirthStaff;
    @FXML
    private Text lblPhoneNumber;
    @FXML
    private Text lblRole;
    @FXML
    private Button btnLogout;
    @FXML
    private Tab tabChangePassword;
    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnCancel;
    @FXML
    private Text lblEmail;
    @FXML
    private PasswordField txtCurrentPassword;
    @FXML
    private PasswordField txtNewPassword;
    @FXML
    private PasswordField txtConfirmNewPassword;

    @FXML
    public void setOnActionLogOut(ActionEvent actionEvent) {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
        Window mainWindow = currentStage.getOwner();
        if (mainWindow instanceof Stage) {
            ((Stage) mainWindow).close();
        }
        StageRequestUtils requestUtils = StageRequestUtils.builder()
                .url("/form/login.fxml")
                .title("Đăng nhập")
                .nodeOwner(null)
                .width(600d)
                .height(400d)
                .build();
        FXMLLoaderUtils.getInstance().showFormChild(requestUtils);
    }

    @FXML
    public void setOnActionChangePassword(ActionEvent actionEvent) {
        String currentPassword = txtCurrentPassword.getText().trim();
        String newPassword = txtNewPassword.getText().trim();
        String confirmNewPassword = txtConfirmNewPassword.getText().trim();

        boolean checkStrongPassword = Utilities.getInstance().checkStrongPassword(newPassword);
        if (checkStrongPassword) {
            if (newPassword.equals(confirmNewPassword)) {
                Staff staff = SharedData.getInstance().getStaffCurrentLogin();
                String oldPasswordHashed = staff.getPassword();
                if(PasswordHashingUtils.getInstance().verify(currentPassword,oldPasswordHashed)) {
                    boolean updatePasswordSuccess= StaffServiceImpl.getInstance().updatePassword(staff.getId(),newPassword);
                    if(updatePasswordSuccess) {
                        AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS,MessageUtils.UPDATE_PASSWORD_SUCCESS);
                        txtCurrentPassword.clear();
                        txtNewPassword.clear();
                        txtConfirmNewPassword.clear();
                    } else {
                        AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED,MessageUtils.WARNING_HAS_ERROR_OCCURRED);
                    }
                } else {
                    AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED,MessageUtils.OLD_PASSWORD_NOT_CORRECT);
                }
            } else {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_CONFIRM_PASSWORD_NOT_MATCH);
            }
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_PASSWORD_NOT_ENOUGH_STRONG);
        }
    }

    @FXML
    public void setOnActionCancelChangePassword(ActionEvent actionEvent) {
        txtCurrentPassword.clear();
        txtNewPassword.clear();
        txtConfirmNewPassword.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Staff staff = SharedData.getInstance().getStaffCurrentLogin();
        lblIdStaff.setText(staff.getId().toString());
        lblFullNameStaff.setText(staff.getFullName());
        lblSexStaff.setText(staff.getSex().getDisplay());
        lblDateOfBirthStaff.setText(staff.getDateOfBirth().toString());
        lblPhoneNumber.setText(staff.getPhoneNumber());
        lblEmail.setText(staff.getEmail());
        lblRole.setText(staff.getRole().getRoleName());
    }
}
