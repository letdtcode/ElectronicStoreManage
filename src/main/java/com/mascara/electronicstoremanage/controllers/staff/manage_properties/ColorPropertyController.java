package com.mascara.electronicstoremanage.controllers.staff.manage_properties;

import com.mascara.electronicstoremanage.services.color.ColorServiceImpl;
import com.mascara.electronicstoremanage.utils.AlertUtils;
import com.mascara.electronicstoremanage.utils.MessageUtils;
import com.mascara.electronicstoremanage.view_model.color.ColorCreateRequest;
import com.mascara.electronicstoremanage.view_model.color.ColorPagingRequest;
import com.mascara.electronicstoremanage.view_model.color.ColorUpdateRequest;
import com.mascara.electronicstoremanage.view_model.color.ColorViewModel;
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
 * Date      : 15/04/2024
 * Time      : 6:00 CH
 * Filename  : ColorPropertyController
 */
public class ColorPropertyController implements Initializable {
    @FXML
    private Button btnCreateColor;
    @FXML
    private Button btnUpdateColor;
    @FXML
    private Button btnDeleteColor;
    @FXML
    private TextField txtColorName;

    private ObservableList<ColorViewModel> colorViewModels;
    @FXML
    private TableColumn idColorColumn;
    @FXML
    private TableColumn colorNameColumn;
    @FXML
    private TableView<ColorViewModel> colorTableView;

    @FXML
    public void setOnActionCreateColor(ActionEvent actionEvent) {
        boolean isValid = validateData(txtColorName.getText());
        if (isValid) {
            ColorCreateRequest request = ColorCreateRequest.builder()
                    .colorName(txtColorName.getText())
                    .build();
            Long colorId = ColorServiceImpl.getInstance().insertColor(request);
            if (colorId == -1) {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_COLOR_NAME_DUPLICATED);
            } else {
                AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_CREATE_COLOR_SUCCESS);
            }
            retrieveAllColor();
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_DATA_NOT_VALID);
        }
    }

    private boolean validateData(String colorName) {
        boolean isValid = true;
        if (colorName == null || colorName.trim().isBlank()) {
            isValid = false;
        }
        return isValid;
    }

    @FXML
    public void setOnActionUpdateColor(ActionEvent actionEvent) {
        boolean isValid = validateData(txtColorName.getText());
        if (isValid && !colorTableView.getSelectionModel().isEmpty()) {
            ColorViewModel colorViewModel = colorTableView.getSelectionModel().getSelectedItem();
            ColorUpdateRequest request = ColorUpdateRequest.builder()
                    .id(colorViewModel.getId())
                    .colorName(txtColorName.getText().trim()).build();
            boolean success = ColorServiceImpl.getInstance().updateColor(request);
            if (success) {
                AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_UPDATE_COLOR_SUCCESS);
            } else {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_COLOR_NAME_DUPLICATED);
            }
            retrieveAllColor();
        } else if (colorTableView.getSelectionModel().isEmpty()) {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_SELECT_ROW);
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_DATA_NOT_VALID);
        }
    }

    @FXML
    public void setOnActionDeleteColor(ActionEvent actionEvent) {
        ColorViewModel colorViewModel = colorTableView.getSelectionModel().getSelectedItem();
        boolean deleteSuccess = ColorServiceImpl.getInstance().deleteColor(colorViewModel.getId());
        if (deleteSuccess) {
            AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_DELETE_COLOR_SUCCESS);
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_COLOR_CAN_NOT_DELETE);
        }
        retrieveAllColor();
    }

    public void retrieveAllColor() {
        ColorPagingRequest request = new ColorPagingRequest();
        List<ColorViewModel> colorList = ColorServiceImpl.getInstance().retrieveAllColor(request);
        colorViewModels = FXCollections.observableList(colorList);
        idColorColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        colorNameColumn.setCellValueFactory(new PropertyValueFactory<>("colorName"));
        colorTableView.setItems(colorViewModels);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retrieveAllColor();
        addListener();
    }

    private void addListener() {
        colorTableView.setRowFactory(param -> {
            TableRow<ColorViewModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    int rowIndex = colorTableView.getSelectionModel().getSelectedIndex();
                    ColorViewModel colorViewModel = colorTableView.getItems().get(rowIndex);
                    txtColorName.setText(colorViewModel.getColorName());
                }
            });
            return row;
        });
    }
}
