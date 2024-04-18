package com.mascara.electronicstoremanage.controllers.staff.manage_properties;

import com.mascara.electronicstoremanage.services.brand.BrandServiceImpl;
import com.mascara.electronicstoremanage.utils.AlertUtils;
import com.mascara.electronicstoremanage.utils.MessageUtils;
import com.mascara.electronicstoremanage.view_model.brand.BrandCreateRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandPagingRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandUpdateRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandViewModel;
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
 * Filename  : BrandPropertyController
 */
public class BrandPropertyController implements Initializable {

    @FXML
    private TableView<BrandViewModel> brandTableView;
    @FXML
    private TableColumn<BrandViewModel, Long> idBrandColumn;
    @FXML
    private TableColumn<BrandViewModel, String> brandNameColumn;
    @FXML
    private Button btnCreateBrand;
    @FXML
    private Button btnUpdateBrand;
    @FXML
    private Button btnDeleteBrand;
    @FXML
    private TextField txtBrandName;

    private ObservableList<BrandViewModel> brandViewModels;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retrieveAllBrand();
        brandTableView.setRowFactory(param -> {
            TableRow<BrandViewModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    int rowIndex = brandTableView.getSelectionModel().getSelectedIndex();
                    BrandViewModel brandViewModel = brandTableView.getItems().get(rowIndex);
                    txtBrandName.setText(brandViewModel.getBrandName());
                }
            });
            return row;
        });
    }

    @FXML
    public void setOnActionCreateBrand(ActionEvent actionEvent) {
        boolean isValid = validateData(txtBrandName.getText());
        if (isValid) {
            BrandCreateRequest request = BrandCreateRequest.builder()
                    .brandName(txtBrandName.getText())
                    .build();
            Long brandId = BrandServiceImpl.getInstance().insertBrand(request);
            if (brandId == -1) {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_BRAND_NAME_DUPLICATED);
            } else {
                AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_CREATE_BRAND_SUCCESS);
            }
            retrieveAllBrand();
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_DATA_NOT_VALID);
        }
    }

    private boolean validateData(String brandName) {
        boolean isValid = true;
        if (brandName == null || brandName.trim().isBlank()) {
            isValid = false;
        }
        return isValid;
    }

    @FXML
    public void setOnActionUpdateBrand(ActionEvent actionEvent) {
        boolean isValid = validateData(txtBrandName.getText());
        if (isValid && !brandTableView.getSelectionModel().isEmpty()) {
            BrandViewModel brandViewModel = brandTableView.getSelectionModel().getSelectedItem();
            BrandUpdateRequest request = BrandUpdateRequest.builder()
                    .id(brandViewModel.getId())
                    .brandName(txtBrandName.getText().trim()).build();
            boolean success = BrandServiceImpl.getInstance().updateBrand(request);
            if (success) {
                AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_UPDATE_BRAND_SUCCESS);
            } else {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_BRAND_NAME_DUPLICATED);
            }
            retrieveAllBrand();
        } else if (brandTableView.getSelectionModel().isEmpty()) {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_SELECT_ROW);
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_DATA_NOT_VALID);
        }
    }

    @FXML
    public void setOnActionDeleteBrand(ActionEvent actionEvent) {
        BrandViewModel brandViewModel = brandTableView.getSelectionModel().getSelectedItem();
        boolean deleteSuccess = BrandServiceImpl.getInstance().deleteBrand(brandViewModel.getId());
        if (deleteSuccess) {
            AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_DELETE_BRAND_SUCCESS);
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_BRAND_CAN_NOT_DELETE);
        }
        retrieveAllBrand();
    }

    public void retrieveAllBrand() {
        BrandPagingRequest request = new BrandPagingRequest();
        List<BrandViewModel> brandList = BrandServiceImpl.getInstance().retrieveAllBrand(request);
        brandViewModels = FXCollections.observableList(brandList);
        idBrandColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        brandNameColumn.setCellValueFactory(new PropertyValueFactory<>("brandName"));
        brandTableView.setItems(brandViewModels);
    }
}
