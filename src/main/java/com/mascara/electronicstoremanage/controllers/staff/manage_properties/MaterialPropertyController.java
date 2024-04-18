package com.mascara.electronicstoremanage.controllers.staff.manage_properties;

import com.mascara.electronicstoremanage.services.material.MaterialServiceImpl;
import com.mascara.electronicstoremanage.utils.AlertUtils;
import com.mascara.electronicstoremanage.utils.MessageUtils;
import com.mascara.electronicstoremanage.view_model.material.MaterialCreateRequest;
import com.mascara.electronicstoremanage.view_model.material.MaterialPagingRequest;
import com.mascara.electronicstoremanage.view_model.material.MaterialUpdateRequest;
import com.mascara.electronicstoremanage.view_model.material.MaterialViewModel;
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
 * Filename  : MaterialPropertyController
 */
public class MaterialPropertyController implements Initializable {
    @FXML
    private TableColumn idMaterialColumn;
    @FXML
    private TableColumn nameMaterialColumn;
    @FXML
    private Button btnAddMaterial;
    @FXML
    private Button btnUpdateMaterial;
    @FXML
    private Button btnDeleteMaterial;
    @FXML
    private TextField txtMaterialName;
    @FXML
    private TableView<MaterialViewModel> materialTableView;

    private ObservableList<MaterialViewModel> materialViewModels;

    @FXML
    public void setOnActionCreateMaterial(ActionEvent actionEvent) {
        boolean isValid = validateData(txtMaterialName.getText());
        if (isValid) {
            MaterialCreateRequest request = MaterialCreateRequest.builder()
                    .materialName(txtMaterialName.getText())
                    .build();
            Long materialId = MaterialServiceImpl.getInstance().insertMaterial(request);
            if (materialId == -1) {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_MATERIAL_NAME_DUPLICATED);
            } else {
                AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_CREATE_MATERIAL_SUCCESS);
            }
            retrieveAllMaterial();
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_DATA_NOT_VALID);
        }
    }

    @FXML
    public void setOnActionUpdateMaterial(ActionEvent actionEvent) {
        boolean isValid = validateData(txtMaterialName.getText());
        if (isValid && !materialTableView.getSelectionModel().isEmpty()) {
            MaterialViewModel materialViewModel = materialTableView.getSelectionModel().getSelectedItem();
            MaterialUpdateRequest request = MaterialUpdateRequest.builder()
                    .id(materialViewModel.getId())
                    .materialName(txtMaterialName.getText().trim()).build();
            boolean success = MaterialServiceImpl.getInstance().updateMaterial(request);
            if (success) {
                AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_UPDATE_MATERIAL_SUCCESS);
            } else {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_MATERIAL_NAME_DUPLICATED);
            }
            retrieveAllMaterial();
        } else if (materialTableView.getSelectionModel().isEmpty()) {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_SELECT_ROW);
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_DATA_NOT_VALID);
        }
    }

    @FXML
    public void setOnActionDeleteMaterial(ActionEvent actionEvent) {
        MaterialViewModel materialViewModel = materialTableView.getSelectionModel().getSelectedItem();
        boolean deleteSuccess = MaterialServiceImpl.getInstance().deleteMaterial(materialViewModel.getId());
        if (deleteSuccess) {
            AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_DELETE_MATERIAL_SUCCESS);
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_MATERIAL_CAN_NOT_DELETE);
        }
        retrieveAllMaterial();
    }

    private boolean validateData(String materialName) {
        boolean isValid = true;
        if (materialName == null || materialName.trim().isBlank()) {
            isValid = false;
        }
        return isValid;
    }

    public void retrieveAllMaterial() {
        MaterialPagingRequest request = new MaterialPagingRequest();
        List<MaterialViewModel> materialList = MaterialServiceImpl.getInstance().retrieveAllMaterial(request);
        materialViewModels = FXCollections.observableList(materialList);
        idMaterialColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameMaterialColumn.setCellValueFactory(new PropertyValueFactory<>("materialName"));
        materialTableView.setItems(materialViewModels);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retrieveAllMaterial();
        materialTableView.setRowFactory(param -> {
            TableRow<MaterialViewModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    int rowIndex = materialTableView.getSelectionModel().getSelectedIndex();
                    MaterialViewModel materialViewModel = materialTableView.getItems().get(rowIndex);
                    txtMaterialName.setText(materialViewModel.getMaterialName());
                }
            });
            return row;
        });
    }
}
