package com.mascara.electronicstoremanage.controllers.staff.manage_product;

import com.mascara.electronicstoremanage.enums.product.ProductStatusEnum;
import com.mascara.electronicstoremanage.services.category.CategoryServiceImpl;
import com.mascara.electronicstoremanage.services.color.ColorServiceImpl;
import com.mascara.electronicstoremanage.services.feature.FeatureServiceImpl;
import com.mascara.electronicstoremanage.services.product.ProductServiceImpl;
import com.mascara.electronicstoremanage.utils.AlertUtils;
import com.mascara.electronicstoremanage.utils.MessageUtils;
import com.mascara.electronicstoremanage.view_model.category.CategoryCreateRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryPagingRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryUpdateRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryViewModel;
import com.mascara.electronicstoremanage.view_model.color.ColorPagingRequest;
import com.mascara.electronicstoremanage.view_model.color.ColorViewModel;
import com.mascara.electronicstoremanage.view_model.feature.FeaturePagingRequest;
import com.mascara.electronicstoremanage.view_model.feature.FeatureViewModel;
import com.mascara.electronicstoremanage.view_model.product.ProductPagingRequest;
import com.mascara.electronicstoremanage.view_model.product.ProductUpdateRequest;
import com.mascara.electronicstoremanage.view_model.product.ProductViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

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
    private List<String> colorNameList;
    private List<String> featureNameList;

    private ObservableList<String> productStatusList = FXCollections.observableArrayList(
            ProductStatusEnum.ON_BUSINESS.getShowView(),
            ProductStatusEnum.STOP_BUSINESS.getShowView());

    private File fileImage;
    private FileChooser fileChooser;
    @FXML
    private GridPane gridPanelColor;
    @FXML
    private GridPane gridPanelFeature;
    @FXML
    private StackPane nodeRoot;

    public ManageProductController() {
        fileChooser = new FileChooser();
    }

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
        addListener();
        retrieveAllCategory();
        retrieveAllProduct();
        setUpDataColor();
        setUpDataFeature();

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
                    cbbBrandName.setValue(productViewModel.getCategoryName());
                    cbbCategoryName.setValue(productViewModel.getCategoryName());
                    cbbMaterialName.setValue(productViewModel.getMaterialName());
                    txtSize.setText(productViewModel.getSize());
                    txtWeight.setText(productViewModel.getWeight().toString());
                    cbbWarrantyPeriodUnit.setValue(productViewModel.getWeightUnit().getDisplay());
                    txtOrigin.setText(productViewModel.getOrigin());
                    txtWarrantyPeriod.setText(productViewModel.getWarrantyPeriod().toString());
                    cbbWarrantyPeriodUnit.setValue(productViewModel.getWarrantyPeriodUnit().getDisplay());
                }
            });
            return row;
        });
    }

    private void addListener() {
        txtPriceImport.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtPriceImport.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        txtPriceSale.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtPriceSale.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        txtQuantity.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtQuantity.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        txtWeight.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtWeight.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        txtWarrantyPeriod.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtWarrantyPeriod.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    private void setUpDataColor() {
        ColorPagingRequest request = new ColorPagingRequest();
        List<ColorViewModel> colorViewModelList = ColorServiceImpl.getInstance().retrieveAllColor(request);
        colorNameList = colorViewModelList.stream()
                .map(colorViewModel -> colorViewModel.getColorName()).collect(Collectors.toList());

        if (colorNameList.size() > 9) {
            int numRowOfGrid = (int) Math.ceil(colorNameList.size() / 3);
            for (int i = 3; i < numRowOfGrid; i++) {
                gridPanelColor.addRow(i);
            }
        }

        int column = 0;
        int row = 0;
        for (int i = 0; i < colorNameList.size(); i++) {
            CheckBox checkBox = new CheckBox();
            checkBox.setText(colorNameList.get(i));
            checkBox.setFont(Font.font("Segoe UI", 10));
            checkBox.setAlignment(Pos.CENTER);
            gridPanelColor.add(checkBox, column, row);
            column = ++column % 3;
            row = column == 0 ? ++row : row;
        }
    }

    private void setUpDataFeature() {
        FeaturePagingRequest request = new FeaturePagingRequest();
        List<FeatureViewModel> featureViewModelList = FeatureServiceImpl.getInstance().retrieveAllFeature(request);
        featureNameList = featureViewModelList.stream()
                .map(featureViewModel -> featureViewModel.getFeatureName()).collect(Collectors.toList());

        if (featureNameList.size() > 6) {
            int numRowOfGrid = (int) Math.ceil(featureNameList.size() / 2);
            for (int i = 3; i < numRowOfGrid; i++) {
                gridPanelFeature.addRow(i);
            }
        }

        int column = 0;
        int row = 0;
        for (int i = 0; i < featureNameList.size(); i++) {
            CheckBox checkBox = new CheckBox();
            checkBox.setText(featureNameList.get(i));
            checkBox.setFont(Font.font("Segoe UI", 10));
            checkBox.setAlignment(Pos.CENTER);
            gridPanelFeature.add(checkBox, column, row);
            column = ++column % 2;
            row = column == 0 ? ++row : row;
        }
    }

    @FXML
    public void setOnActionCreateCategory(ActionEvent actionEvent) {
        boolean isValid = validateDataCategory(txtNameCategory.getText());
        if (isValid) {
            CategoryCreateRequest request = CategoryCreateRequest.builder()
                    .categoryName(txtNameCategory.getText())
                    .build();
            Long categoryId = CategoryServiceImpl.getInstance().insertCategory(request);
            if (categoryId == -1) {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_CATEGORY_NAME_DUPLICATED);
            } else {
                AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_CREATE_CATEGORY_SUCCESS);
            }
            retrieveAllCategory();
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_DATA_NOT_VALID);
        }
    }

    @FXML
    public void setOnActionUpdateCategory(ActionEvent actionEvent) {
        boolean isValid = validateDataCategory(txtNameCategory.getText());
        if (isValid) {
            CategoryViewModel categoryViewModel = categoryTableView.getSelectionModel().getSelectedItem();
            CategoryUpdateRequest request = CategoryUpdateRequest.builder()
                    .id(categoryViewModel.getId())
                    .categoryName(txtNameCategory.getText().trim()).build();
            boolean success = CategoryServiceImpl.getInstance().updateCategory(request);
            if (success) {
                AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_UPDATE_CATEGORY_SUCCESS);
            } else {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_CATEGORY_NAME_DUPLICATED);
            }
            retrieveAllCategory();
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_DATA_NOT_VALID);
        }
    }

    @FXML
    public void setOnActionDeleteCategory(ActionEvent actionEvent) {
        CategoryViewModel categoryViewModel = categoryTableView.getSelectionModel().getSelectedItem();
        boolean deleteSuccess = CategoryServiceImpl.getInstance().deleteCategory(categoryViewModel.getId());
        if (deleteSuccess) {
            AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_DELETE_CATEGORY_SUCCESS);
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_CATEGORY_CAN_NOT_DELETE);
        }
        retrieveAllCategory();
    }

    @FXML
    public void setOnActionReloadCategory(ActionEvent actionEvent) {
        retrieveAllCategory();
    }

    private boolean validateDataCategory(String categoryName) {
        boolean isValid = true;
        if (categoryName == null || categoryName.trim().isBlank()) {
            isValid = false;
        }
        return isValid;
    }

    private boolean validateDataProduct(String productName,
                                        Double priceImport,
                                        Double priceSale,
                                        Integer quantity,
                                        String size,
                                        Float weight,
                                        String origin,
                                        Integer timePeriod,
                                        String description,
                                        List<CheckBox> colorList,
                                        List<CheckBox> featureList) {
        boolean isValid = true;
        AtomicBoolean colorIsSelected = new AtomicBoolean(false);
        AtomicBoolean featureIsSelected = new AtomicBoolean(false);
        if (productName == null || productName.trim().isBlank()
                || priceImport == null || priceSale == null ||
                quantity == null || size == null || weight == null ||
                origin == null || timePeriod == null || description == null) {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_DATA_NOT_VALID);
            isValid = false;
        }
        if (priceImport >= priceSale) {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED,
                    MessageUtils.WARNING_CREATE_PRODUCT_IMPORT_PRICE_MUST_LESS_THAN_EXPORT_PRICE);
            isValid = false;
        }
        colorList.stream().peek(colorCkb -> {
            if (colorCkb.isSelected())
                colorIsSelected.set(true);
        });
        featureList.stream().peek(featureCkb -> {
            if (featureCkb.isSelected())
                featureIsSelected.set(true);
        });

        if (colorIsSelected.get() == false) {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_CREATE_PRODUCT_MUST_HAVE_COLOR);
            isValid = false;
        }

        if (featureIsSelected.get() == false) {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_CREATE_PRODUCT_MUST_HAVE_FEATURE);
            isValid = false;
        }
        return isValid;
    }

    @FXML
    public void setOnActionCreateProduct(ActionEvent actionEvent) {
        boolean isValid = validateDataProduct(txtProductName.getText(),
                Double.valueOf(txtPriceImport.getText().trim()),
                Double.valueOf(txtPriceSale.getText().trim()),
                Integer.parseInt(txtQuantity.getText().trim()),
                txtSize.getText(), Float.valueOf(txtWeight.getText().trim()),
                txtOrigin.getText(),
                Integer.parseInt(txtWarrantyPeriod.getText()),
                textareaDescription.getText(),
                gridPanelColor.getChildren().stream().map(node -> (CheckBox) node).collect(Collectors.toList()),
                gridPanelFeature.getChildren().stream().map(node -> (CheckBox) node).collect(Collectors.toList())
        );
        if (isValid) {
            CategoryCreateRequest request = CategoryCreateRequest.builder()
                    .categoryName(txtNameCategory.getText())
                    .build();
            Long categoryId = CategoryServiceImpl.getInstance().insertCategory(request);
            if (categoryId == -1) {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_CATEGORY_NAME_DUPLICATED);
            } else {
                AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_CREATE_CATEGORY_SUCCESS);
            }
            retrieveAllCategory();
        }
    }

    @FXML
    public void setOnActionUpdateProduct(ActionEvent actionEvent) {
        boolean isValid = validateDataProduct(txtProductName.getText(),
                Double.valueOf(txtPriceImport.getText().trim()),
                Double.valueOf(txtPriceSale.getText().trim()),
                Integer.parseInt(txtQuantity.getText().trim()),
                txtSize.getText(), Float.valueOf(txtWeight.getText().trim()),
                txtOrigin.getText(),
                Integer.parseInt(txtWarrantyPeriod.getText()),
                textareaDescription.getText(),
                gridPanelColor.getChildren().stream().map(node -> (CheckBox) node).collect(Collectors.toList()),
                gridPanelFeature.getChildren().stream().map(node -> (CheckBox) node).collect(Collectors.toList())
        );
        if (isValid) {
            ProductViewModel productViewModel = productTableView.getSelectionModel().getSelectedItem();
            ProductUpdateRequest request = ProductUpdateRequest.builder()
                    .id(productViewModel.getId())
                    .productName(txtProductName.getText())
                    .description(textareaDescription.getText())
                    .categoryName(txtNameCategory.getText().trim()).build();
            boolean success = CategoryServiceImpl.getInstance().updateCategory(request);
            if (success) {
                AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_UPDATE_CATEGORY_SUCCESS);
            } else {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_CATEGORY_NAME_DUPLICATED);
            }
            retrieveAllCategory();
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_DATA_NOT_VALID);
        }
    }

    @FXML
    public void setOnActionDeleteProduct(ActionEvent actionEvent) {
    }

    @FXML
    public void setOnActionReloadProduct(ActionEvent actionEvent) {
    }

    @FXML
    public void setOnActionChooseImage(ActionEvent actionEvent) {
        fileImage = fileChooser.showOpenDialog(nodeRoot.getScene().getWindow());
        if (fileImage != null) {
            imgViewProduct.setImage(new Image(fileImage.toURI().toString(), 200, 175, true, true));
        }
    }
}
