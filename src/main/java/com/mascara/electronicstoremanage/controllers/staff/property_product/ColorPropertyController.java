package com.mascara.electronicstoremanage.controllers.staff.property_product;

import com.mascara.electronicstoremanage.services.color.ColorServiceImpl;
import com.mascara.electronicstoremanage.utils.AlertUtils;
import com.mascara.electronicstoremanage.view_model.color.ColorCreateRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:00 CH
 * Filename  : ColorPropertyController
 */
public class ColorPropertyController {
    @FXML
    private Button btnCreateColor;
    @FXML
    private Button btnUpdateColor;
    @FXML
    private Button btnDeleteColor;
    @FXML
    private TextField txtColorName;

    @FXML
    public void setOnActionCreateColor(ActionEvent actionEvent) {
        boolean isValid = validateData(txtColorName.getText());
        if (isValid) {
            ColorCreateRequest request = ColorCreateRequest.builder()
                    .colorName(txtColorName.getText())
                    .build();
            Long colorId = ColorServiceImpl.getInstance().insertColor(request);
            if (colorId == -1) {
                AlertUtils.showMessageWarning("Màu sắc đã tồn tại");
            } else {
                AlertUtils.showMessageInfo("Thêm mới màu sắc thành công");
            }
        }
    }

    private boolean validateData(String colorName) {
        boolean isValid = true;
        if (colorName == null || colorName.trim().isBlank()) {
            isValid = false;
        }
        return isValid;
    }
}
