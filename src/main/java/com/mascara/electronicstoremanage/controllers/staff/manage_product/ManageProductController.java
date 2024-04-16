package com.mascara.electronicstoremanage.controllers.staff.manage_product;

import com.mascara.electronicstoremanage.enums.product.ProductStatusEnum;
import com.mascara.electronicstoremanage.services.category.CategoryServiceImpl;
import com.mascara.electronicstoremanage.services.product.ProductServiceImpl;
import com.mascara.electronicstoremanage.view_model.category.CategoryPagingRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryViewModel;
import com.mascara.electronicstoremanage.view_model.product.ProductPagingRequest;
import com.mascara.electronicstoremanage.view_model.product.ProductViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 5:59 CH
 * Filename  : ManageProductController
 */
public class ManageProductController implements Initializable {
    @FXML
    private TextField txtIdCategory;
    @FXML
    private TextField txtNameCategory;
    @FXML
    private Button btnAddCategory;
    @FXML
    private Button btnUpdateCategory;
    @FXML
    private Button btnDeleteCategory;
    @FXML
    private Button btnReloadCategory;
    @FXML
    private TableColumn idCategoryColumn;
    @FXML
    private TableColumn categoryNameColumn;
    @FXML
    private TextField txtSearchCategory;
    @FXML
    private TextField txtIdProduct;
    @FXML
    private TextField txtProductName;
    @FXML
    private TextField txtPriceImport;
    @FXML
    private TextField txtPriceSale;
    @FXML
    private TextField txtQuantity;
    @FXML
    private ComboBox cbbProductStatus;
    @FXML
    private ComboBox cbbBrandName;
    @FXML
    private Button btnAddProduct;
    @FXML
    private Button btnUpdateProduct;
    @FXML
    private Button btnDeleteProduct;
    @FXML
    private Button btnReloadProduct;
    @FXML
    private TextArea textareaDescription;
    @FXML
    private ComboBox cbbCategoryName;
    @FXML
    private ComboBox cbbMaterialName;
    @FXML
    private TextField txtSize;
    @FXML
    private TextField txtWeight;
    @FXML
    private ComboBox cbbWeightUnit;
    @FXML
    private TextField txtOrigin;
    @FXML
    private TextField txtWarrantyPeriod;
    @FXML
    private ComboBox cbbWarrantyPeriodUnit;
    @FXML
    private Pane panelLoadCkbColor;
    @FXML
    private Pane panelLoadCkbFeature;
    @FXML
    private Button btnFileChooser;
    @FXML
    private ImageView imgViewProduct;
    @FXML
    private TextField txtSearchProduct;
    @FXML
    private ComboBox cbbCategoryFilter;
    @FXML
    private ComboBox cbbProductStatusFilter;
    @FXML
    private TableColumn idProductColumn;
    @FXML
    private TableColumn productNameColumn;
    @FXML
    private TableColumn descriptionColumn;
    @FXML
    private TableColumn priceImportColumn;
    @FXML
    private TableColumn priceSaleColumn;
    @FXML
    private TableColumn colorProductColumn;
    @FXML
    private TableColumn brandColumn;
    @FXML
    private TableColumn materialColumn;
    @FXML
    private TableColumn categoryColumn;
    @FXML
    private TableColumn weightColumn;
    @FXML
    private TableColumn originColumn;
    @FXML
    private TableColumn warrantyPeriodColumn;
    @FXML
    private TableColumn sizeColumn;
    @FXML
    private TableColumn featureColumn;
    @FXML
    private TableColumn statusProductColumn;
    @FXML
    private TableView<CategoryViewModel> categoryTableView;
    @FXML
    private Button btnExportExcel;
    @FXML
    private TableView<ProductViewModel> productTableView;

    private ObservableList<CategoryViewModel> categoryViewModels;

    private ObservableList<ProductViewModel> productViewModels;

    private ObservableList<String> productStatusList = FXCollections.observableArrayList(
            ProductStatusEnum.ON_BUSINESS.getShowView(),
            ProductStatusEnum.STOP_BUSINESS.getShowView());

    public void retrieveAllCategory() {
        CategoryPagingRequest request = new CategoryPagingRequest();
        List<CategoryViewModel> categoryList = CategoryServiceImpl.getInstance().retrieveAllCategory(request);
        categoryViewModels = FXCollections.observableList(categoryList);
        idCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        categoryNameColumn.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        categoryTableView.setItems(categoryViewModels);
    }

    public void retrieveAllProduct() {
        ProductPagingRequest request = new ProductPagingRequest();
        List<ProductViewModel> productList = ProductServiceImpl.getInstance().retrieveAllProduct(request);
        productViewModels = FXCollections.observableList(productList);
        idProductColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceImportColumn.setCellValueFactory(new PropertyValueFactory<>("importPrice"));
        priceSaleColumn.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        colorProductColumn.setCellValueFactory(new PropertyValueFactory<>("colorNameListShow"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brandName"));
        materialColumn.setCellValueFactory(new PropertyValueFactory<>("materialName"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        originColumn.setCellValueFactory(new PropertyValueFactory<>("origin"));
        warrantyPeriodColumn.setCellValueFactory(new PropertyValueFactory<>("warrantyPeriod"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        featureColumn.setCellValueFactory(new PropertyValueFactory<>("featureNameListShow"));
        statusProductColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        productTableView.setItems(productViewModels);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retrieveAllCategory();
        retrieveAllProduct();

        cbbProductStatus.setItems(productStatusList);

        txtIdCategory.setEditable(false);
        txtIdProduct.setEditable(false);

        categoryTableView.setRowFactory(param -> {
            TableRow<CategoryViewModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    int rowIndex = categoryTableView.getSelectionModel().getSelectedIndex();
                    CategoryViewModel categoryViewModel = categoryTableView.getItems().get(rowIndex);
                    txtIdCategory.setText(categoryViewModel.getId().toString());
                    txtNameCategory.setText(categoryViewModel.getCategoryName());
                }
            });
            return row;
        });

        productTableView.setRowFactory(param -> {
            TableRow<ProductViewModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    int rowIndex = productTableView.getSelectionModel().getSelectedIndex();
                    ProductViewModel productViewModel = productTableView.getItems().get(rowIndex);
                    txtIdProduct.setText(productViewModel.getId().toString());
                    txtProductName.setText(productViewModel.getProductName());
                    txtPriceImport.setText(productViewModel.getImportPrice().toString());
                    txtPriceSale.setText(productViewModel.getSalePrice().toString());
                    txtQuantity.setText(productViewModel.getQuantity().toString());
                    cbbProductStatus.setValue(productViewModel.getStatus().getShowView());
                }
            });
            return row;
        });
    }

    @FXML
    public void setOnActionCreateCategory(ActionEvent actionEvent) {
    }

    @FXML
    public void setOnActionUpdateCategory(ActionEvent actionEvent) {
    }

    @FXML
    public void setOnActionDeleteCategory(ActionEvent actionEvent) {
    }

    @FXML
    public void setOnActionReloadCategory(ActionEvent actionEvent) {
    }
}
