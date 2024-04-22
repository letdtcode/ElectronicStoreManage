package com.mascara.electronicstoremanage.controllers.staff.sale;

import com.mascara.electronicstoremanage.enums.order.ModeOfDeliveryEnum;
import com.mascara.electronicstoremanage.enums.order.ModeOfPaymentEnum;
import com.mascara.electronicstoremanage.enums.order.OrderStatusEnum;
import com.mascara.electronicstoremanage.services.category.CategoryServiceImpl;
import com.mascara.electronicstoremanage.services.order.OrderServiceImpl;
import com.mascara.electronicstoremanage.services.order_item.OrderItemServiceImpl;
import com.mascara.electronicstoremanage.services.product.ProductServiceImpl;
import com.mascara.electronicstoremanage.utils.AlertUtils;
import com.mascara.electronicstoremanage.utils.FXMLLoaderUtils;
import com.mascara.electronicstoremanage.utils.MessageUtils;
import com.mascara.electronicstoremanage.utils.StageRequestUtils;
import com.mascara.electronicstoremanage.view_model.category.CategoryPagingRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryViewModel;
import com.mascara.electronicstoremanage.view_model.order.OrderPagingRequest;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemCreateRequest;
import com.mascara.electronicstoremanage.view_model.product.ProductPagingRequest;
import com.mascara.electronicstoremanage.view_model.sale.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 18/04/2024
 * Time      : 11:43 CH
 * Filename  : ManageSaleController
 */
public class ManageSaleController implements Initializable {
    @FXML
    private TableView<OrderWaitingViewModel> orderWaitingTableView;
    @FXML
    private Button btnAddToCard;
    @FXML
    private TableView<ProductSaleViewModel> productTableView;
    @FXML
    private Pane panelCamera;
    @FXML
    private Text lblIdOrder;
    @FXML
    private Text lblTotalBill;
    @FXML
    private Text lblDiscountMoney;
    @FXML
    private Text lblTotalPay;
    @FXML
    private TextField txtCustomerGive;
    @FXML
    private Text lblChangeMoney;
    @FXML
    private Text lblIdCustomer;
    @FXML
    private Text lblNameCustomer;
    @FXML
    private Button btnChangeCustomer;
    @FXML
    private Button btnCheckOut;
    @FXML
    private TextArea textareaNote;
    @FXML
    private Button btnCreateOrder;
    @FXML
    private ComboBox cbbPayment;
    @FXML
    private ComboBox cbbDelivery;
    @FXML
    private Button btnCancelOrder;
    @FXML
    private Button btnReloadOrder;
    @FXML
    private TableView<CardItemViewModel> cardItemsTableView;
    @FXML
    private Button btnDeleteCardItem;
    @FXML
    private Button btnDelteAllCardItem;
    @FXML
    private TableColumn orderIdColumn;
    @FXML
    private TableColumn dateCreateOrderColumn;
    @FXML
    private TableColumn nameStaffOrderColumn;
    @FXML
    private TableColumn nameCustomerOrderColumn;
    @FXML
    private TableColumn idProductColumn;
    @FXML
    private TableColumn nameProductColumn;
    @FXML
    private TableColumn priceSaleColumn;
    @FXML
    private TableColumn discountValueColumn;
    @FXML
    private TableColumn colorColumn;
    @FXML
    private TableColumn originColumn;
    @FXML
    private TableColumn quantityColumn;
    @FXML
    private TableColumn idProductInCartColumn;
    @FXML
    private TableColumn nameProductInCartColumn;
    @FXML
    private TableColumn priceSaleInCartColumn;
    @FXML
    private TableColumn quantityProductInCartColumn;

    private ObservableList<ProductSaleViewModel> productSaleViewModels;
    private ObservableList<OrderWaitingViewModel> orderWaitingViewModels;
    private ObservableList<CardItemViewModel> cardItemViewModels;
    private ObservableList<String> categoryNameList = null;

    private ObservableList<String> paymentNameList = FXCollections.observableArrayList(
            ModeOfPaymentEnum.CASH.getDisplay(),
            ModeOfPaymentEnum.CARD_SWIPE.getDisplay(),
            ModeOfPaymentEnum.BANK_TRANSFER.getDisplay());

