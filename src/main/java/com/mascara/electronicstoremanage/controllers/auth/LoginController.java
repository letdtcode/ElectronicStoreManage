package com.mascara.electronicstoremanage.controllers.auth;

import com.mascara.electronicstoremanage.controllers.staff.dashboard.DashboardController;
import com.mascara.electronicstoremanage.entities.Staff;
import com.mascara.electronicstoremanage.services.auth.AuthServiceImpl;
import com.mascara.electronicstoremanage.services.staff.StaffServiceImpl;
import com.mascara.electronicstoremanage.utils.AlertUtils;
import com.mascara.electronicstoremanage.utils.MessageUtils;
import com.mascara.electronicstoremanage.utils.SharedData;
import com.mascara.electronicstoremanage.view_model.auth.LoginCreateRequest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 5:57 CH
 * Filename  : LoginController
 */
public class LoginController {

    @FXML
    private TextField txtUserName;

    @FXML
    private Button btnLogin;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label labelName;
    @FXML
    private PasswordField txtPassword;

    @FXML
    public void initialize() {
        this.btnLogin.setOnAction(event -> {
            String email = txtUserName.getText().trim();
            String password = txtPassword.getText().trim();
            LoginCreateRequest request = LoginCreateRequest.builder()
                    .userName(email)
                    .password(password)
                    .build();

            boolean validLogin = validateLogin(email, password);
            if (!validLogin) {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.USER_NAME_PASSWORD_NOT_EMPTY);
            } else {
                boolean loginSuccess = AuthServiceImpl.getInstance().login(request);
                if (loginSuccess) {
                    Staff staffLogin = StaffServiceImpl.getInstance().getInfoByEmail(email).get();
                    SharedData.getInstance().setStaffCurrentLogin(staffLogin);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Hộp thoại thông báo");
                    alert.setHeaderText("Thông báo");
                    alert.setContentText("Đăng nhập thành công !");

                    ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    alert.getButtonTypes().setAll(buttonTypeOk);
                    alert.showAndWait();

                    if (alert.getResult() == buttonTypeOk) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/form/dashboard.fxml"));
                        try {
                            root = fxmlLoader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        DashboardController dashboardController = fxmlLoader.getController();
                        dashboardController.loadPage("panel_sale");

                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root, 1366, 766);
                        stage.setScene(scene);
                        stage.setTitle("Quản lý bán hàng thiết bị điện tử");
                        scene.getWindow().centerOnScreen();
                        stage.show();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Hộp thoại thông báo");
                    alert.setHeaderText("Thông báo");
                    alert.setContentText("Tên đăng nhập hoặc mật khẩu không chính xác !");

                    ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    alert.getButtonTypes().setAll(buttonTypeOk);
                    alert.showAndWait();
                }
            }
        });
    }

    private boolean validateLogin(String userName, String password) {
        if (userName.trim().isEmpty() || password.trim().isEmpty())
            return false;
        return true;
    }
}
