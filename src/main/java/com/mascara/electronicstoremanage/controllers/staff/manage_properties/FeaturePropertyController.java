package com.mascara.electronicstoremanage.controllers.staff.manage_properties;

import com.mascara.electronicstoremanage.services.feature.FeatureServiceImpl;
import com.mascara.electronicstoremanage.utils.AlertUtils;
import com.mascara.electronicstoremanage.utils.MessageUtils;
import com.mascara.electronicstoremanage.view_model.feature.FeatureCreateRequest;
import com.mascara.electronicstoremanage.view_model.feature.FeaturePagingRequest;
import com.mascara.electronicstoremanage.view_model.feature.FeatureUpdateRequest;
import com.mascara.electronicstoremanage.view_model.feature.FeatureViewModel;
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
 * Filename  : FeaturePropertyController
 */
public class FeaturePropertyController implements Initializable {
    @FXML
    private TableColumn idFeatureColumn;
    @FXML
    private TableColumn nameFeatureColumn;
    @FXML
    private Button btnAddFeature;
    @FXML
    private Button btnUpdateFeature;
    @FXML
    private Button btnDeleteFeature;
    @FXML
    private TextField txtFeatureName;
    @FXML
    private TableView<FeatureViewModel> featureTableView;

    private ObservableList<FeatureViewModel> featureViewModels;

    @FXML
    public void setOnActionCreateFeature(ActionEvent actionEvent) {
        boolean isValid = validateData(txtFeatureName.getText());
        if (isValid) {
            FeatureCreateRequest request = FeatureCreateRequest.builder()
                    .featureName(txtFeatureName.getText())
                    .build();
            Long featureId = FeatureServiceImpl.getInstance().insertFeature(request);
            if (featureId == -1) {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_FEATURE_NAME_DUPLICATED);
            } else {
                AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_CREATE_FEATURE_SUCCESS);
            }
            retrieveAllFeature();
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_DATA_NOT_VALID);
        }
    }

    private boolean validateData(String featureName) {
        boolean isValid = true;
        if (featureName == null || featureName.trim().isBlank()) {
            isValid = false;
        }
        return isValid;
    }

    public void retrieveAllFeature() {
        FeaturePagingRequest request = new FeaturePagingRequest();
        List<FeatureViewModel> featureList = FeatureServiceImpl.getInstance().retrieveAllFeature(request);
        featureViewModels = FXCollections.observableList(featureList);
        idFeatureColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameFeatureColumn.setCellValueFactory(new PropertyValueFactory<>("featureName"));
        featureTableView.setItems(featureViewModels);
    }

    @FXML
    public void setOnActionUpdateFeature(ActionEvent actionEvent) {
        boolean isValid = validateData(txtFeatureName.getText());
        if (isValid) {
            FeatureViewModel featureViewModel = featureTableView.getSelectionModel().getSelectedItem();
            FeatureUpdateRequest request = FeatureUpdateRequest.builder()
                    .id(featureViewModel.getId())
                    .featureName(txtFeatureName.getText().trim()).build();
            boolean success = FeatureServiceImpl.getInstance().updateFeature(request);
            if (success) {
                AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_UPDATE_FEATURE_SUCCESS);
            } else {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_FEATURE_NAME_DUPLICATED);
            }
            retrieveAllFeature();
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_DATA_NOT_VALID);
        }
    }

    @FXML
    public void setOnActionDeleteFeature(ActionEvent actionEvent) {
        FeatureViewModel featureViewModel = featureTableView.getSelectionModel().getSelectedItem();
        boolean deleteSuccess = FeatureServiceImpl.getInstance().deleteFeature(featureViewModel.getId());
        if (deleteSuccess) {
            AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_DELETE_FEATURE_SUCCESS);
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_FEATURE_CAN_NOT_DELETE);
        }
        retrieveAllFeature();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retrieveAllFeature();
        featureTableView.setRowFactory(param -> {
            TableRow<FeatureViewModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    int rowIndex = featureTableView.getSelectionModel().getSelectedIndex();
                    FeatureViewModel featureViewModel = featureTableView.getItems().get(rowIndex);
                    txtFeatureName.setText(featureViewModel.getFeatureName());
                }
            });
            return row;
        });
    }
}
