package com.mascara.electronicstoremanage.controllers.auth;

import com.mascara.electronicstoremanage.controllers.staff.dashboard.DashboardController;
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
    private TextField txtPassword;

    @FXML
    private Button btnLogin;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize() {
        this.btnLogin.setOnAction(event -> {
            System.out.println(txtUserName.getText());
            LoginCreateRequest request = LoginCreateRequest.builder()
                    .userName(txtUserName.getText().trim())
                    .password(txtPassword.getText().trim())
                    .build();
            boolean loginSuccess = true;
//                    AuthServiceImpl.getInstance().login(request);
            if (loginSuccess) {
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
        });
    }
}
