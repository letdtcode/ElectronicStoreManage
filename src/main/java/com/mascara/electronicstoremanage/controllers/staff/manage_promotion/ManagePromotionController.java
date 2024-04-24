package com.mascara.electronicstoremanage.controllers.staff.manage_promotion;

import com.mascara.electronicstoremanage.enums.discount.DiscountStatus;
import com.mascara.electronicstoremanage.enums.discount.TypeDiscountEnum;
import com.mascara.electronicstoremanage.services.discount.DiscountServiceImpl;
import com.mascara.electronicstoremanage.services.product.ProductServiceImpl;
import com.mascara.electronicstoremanage.utils.AlertUtils;
import com.mascara.electronicstoremanage.utils.MessageUtils;
import com.mascara.electronicstoremanage.utils.Utillities;
import com.mascara.electronicstoremanage.view_model.discount.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 5:59 CH
 * Filename  : ManagePromotionController
 */
public class ManagePromotionController implements Initializable {
    @FXML
    private TextField txtCapaignName;
    @FXML
    private ComboBox cbbTypeDiscount;
    @FXML
    private TextField txtDiscountValue;
    @FXML
    private CheckBox ckbSelectAll;
    @FXML
    private TableView<ProductApplyViewModel> productApplyTableView;
    @FXML
    private TableColumn<DiscountViewModel, CheckBox> selectColumn;
    @FXML
    private TableColumn<DiscountViewModel, Long> idProductColumn;
    @FXML
    private TableColumn nameProductColumn;
    @FXML
    private TableColumn codeProductColumn;
    @FXML
    private ComboBox cbbDiscountStatus;
    @FXML
    private DatePicker dtpDateEnd;
    @FXML
    private DatePicker dtpDateStart;
    @FXML
    private TextArea txtDescription;
    @FXML
    private TableView<DiscountViewModel> discountTableView;
    @FXML
    private TableColumn idDiscountColumn;
    @FXML
    private TableColumn capaignNameColumn;
    @FXML
    private TableColumn discountValueColumn;
    @FXML
    private TableColumn dateStartColumn;
    @FXML
    private TableColumn dateEndColumn;
    @FXML
    private TableColumn descriptionColumn;
    @FXML
    private TableColumn statusColumn;

    private ObservableList<DiscountViewModel> discountViewModels;
    private ObservableList<ProductApplyViewModel> productApplyViewModels;

    private ObservableList<String> discountTypeList = FXCollections.observableArrayList(
            TypeDiscountEnum.PERCENT.getDisplay(),
            TypeDiscountEnum.CASH.getDisplay());

