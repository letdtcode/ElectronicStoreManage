package com.mascara.electronicstoremanage.controllers.staff.property_product;

import com.mascara.electronicstoremanage.services.brand.BrandServiceImpl;
import com.mascara.electronicstoremanage.utils.AlertUtils;
import com.mascara.electronicstoremanage.view_model.brand.BrandCreateRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandPagingRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandUpdateRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    }

    @FXML
    public void setOnActionCreateBrand(ActionEvent actionEvent) {
        BrandCreateRequest request = BrandCreateRequest.builder()
                .brandName(txtBrandName.getText())
                .build();
        Long brandId = BrandServiceImpl.getInstance().insertBrand(request);
        if (brandId == -1) {
            AlertUtils.showMessageWarning("Tên thương hiệu đã tồn tại");
        } else {
            AlertUtils.showMessageWarning("Thêm mới thương hiệu thành công");
        }
        retrieveAllBrand();
    }

    @FXML
    public void setOnActionUpdateBrand(ActionEvent actionEvent) {
        BrandViewModel brandViewModel = brandTableView.getSelectionModel().getSelectedItem();
        BrandUpdateRequest request = BrandUpdateRequest.builder()
                .id(brandViewModel.getId())
                .brandName(txtBrandName.getText().trim()).build();
        boolean success = BrandServiceImpl.getInstance().updateBrand(request);
        if(success) {
            AlertUtils.showMessageInfo("Cập nhật thương hiệu thành công");
        } else {
            AlertUtils.showMessageWarning("Tên thương hiệu đã tồn tại");
        }
        retrieveAllBrand();
    }

    @FXML
    public void setOnActionDeleteBrand(ActionEvent actionEvent) {
        BrandViewModel brandViewModel = brandTableView.getSelectionModel().getSelectedItem();
        BrandServiceImpl.getInstance().deleteBrand(brandViewModel.getId());
        retrieveAllBrand();
    }

    public void retrieveAllBrand() {
        BrandPagingRequest request = new BrandPagingRequest();
        List<BrandViewModel> brandList = BrandServiceImpl.getInstance().retrieveAllBrand(request);
//        log.warn(String.valueOf(brandList.size()));
        brandViewModels = FXCollections.observableList(brandList);
        idBrandColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        brandNameColumn.setCellValueFactory(new PropertyValueFactory<>("brandName"));
        brandTableView.setItems(brandViewModels);
    }
}
