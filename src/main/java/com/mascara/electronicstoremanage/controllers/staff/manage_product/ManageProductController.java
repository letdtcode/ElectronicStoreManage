package com.mascara.electronicstoremanage.controllers.staff.manage_product;

import com.mascara.electronicstoremanage.enums.product.ProductStatusEnum;
import com.mascara.electronicstoremanage.enums.product.WarrantyPeriodUnitENum;
import com.mascara.electronicstoremanage.enums.product.WeightUnitEnum;
import com.mascara.electronicstoremanage.services.brand.BrandServiceImpl;
import com.mascara.electronicstoremanage.services.category.CategoryServiceImpl;
import com.mascara.electronicstoremanage.services.color.ColorServiceImpl;
import com.mascara.electronicstoremanage.services.feature.FeatureServiceImpl;
import com.mascara.electronicstoremanage.services.material.MaterialServiceImpl;
import com.mascara.electronicstoremanage.services.product.ProductServiceImpl;
import com.mascara.electronicstoremanage.utils.AlertUtils;
import com.mascara.electronicstoremanage.utils.FileHandleUtils;
import com.mascara.electronicstoremanage.utils.MessageUtils;
import com.mascara.electronicstoremanage.utils.Utillities;
import com.mascara.electronicstoremanage.view_model.brand.BrandPagingRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandViewModel;
import com.mascara.electronicstoremanage.view_model.category.CategoryCreateRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryPagingRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryUpdateRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryViewModel;
import com.mascara.electronicstoremanage.view_model.color.ColorPagingRequest;
import com.mascara.electronicstoremanage.view_model.color.ColorViewModel;
import com.mascara.electronicstoremanage.view_model.feature.FeaturePagingRequest;
import com.mascara.electronicstoremanage.view_model.feature.FeatureViewModel;
import com.mascara.electronicstoremanage.view_model.material.MaterialPagingRequest;
import com.mascara.electronicstoremanage.view_model.material.MaterialViewModel;
import com.mascara.electronicstoremanage.view_model.product.ProductCreateRequest;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    private TableColumn<ProductViewModel, Long> idProductColumn;
    @FXML
    private TableColumn<ProductViewModel, String> productNameColumn;
    @FXML
    private TableColumn<ProductViewModel, String> descriptionColumn;
    @FXML
    private TableColumn<ProductViewModel, String> priceImportColumn;
    @FXML
    private TableColumn<ProductViewModel, String> priceSaleColumn;
    @FXML
    private TableColumn<ProductViewModel, String> colorProductColumn;
    @FXML
    private TableColumn<ProductViewModel, String> brandColumn;
    @FXML
    private TableColumn<ProductViewModel, String> materialColumn;
    @FXML
    private TableColumn<ProductViewModel, String> categoryColumn;
    @FXML
    private TableColumn<ProductViewModel, Double> weightColumn;
    @FXML
    private TableColumn<ProductViewModel, String> originColumn;
    @FXML
    private TableColumn<ProductViewModel, String> warrantyPeriodColumn;
    @FXML
    private TableColumn<ProductViewModel, String> sizeColumn;
    @FXML
    private TableColumn<ProductViewModel, String> featureColumn;
    @FXML
    private TableColumn<ProductViewModel, String> statusProductColumn;
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
            ProductStatusEnum.ON_BUSINESS.getDisplay(),
            ProductStatusEnum.STOP_BUSINESS.getDisplay());

    private ObservableList<String> brandNameList = null;
    private ObservableList<String> categoryNameList = null;
    private ObservableList<String> materialNameList = null;
    private ObservableList<String> weightUnitList = FXCollections.observableArrayList(
            WeightUnitEnum.G.getDisplay(),
            WeightUnitEnum.KG.getDisplay());
    private ObservableList<String> warrantyPeriodUnitList = FXCollections.observableArrayList(
            WarrantyPeriodUnitENum.BY_YEAR.getDisplay(),
            WarrantyPeriodUnitENum.BY_MONTH.getDisplay());

    private File fileImage = null;
    private FileChooser fileChooser;
    @FXML
    private GridPane gridPanelColor;
    @FXML
    private GridPane gridPanelFeature;
    @FXML
    private StackPane nodeRoot;
    @FXML
    private TabPane tabPanel;
    @FXML
    private Pane categoryPanel;
    @FXML
    private Pane productPanel;
    @FXML
    private Text lblProductCode;

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
        priceImportColumn.setCellValueFactory(new PropertyValueFactory<>("importPriceShow"));
        priceSaleColumn.setCellValueFactory(new PropertyValueFactory<>("salePriceShow"));
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
        setUpUI();
        addListenerForEachRow();
    }

    private void addListenerForEachRow() {
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
                    txtPriceImport.setText(Utillities.getInstance().removeTrailingZeros(productViewModel.getImportPrice()));
                    txtPriceSale.setText(Utillities.getInstance().removeTrailingZeros(productViewModel.getSalePrice()));
                    txtQuantity.setText(Utillities.getInstance().removeTrailingZeros(productViewModel.getQuantity()));
                    cbbProductStatus.setValue(productViewModel.getStatus().getDisplay());
                    cbbBrandName.setValue(productViewModel.getBrandName());
                    cbbCategoryName.setValue(productViewModel.getCategoryName());
                    cbbMaterialName.setValue(productViewModel.getMaterialName());
                    txtSize.setText(productViewModel.getSize());
                    txtWeight.setText(Utillities.getInstance().removeTrailingZeros(productViewModel.getWeight()));
                    cbbWeightUnit.setValue(productViewModel.getWeightUnit().getDisplay());
                    txtOrigin.setText(productViewModel.getOrigin());
                    txtWarrantyPeriod.setText(productViewModel.getWarrantyPeriod().toString());
                    cbbWarrantyPeriodUnit.setValue(productViewModel.getWarrantyPeriodUnit().getDisplay());
                    textareaDescription.setText(productViewModel.getDescription());
                    lblProductCode.setText(productViewModel.getCode());

//                    Get Image show
                    fileImage = new File(productViewModel.getPathImage());
                    imgViewProduct.setImage(new Image(fileImage.toURI().toString(), 200, 175, true, true));

                    //                    Cheked for color list
                    List<CheckBox> colorCheckBoxs = gridPanelColor.getChildren().stream().map(node -> (CheckBox) node)
                            .collect(Collectors.toList());
                    List<String> colorNameList = productViewModel.getColorNameList();
                    List<CheckBox> filteredColorCheckBox = colorCheckBoxs.stream()
                            .filter(checkBox -> colorNameList.contains(checkBox.getText()))
                            .collect(Collectors.toList());
                    colorCheckBoxs.stream().map(checkBox -> {
                        if (filteredColorCheckBox.contains(checkBox)) {
                            checkBox.setSelected(true);
                        } else {
                            checkBox.setSelected(false);
                        }
                        return checkBox;
                    }).forEach(checkBox -> {
                    });

                    //                    Cheked for feature list
                    List<CheckBox> featureCheckBoxs = gridPanelFeature.getChildren().stream().map(node -> (CheckBox) node)
                            .collect(Collectors.toList());
                    List<String> featureNameList = productViewModel.getFeatureNameList();
                    List<CheckBox> filteredFeatureCheckBox = featureCheckBoxs.stream()
                            .filter(checkBox -> featureNameList.contains(checkBox.getText()))
                            .collect(Collectors.toList());
                    featureCheckBoxs.stream().map(checkBox -> {
                        if (filteredFeatureCheckBox.contains(checkBox)) {
                            checkBox.setSelected(true);
                        } else {
                            checkBox.setSelected(false);
                        }
                        return checkBox;
                    }).forEach(checkBox -> {
                    });
                }
            });
            return row;
        });
    }

    private void setUpUI() {
        cbbProductStatus.setItems(productStatusList);
        txtIdCategory.setEditable(false);
        txtIdProduct.setEditable(false);

//        Set up Color panel
        ColorPagingRequest requestColor = new ColorPagingRequest();
        List<ColorViewModel> colorViewModelList = ColorServiceImpl.getInstance().retrieveAllColor(requestColor);
        colorNameList = colorViewModelList.stream()
                .map(colorViewModel -> colorViewModel.getColorName()).collect(Collectors.toList());

        if (colorNameList.size() > 9) {
            int numRowOfGrid = (int) Math.ceil(colorNameList.size() / 3);
            for (int i = 3; i < numRowOfGrid; i++) {
                gridPanelColor.addRow(i);
            }
        }

        int columnColor = 0;
        int rowColor = 0;
        for (int i = 0; i < colorNameList.size(); i++) {
            CheckBox checkBox = new CheckBox();
            checkBox.setText(colorNameList.get(i));
            checkBox.setFont(Font.font("Segoe UI", 10));
            checkBox.setAlignment(Pos.CENTER);
            gridPanelColor.add(checkBox, columnColor, rowColor);
            columnColor = ++columnColor % 3;
            rowColor = columnColor == 0 ? ++rowColor : rowColor;
        }

//        Set up Feature panel

        FeaturePagingRequest requestFeature = new FeaturePagingRequest();
        List<FeatureViewModel> featureViewModelList = FeatureServiceImpl.getInstance().retrieveAllFeature(requestFeature);
        featureNameList = featureViewModelList.stream()
                .map(featureViewModel -> featureViewModel.getFeatureName()).collect(Collectors.toList());

        if (featureNameList.size() > 6) {
            int numRowOfGrid = (int) Math.ceil(featureNameList.size() / 2);
            for (int i = 3; i < numRowOfGrid; i++) {
                gridPanelFeature.addRow(i);
            }
        }

        int columnFeature = 0;
        int rowFeature = 0;
        for (int i = 0; i < featureNameList.size(); i++) {
            CheckBox checkBox = new CheckBox();
            checkBox.setText(featureNameList.get(i));
            checkBox.setFont(Font.font("Segoe UI", 10));
            checkBox.setAlignment(Pos.CENTER);
            gridPanelFeature.add(checkBox, columnFeature, rowFeature);
            columnFeature = ++columnFeature % 2;
            rowFeature = columnFeature == 0 ? ++rowFeature : rowFeature;
        }

        //        Set up combobox product status
        cbbProductStatus.setItems(productStatusList);
        cbbProductStatus.getSelectionModel().selectFirst();

        //        Set up combobox brand name
        BrandPagingRequest brandPagingRequest = new BrandPagingRequest();
        List<BrandViewModel> brandViewModels = BrandServiceImpl.getInstance().retrieveAllBrand(brandPagingRequest);
        brandNameList = FXCollections.observableArrayList(brandViewModels.stream()
                .map(BrandViewModel::getBrandName).collect(Collectors.toList()));
        cbbBrandName.setItems(brandNameList);
        cbbBrandName.getSelectionModel().selectFirst();

        //        Set up combobox category name
        CategoryPagingRequest categoryPagingRequest = new CategoryPagingRequest();
        List<CategoryViewModel> categoryViewModels = CategoryServiceImpl.getInstance().retrieveAllCategory(categoryPagingRequest);
        categoryNameList = FXCollections.observableArrayList(categoryViewModels.stream()
                .map(CategoryViewModel::getCategoryName).collect(Collectors.toList()));
        cbbCategoryName.setItems(categoryNameList);
        cbbCategoryName.getSelectionModel().selectFirst();

        //        Set up combobox material name
        MaterialPagingRequest materialPagingRequest = new MaterialPagingRequest();
        List<MaterialViewModel> materialViewModels = MaterialServiceImpl.getInstance().retrieveAllMaterial(materialPagingRequest);
        materialNameList = FXCollections.observableArrayList(materialViewModels.stream()
                .map(MaterialViewModel::getMaterialName).collect(Collectors.toList()));
        cbbMaterialName.setItems(materialNameList);
        cbbMaterialName.getSelectionModel().selectFirst();

        //        Set up cbb weight unit
        cbbWeightUnit.setItems(weightUnitList);
        cbbWeightUnit.getSelectionModel().selectFirst();

        //        Set up cbb warranty period unit
        cbbWarrantyPeriodUnit.setItems(warrantyPeriodUnitList);
        cbbWarrantyPeriodUnit.getSelectionModel().selectFirst();

        //        Set up category name filter
        cbbCategoryFilter.setItems(categoryNameList);
        cbbCategoryFilter.getSelectionModel().selectFirst();
        //        Set up product status filter
        cbbProductStatusFilter.setItems(productStatusList);
        cbbProductStatusFilter.getSelectionModel().selectFirst();
    }

    private void addListener() {
        tabPanel.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int selectedIndex = newValue.intValue();
            if (selectedIndex == 1) {
                CategoryPagingRequest categoryPagingRequest = new CategoryPagingRequest();
                List<CategoryViewModel> categoryViewModels = CategoryServiceImpl.getInstance().retrieveAllCategory(categoryPagingRequest);
                categoryNameList = FXCollections.observableArrayList(categoryViewModels.stream()
                        .map(CategoryViewModel::getCategoryName).collect(Collectors.toList()));
                cbbCategoryName.setItems(categoryNameList);
                cbbCategoryFilter.setItems(categoryNameList);
            }
        });

        Utillities.getInstance().setEventOnlyAcceptNumber(txtPriceImport);
        Utillities.getInstance().setEventOnlyAcceptNumber(txtPriceSale);
        Utillities.getInstance().setEventOnlyAcceptNumber(txtQuantity);
        Utillities.getInstance().setEventOnlyAcceptNumber(txtWeight);
        Utillities.getInstance().setEventOnlyAcceptNumber(txtWarrantyPeriod);
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
            if (!categoryTableView.getSelectionModel().isEmpty()) {
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
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_SELECT_ROW);
            }
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
        Utillities.getInstance().clearAllTextField(categoryPanel);
        retrieveAllCategory();
    }

    @FXML
    public void setOnActionReloadCategory(ActionEvent actionEvent) {
        Utillities.getInstance().clearAllTextField(categoryPanel);
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
                                        List<CheckBox> featureList,
                                        File fileImage) {
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
        }).forEach(checkBox -> {
        });
        featureList.stream().peek(featureCkb -> {
            if (featureCkb.isSelected())
                featureIsSelected.set(true);
        }).forEach(checkBox -> {
        });

        if (colorIsSelected.get() == false) {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_CREATE_PRODUCT_MUST_HAVE_COLOR);
            isValid = false;
        }

        if (featureIsSelected.get() == false) {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_CREATE_PRODUCT_MUST_HAVE_FEATURE);
            isValid = false;
        }
        if (fileImage == null) {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, "Hình ảnh không hợp lệ");
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
                gridPanelFeature.getChildren().stream().map(node -> (CheckBox) node).collect(Collectors.toList()),
                fileImage);
        if (isValid) {
            String pathImage = FileHandleUtils.getInstance().copyFile(
                    Utillities.getInstance().toSlug(txtProductName.getText()),
                    fileImage.getAbsolutePath());
            ProductCreateRequest request = ProductCreateRequest.builder()
                    .productName(txtProductName.getText())
                    .description(textareaDescription.getText())
                    .pathImage(pathImage)
                    .salePrice(Double.valueOf(txtPriceSale.getText()))
                    .importPrice(Double.valueOf(txtPriceImport.getText()))
                    .quantity(Integer.valueOf(txtQuantity.getText()))
                    .origin(txtOrigin.getText())
                    .weight(Double.valueOf(txtWeight.getText()))
                    .weightUnit(WeightUnitEnum.getEnumByDisplay(cbbWeightUnit.getValue().toString()))
                    .warrantyPeriod(Integer.valueOf(txtWarrantyPeriod.getText()))
                    .warrantyPeriodUnit(WarrantyPeriodUnitENum.getEnumByDisplay(cbbWarrantyPeriodUnit.getValue().toString()))
                    .size(txtSize.getText())
                    .brandName(cbbBrandName.getValue().toString())
                    .materialName(cbbMaterialName.getValue().toString())
                    .categoryName(cbbCategoryName.getValue().toString())
                    .status(ProductStatusEnum.getEnumByDisplay(cbbProductStatus.getValue().toString()))
                    .build();

//            Get list color name checked
            List<CheckBox> colorCheckBoxs = gridPanelColor.getChildren().stream().map(node -> (CheckBox) node)
                    .collect(Collectors.toList());
            List<CheckBox> filteredColorCheckBox = colorCheckBoxs.stream()
                    .filter(checkBox -> checkBox.isSelected())
                    .collect(Collectors.toList());
            List<String> colorNames = filteredColorCheckBox.stream().map(checkBox -> checkBox.getText())
                    .collect(Collectors.toList());

//            Get list feature name checked
            List<CheckBox> featureCheckBoxs = gridPanelFeature.getChildren().stream().map(node -> (CheckBox) node)
                    .collect(Collectors.toList());
            List<CheckBox> filteredFeatureCheckBox = featureCheckBoxs.stream()
                    .filter(checkBox -> checkBox.isSelected())
                    .collect(Collectors.toList());
            List<String> featureNames = filteredFeatureCheckBox.stream().map(checkBox -> checkBox.getText())
                    .collect(Collectors.toList());

            request.setColorNameList(colorNames);
            request.setFeatureNameList(featureNames);

            Long productId = ProductServiceImpl.getInstance().insertProduct(request);
            if (productId == -1) {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_PRODUCT_NAME_DUPLICATED);
            } else {
                AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_CREATE_PRODUCT_SUCCESS);
            }
            retrieveAllProduct();
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
                gridPanelFeature.getChildren().stream().map(node -> (CheckBox) node).collect(Collectors.toList()),
                fileImage
        );
        if (isValid && !productTableView.getSelectionModel().isEmpty()) {
            ProductViewModel productViewModel = productTableView.getSelectionModel().getSelectedItem();
            String pathImage = FileHandleUtils.getInstance().copyFile(
                    Utillities.getInstance().toSlug(txtProductName.getText()),
                    fileImage.getAbsolutePath());

            ProductUpdateRequest request = ProductUpdateRequest.builder()
                    .id(productViewModel.getId())
                    .productName(txtProductName.getText())
                    .description(textareaDescription.getText())
                    .pathImage(pathImage)
                    .salePrice(Double.valueOf(txtPriceSale.getText()))
                    .importPrice(Double.valueOf(txtPriceImport.getText()))
                    .quantity(Integer.valueOf(txtQuantity.getText()))
                    .origin(txtOrigin.getText())
                    .weight(Double.valueOf(txtWeight.getText()))
                    .weightUnit(WeightUnitEnum.getEnumByDisplay(cbbWeightUnit.getValue().toString()))
                    .warrantyPeriod(Integer.valueOf(txtWarrantyPeriod.getText()))
                    .warrantyPeriodUnit(WarrantyPeriodUnitENum.getEnumByDisplay(cbbWarrantyPeriodUnit.getValue().toString()))
                    .size(txtSize.getText())
                    .brandName(cbbBrandName.getValue().toString())
                    .materialName(cbbMaterialName.getValue().toString())
                    .categoryName(cbbCategoryName.getValue().toString())
                    .status(ProductStatusEnum.getEnumByDisplay(cbbProductStatus.getValue().toString()))
                    .build();

//            Get list color name checked
            List<CheckBox> colorCheckBoxs = gridPanelColor.getChildren().stream().map(node -> (CheckBox) node)
                    .collect(Collectors.toList());
            List<CheckBox> filteredColorCheckBox = colorCheckBoxs.stream()
                    .filter(checkBox -> checkBox.isSelected())
                    .collect(Collectors.toList());
            List<String> colorNames = filteredColorCheckBox.stream().map(checkBox -> checkBox.getText())
                    .collect(Collectors.toList());

//            Get list feature name checked
            List<CheckBox> featureCheckBoxs = gridPanelFeature.getChildren().stream().map(node -> (CheckBox) node)
                    .collect(Collectors.toList());
            List<CheckBox> filteredFeatureCheckBox = featureCheckBoxs.stream()
                    .filter(checkBox -> checkBox.isSelected())
                    .collect(Collectors.toList());
            List<String> featureNames = filteredFeatureCheckBox.stream().map(checkBox -> checkBox.getText())
                    .collect(Collectors.toList());

            request.setColorNameList(colorNames);
            request.setFeatureNameList(featureNames);

            boolean success = ProductServiceImpl.getInstance().updateProduct(request);
            if (success) {
                AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_UPDATE_PRODUCT_SUCCESS);
            } else {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_PRODUCT_NAME_DUPLICATED);
            }
            retrieveAllProduct();
        } else if (productTableView.getSelectionModel().isEmpty()) {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_SELECT_ROW);
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_DATA_NOT_VALID);
        }

    }

    @FXML
    public void setOnActionDeleteProduct(ActionEvent actionEvent) {
        ProductViewModel productViewModel = productTableView.getSelectionModel().getSelectedItem();
        boolean deleteSuccess = ProductServiceImpl.getInstance().deleteProduct(productViewModel.getId());
        if (deleteSuccess) {
            AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_DELETE_PRODUCT_SUCCESS);
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_PRODUCT_CAN_NOT_DELETE);
        }
        Utillities.getInstance().clearAllTextField(productPanel);
        Utillities.getInstance().unCheckedAllCheckBox(gridPanelColor);
        Utillities.getInstance().unCheckedAllCheckBox(gridPanelFeature);
        retrieveAllProduct();
    }

    @FXML
    public void setOnActionReloadProduct(ActionEvent actionEvent) {
        Utillities.getInstance().clearAllTextField(productPanel);
        Utillities.getInstance().unCheckedAllCheckBox(gridPanelColor);
        Utillities.getInstance().unCheckedAllCheckBox(gridPanelFeature);
        imgViewProduct.setImage(new Image("file:upload\\images\\default_image_product.png", 200, 175, true, true));
        retrieveAllProduct();
    }

    @FXML
    public void setOnActionChooseImage(ActionEvent actionEvent) {
        fileImage = fileChooser.showOpenDialog(nodeRoot.getScene().getWindow());
        if (fileImage != null) {
            imgViewProduct.setImage(new Image(fileImage.toURI().toString(), 200, 175, true, true));
        }
    }
}