    private ObservableList<String> discountStatusList = FXCollections.observableArrayList(
            DiscountStatus.IS_APPLYING.getDisplay(),
            DiscountStatus.NOT_APPLY.getDisplay());
    @FXML
    private Button btnCreateDiscount;
    @FXML
    private Button btnUpdateDiscount;
    @FXML
    private Button btnDeleteDiscount;
    @FXML
    private Button btnReload;
    @FXML
    private Pane firstDiscountPanel;
    @FXML
    private Pane secondDiscountPanel;
    @FXML
    private TableColumn typeDiscountColumn;
    @FXML
    private TableColumn salePriceColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retrieveAllDiscount();
        retrieveAllProductForApply();
        setUpUI();
        addListener();
    }

    private void addListener() {
        discountTableView.setRowFactory(param -> {
            TableRow<DiscountViewModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    ckbSelectAll.setSelected(false);

                    DiscountViewModel discountViewModel = discountTableView.getSelectionModel().getSelectedItem();
                    txtCapaignName.setText(discountViewModel.getCapaignName());
                    cbbTypeDiscount.setValue(discountViewModel.getTypeDiscount().getDisplay());
                    txtDiscountValue.setText(Utillities.getInstance().removeTrailingZeros(discountViewModel.getDiscountValue()));
                    cbbDiscountStatus.setValue(discountViewModel.getStatus().getDisplay());
                    dtpDateStart.setValue(discountViewModel.getDateStart());
                    dtpDateEnd.setValue(discountViewModel.getDateEnd());
                    txtDescription.setText(discountViewModel.getDescription());
                    for (int i = 0; i < productApplyViewModels.size(); i++) {
                        Long id = idProductColumn.getCellObservableValue(i).getValue();
                        if (discountViewModel.getIdProductList().contains(id)) {
                            productApplyViewModels.get(i).getCheckBox().setSelected(true);
                        } else {
                            productApplyViewModels.get(i).getCheckBox().setSelected(false);
                        }
                    }
                }
            });
            return row;
        });

        ckbSelectAll.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (ckbSelectAll.isSelected()) {
                    for (ProductApplyViewModel model : productApplyViewModels) {
                        model.getCheckBox().setSelected(true);
                    }
                } else {
                    for (ProductApplyViewModel model : productApplyViewModels) {
                        model.getCheckBox().setSelected(false);
                    }
                }
            }
        });
        Utillities.getInstance().setEventOnlyAcceptNumber(txtDiscountValue);
    }

    private void retrieveAllProductForApply() {
        ProductApplyPagingRequest request = new ProductApplyPagingRequest();
        List<ProductApplyViewModel> productList = ProductServiceImpl.getInstance().retrieveAllProductApply(request);
        productApplyViewModels = FXCollections.observableList(productList);

        selectColumn.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        idProductColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameProductColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        codeProductColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        salePriceColumn.setCellValueFactory(new PropertyValueFactory<>("salePriceShow"));
        productApplyTableView.setItems(productApplyViewModels);
    }

    private void setUpUI() {
        cbbTypeDiscount.setItems(discountTypeList);
        cbbTypeDiscount.getSelectionModel().selectFirst();

        cbbDiscountStatus.setItems(discountStatusList);
        cbbDiscountStatus.getSelectionModel().selectFirst();
    }

    private void retrieveAllDiscount() {
        DiscountPagingRequest request = new DiscountPagingRequest();
        List<DiscountViewModel> brandList = DiscountServiceImpl.getInstance().retrieveAllDiscount(request);
        discountViewModels = FXCollections.observableList(brandList);
        idDiscountColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        capaignNameColumn.setCellValueFactory(new PropertyValueFactory<>("capaignName"));
        discountValueColumn.setCellValueFactory(new PropertyValueFactory<>("discountValueShow"));
        typeDiscountColumn.setCellValueFactory(new PropertyValueFactory<>("typeDiscount"));
        dateStartColumn.setCellValueFactory(new PropertyValueFactory<>("dateStart"));
        dateEndColumn.setCellValueFactory(new PropertyValueFactory<>("dateEnd"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        discountTableView.setItems(discountViewModels);
    }

    private boolean validateDataDiscount(String capaignName, Double discountValue, LocalDate dateStart, LocalDate dateEnd, String description) {
        boolean isValid = true;
        if (capaignName == null || capaignName.trim().isBlank() ||
                discountValue == null || discountValue <= 0 ||
                description == null || description.trim().isBlank()) {
            isValid = false;
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_DATA_NOT_VALID);
        }
        if (dateEnd.isBefore(dateStart)) {
            isValid = false;
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_DATE_RANGE_CHOOSE_NOT_VALID);
        }
//        Hình thức giảm giá là % và mức giảm giá trên 100 thì không hợp lệ
        if (cbbTypeDiscount.getValue().equals(TypeDiscountEnum.PERCENT.getDisplay()) && Double.parseDouble(txtDiscountValue.getText()) >= 100) {
            isValid = false;
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_DISCOUNT_PERCENT_CAN_NOT_OVER_100);
        }
        if (cbbTypeDiscount.getValue().equals(TypeDiscountEnum.CASH.getDisplay())) {
            for (ProductApplyViewModel productApplyViewModel : productApplyTableView.getItems()) {
                if (discountValue > productApplyViewModel.getSalePrice()) {
                    isValid = false;
                    AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_DISCOUNT_VALUE_CAN_NOT_OVER_PRICE_PRODUCT);
                    break;
                }
            }
        }
        return isValid;
    }

    private List<Long> getIdProductCheckedInTable() {
        List<Long> idProducts = new ArrayList<>();
        for (ProductApplyViewModel viewModel : productApplyViewModels) {
            if (viewModel.getCheckBox().isSelected())
                idProducts.add(viewModel.getId());
        }
        return idProducts;
    }

    @FXML
    public void setOnActionCreateDiscount(ActionEvent actionEvent) {
        boolean isValid = validateDataDiscount(txtCapaignName.getText(),
                Double.parseDouble(txtDiscountValue.getText()),
                dtpDateStart.getValue(),
                dtpDateEnd.getValue(),
                txtDescription.getText()
        );
        List<Long> idProducts = getIdProductCheckedInTable();
        if (isValid) {
            DiscountCreateRequest request = DiscountCreateRequest.builder()
                    .capaignName(txtCapaignName.getText().trim())
                    .typeDiscount(TypeDiscountEnum.getEnumByDisplay(cbbTypeDiscount.getValue().toString()))
                    .description(txtDescription.getText().trim())
                    .dateStart(dtpDateStart.getValue())
                    .dateEnd(dtpDateEnd.getValue())
                    .discountValue(Double.parseDouble(txtDiscountValue.getText()))
                    .status(DiscountStatus.getEnumByDisplay(cbbDiscountStatus.getValue().toString()))
                    .productIds(idProducts)
                    .build();
            boolean productsCanApply = true;
            if (idProducts.size() > 0) {
                productsCanApply = DiscountServiceImpl.getInstance()
                        .checkListProductCanApplyRangeDate(idProducts,
                                dtpDateStart.getValue(),
                                dtpDateEnd.getValue(),
                                null);
            }
            if (request.getStatus().equals(DiscountStatus.NOT_APPLY) || productsCanApply) {
                Long discountId = DiscountServiceImpl.getInstance().insertDiscount(request);
                if (discountId == -1) {
                    AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_HAS_ERROR_OCCURRED);
                } else {
                    AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_CREATE_DISCOUNT_SUCCESS);
                }
                retrieveAllDiscount();
            } else {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_PRODUCT_RANGE_DATE_OVERLAP);
            }
        }
    }

    @FXML
    public void setOnActionUpdateDiscount(ActionEvent actionEvent) {
        boolean isValid = validateDataDiscount(txtCapaignName.getText(),
                Double.parseDouble(txtDiscountValue.getText()),
                dtpDateStart.getValue(),
                dtpDateEnd.getValue(),
                txtDescription.getText());
        List<Long> idProducts = getIdProductCheckedInTable();
        if (isValid && !discountTableView.getSelectionModel().isEmpty()) {
            DiscountViewModel discountViewModel = discountTableView.getSelectionModel().getSelectedItem();
            DiscountUpdateRequest request = DiscountUpdateRequest.builder()
                    .id(discountViewModel.getId())
                    .capaignName(txtCapaignName.getText().trim())
                    .typeDiscount(TypeDiscountEnum.getEnumByDisplay(cbbTypeDiscount.getValue().toString()))
                    .description(txtDescription.getText().trim())
                    .dateStart(dtpDateStart.getValue())
                    .dateEnd(dtpDateEnd.getValue())
                    .discountValue(Double.parseDouble(txtDiscountValue.getText()))
                    .status(DiscountStatus.getEnumByDisplay(cbbDiscountStatus.getValue().toString()))
                    .productIds(idProducts)
                    .build();

            boolean productsCanApply = true;
            if (idProducts.size() > 0) {
                productsCanApply = DiscountServiceImpl.getInstance()
                        .checkListProductCanApplyRangeDate(idProducts,
                                dtpDateStart.getValue(),
                                dtpDateEnd.getValue(),
                                request.getId());
            }
            if (request.getStatus().equals(DiscountStatus.NOT_APPLY) || productsCanApply) {
                boolean success = DiscountServiceImpl.getInstance().updateDiscount(request);
                if (success) {
                    AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_UPDATE_DISCOUNT_SUCCESS);
                } else {
                    AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_HAS_ERROR_OCCURRED);
                }
                retrieveAllDiscount();
            } else {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_PRODUCT_RANGE_DATE_OVERLAP);
            }
        } else if (discountTableView.getSelectionModel().isEmpty()) {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_SELECT_ROW);
        }
    }

    @FXML
    public void setOnActionDeleteDiscount(ActionEvent actionEvent) {
        DiscountViewModel discountViewModel = discountTableView.getSelectionModel().getSelectedItem();
        boolean deleteSuccess = DiscountServiceImpl.getInstance().deleteDiscount(discountViewModel.getId());
        if (deleteSuccess) {
            AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_DELETE_DISCOUNT_SUCCESS);
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_HAS_ERROR_OCCURRED);
        }
        retrieveAllDiscount();
    }

    @FXML
    public void setOnActionReload(ActionEvent actionEvent) {
        Utillities.getInstance().clearAllTextField(firstDiscountPanel);
        Utillities.getInstance().clearAllTextField(secondDiscountPanel);
        Utillities.getInstance().unCheckedAllCheckBox(productApplyTableView);
        ckbSelectAll.setSelected(false);
        dtpDateStart.setValue(null);
        dtpDateEnd.setValue(null);
        retrieveAllDiscount();
    }
}