    private ObservableList<String> deliveryNameList = FXCollections.observableArrayList(
            ModeOfDeliveryEnum.DIRECT_SALE.getDisplay(),
            ModeOfDeliveryEnum.OTHER.getDisplay());
    @FXML
    private AnchorPane anchorPanelSale;
    @FXML
    private Button btnReloadCustomerData;
    @FXML
    private Button btnDeleteCardItem1;
    @FXML
    private TextField txtSearchProduct;
    @FXML
    private ComboBox cbbCategoryNameFilter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retrieveAllProduct();
        retrieveAllOrderWaiting();
        retrieveAllCardItem();
        setUpUI();
        addListener();
    }

    private void setUpUI() {
        btnCreateOrder.setDisable(false);
        btnReloadCustomerData.setDisable(true);
        btnChangeCustomer.setDisable(true);
        btnAddToCard.setDisable(true);
        btnCancelOrder.setDisable(true);

        CategoryPagingRequest request = new CategoryPagingRequest();
        List<CategoryViewModel> categoryViewModels = CategoryServiceImpl.getInstance().retrieveAllCategory(request);
        List<String> categoryNames = categoryViewModels.stream()
                .map(CategoryViewModel::getCategoryName).collect(Collectors.toList());
        categoryNames.add(0, "Tất cả");
        categoryNameList = FXCollections.observableArrayList(categoryNames);

        cbbCategoryNameFilter.setItems(categoryNameList);
        cbbCategoryNameFilter.getSelectionModel().selectFirst();

        cbbPayment.setItems(paymentNameList);
        cbbDelivery.setItems(deliveryNameList);

        cbbPayment.getSelectionModel().selectFirst();
        cbbDelivery.getSelectionModel().selectFirst();
    }

    private void retrieveAllCardItem() {
        cardItemViewModels = FXCollections.observableArrayList();
        idProductInCartColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        nameProductInCartColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        priceSaleInCartColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        quantityProductInCartColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        cardItemsTableView.setItems(cardItemViewModels);
    }

    private void reloadDataCartItem(Long orderId) {
        CardItemPagingRequest request = new CardItemPagingRequest();
        request.setCondition(" orderId = " + orderId);
        List<CardItemViewModel> cardItemList =
                OrderItemServiceImpl.getInstance().retrieveAllCartItem(orderId, request);
        cardItemViewModels = FXCollections.observableArrayList(cardItemList);
        cardItemsTableView.setItems(cardItemViewModels);
    }

    private void addListener() {
        orderWaitingTableView.setRowFactory(param -> {
            TableRow<OrderWaitingViewModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    btnCreateOrder.setDisable(true);
                    btnReloadCustomerData.setDisable(false);
                    btnChangeCustomer.setDisable(false);
                    btnAddToCard.setDisable(false);
                    btnCancelOrder.setDisable(false);
                    int rowIndex = orderWaitingTableView.getSelectionModel().getSelectedIndex();
                    OrderWaitingViewModel orderWaitingViewModel = orderWaitingTableView.getItems().get(rowIndex);
                    reloadDataCartItem(orderWaitingViewModel.getId());
                }
            });
            return row;
        });

        //        search filter event
        FilteredList<ProductSaleViewModel> filteredList = new FilteredList<>(productSaleViewModels, b -> true);
        txtSearchProduct.textProperty().addListener((observable, oldValue, newValue) -> {
            searchAndFilterProductSale(newValue, cbbCategoryNameFilter.getValue().toString(),filteredList);
        });

        //        filter sex and status
        cbbCategoryNameFilter.valueProperty().addListener((observable, oldValue, newValue) -> {
                    String categoryNameSelected = (String) cbbCategoryNameFilter.getSelectionModel().getSelectedItem();
                    searchAndFilterProductSale(txtSearchProduct.getText().trim(), categoryNameSelected, filteredList);
                }
        );

        SortedList<ProductSaleViewModel> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(productTableView.comparatorProperty());
        productTableView.setItems(sortedList);
    }

    private void searchAndFilterProductSale(String newValueTextField, String categoryNameFilter,
                                            FilteredList<ProductSaleViewModel> filteredList) {
        filteredList.setPredicate(productSaleViewModel -> {
            boolean resultSearch = searchByIdAndProductName(newValueTextField, productSaleViewModel);
            boolean resultFilterCategoryName = filterByCategoryName(categoryNameFilter, productSaleViewModel);
            return resultSearch && resultFilterCategoryName;
        });
    }

    private boolean searchByIdAndProductName(String newValueTextField, ProductSaleViewModel productSaleViewModel) {
        if (newValueTextField.isEmpty() || newValueTextField.isBlank() || newValueTextField == null)
            return true;
        String searchKeyword = newValueTextField.toLowerCase();

        if (productSaleViewModel.getId().toString().equals(searchKeyword))
            return true;
        else if (productSaleViewModel.getProductName().toLowerCase().contains(searchKeyword))
            return true;
        else
            return false;
    }

    private boolean filterByCategoryName(String categoryNameFilter, ProductSaleViewModel productSaleViewModel) {
        if (categoryNameFilter.equals("Tất cả"))
            return true;
        if (categoryNameFilter.equals(productSaleViewModel.getCategoryName()))
            return true;
        return false;
    }


    private void retrieveAllOrderWaiting() {
        OrderPagingRequest request = new OrderPagingRequest();
        request.setCondition(" status = 'PENDING'");
        List<OrderWaitingViewModel> orderList = OrderServiceImpl.getInstance().retrieveOrderListWaiting(request);
        orderWaitingViewModels = FXCollections.observableList(orderList);
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateCreateOrderColumn.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        nameStaffOrderColumn.setCellValueFactory(new PropertyValueFactory<>("nameStaff"));
        nameCustomerOrderColumn.setCellValueFactory(new PropertyValueFactory<>("nameCustomer"));
        orderWaitingTableView.setItems(orderWaitingViewModels);
    }

    private void retrieveAllProduct() {
        ProductPagingRequest request = new ProductPagingRequest();
        List<ProductSaleViewModel> productList = ProductServiceImpl.getInstance().retrieveAllProductSale(request);
        productSaleViewModels = FXCollections.observableList(productList);
        idProductColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameProductColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        priceSaleColumn.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        discountValueColumn.setCellValueFactory(new PropertyValueFactory<>("discountValueShow"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("colorNameListShow"));
        originColumn.setCellValueFactory(new PropertyValueFactory<>("origin"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productTableView.setItems(productSaleViewModels);
    }

    @FXML
    public void setOnActionShowDialogChangeCustomer(ActionEvent actionEvent) {
        StageRequestUtils requestUtils = StageRequestUtils.builder()
                .url("/form/form_change_customer_sale.fxml")
                .title("Thay đổi khách hàng")
                .nodeOwner((Node) actionEvent.getSource())
                .width(612d)
                .height(422d)
                .build();
        FXMLLoaderUtils.getInstance().showFormChild(requestUtils);
    }

    @FXML
    public void setOnActionReloadChangeCustomer(ActionEvent actionEvent) {
        lblIdCustomer.setText(String.valueOf(SharedCustomer.getInstance().getCustomerId()));
        lblNameCustomer.setText(SharedCustomer.getInstance().getNameCustomer());
    }

    @FXML
    public void setOnActionCreateOrder(ActionEvent actionEvent) {
        boolean confirm = AlertUtils.confirmationDialog("Yêu cầu xác nhận", "Xác nhận tạo mới hóa đơn ?");
        if (confirm) {
            OrderCreateRequest request = OrderCreateRequest.builder()
                    .customerId(Long.valueOf(lblIdCustomer.getText()))
                    .staffId(1L)
                    .status(OrderStatusEnum.PENDING)
                    .build();
            Long orderId = OrderServiceImpl.getInstance().insertOrder(request);
            if (orderId == -1) {
                AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_HAS_ERROR_OCCURRED);
            } else {
                AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_CREATE_ORDER_SUCCESS);
            }
            retrieveAllOrderWaiting();
        }
    }

    @FXML
    public void setOnActionReloadOrder(ActionEvent actionEvent) {
        retrieveAllOrderWaiting();
        retrieveAllProduct();
        setUpUI();
        addListener();
    }

    @FXML
    public void setOnActionAddToCart(ActionEvent actionEvent) {
        if (orderWaitingTableView.getSelectionModel().getSelectedIndex() > -1 && productTableView.getSelectionModel().getSelectedIndex() > -1) {
            OrderWaitingViewModel orderWaitingViewModel = orderWaitingTableView.getSelectionModel().getSelectedItem();
            ProductSaleViewModel productSaleViewModel = productTableView.getSelectionModel().getSelectedItem();

            Optional<String> quantityStr = AlertUtils.textInputDialog("Nhập số lượng", "Vui lòng nhập số lượng sản phẩm: ");
            if (quantityStr.isPresent()) {
                int quantity = Integer.parseInt(quantityStr.get());
                OrderItemCreateRequest request = OrderItemCreateRequest.builder()
                        .quantity(quantity)
                        .orderId(orderWaitingViewModel.getId())
                        .productId(productSaleViewModel.getId())
                        .build();
                Long orderItemId = OrderItemServiceImpl.getInstance().insertOrderItem(request);
                if (orderItemId != -1) {
                    AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_ADD_CART_ITEM_SUCCESS);
                    reloadDataCartItem(orderWaitingViewModel.getId());
                    retrieveAllProduct();
                } else {
                    AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_HAS_ERROR_OCCURRED);
                }
            }
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_MUST_CHOOSE_PRODUCT_AND_ORDER);
        }
    }

    @FXML
    public void setOnActionDeleteSingleCartItem(ActionEvent actionEvent) {
        CardItemViewModel cardItemViewModel = cardItemsTableView.getSelectionModel().getSelectedItem();
        OrderWaitingViewModel orderWaitingViewModel = orderWaitingTableView.getSelectionModel().getSelectedItem();
        boolean deleteSuccess = OrderItemServiceImpl.getInstance().deleteOrderItem(cardItemViewModel.getId());
        if (deleteSuccess) {
            AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_DELETE_CART_ITEM_SUCCESS);
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_HAS_ERROR_OCCURRED);
        }
        retrieveAllProduct();
        reloadDataCartItem(orderWaitingViewModel.getId());
    }

    @FXML
    public void setOnActionDeleteAllCartItem(ActionEvent actionEvent) {
        OrderWaitingViewModel orderWaitingViewModel = orderWaitingTableView.getSelectionModel().getSelectedItem();
        boolean deleteSuccess = OrderItemServiceImpl.getInstance().deleteAllCardItem(orderWaitingViewModel.getId());
        if (deleteSuccess) {
            AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.INFO_DELETE_ALL_CART_ITEM_SUCCESS);
        } else {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_HAS_ERROR_OCCURRED);
        }
        retrieveAllProduct();
        reloadDataCartItem(orderWaitingViewModel.getId());
    }
}
