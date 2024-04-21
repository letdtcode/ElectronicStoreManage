package com.mascara.electronicstoremanage.controllers.staff.sale;

import com.mascara.electronicstoremanage.enums.order.ModeOfDeliveryEnum;
import com.mascara.electronicstoremanage.enums.order.ModeOfPaymentEnum;
import com.mascara.electronicstoremanage.services.category.CategoryServiceImpl;
import com.mascara.electronicstoremanage.services.order.OrderServiceImpl;
import com.mascara.electronicstoremanage.services.order_item.OrderItemServiceImpl;
import com.mascara.electronicstoremanage.services.product.ProductServiceImpl;
import com.mascara.electronicstoremanage.view_model.category.CategoryPagingRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryViewModel;
import com.mascara.electronicstoremanage.view_model.order.OrderPagingRequest;
import com.mascara.electronicstoremanage.view_model.product.ProductPagingRequest;
import com.mascara.electronicstoremanage.view_model.sale.CardItemPagingRequest;
import com.mascara.electronicstoremanage.view_model.sale.CardItemViewModel;
import com.mascara.electronicstoremanage.view_model.sale.OrderWaitingViewModel;
import com.mascara.electronicstoremanage.view_model.sale.ProductSaleViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
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
    private TextField btnSearchProduct;
    @FXML
    private Button btnAddToCard;
    @FXML
    private ComboBox cbbCategoryName;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retrieveAllProduct();
        retrieveAllOrderWaiting();
        retrieveAllCardItem();
        addListenerForEachRowOrder();
        setUpUI();
    }

    private void setUpUI() {
        CategoryPagingRequest request = new CategoryPagingRequest();
        List<CategoryViewModel> categoryViewModels = CategoryServiceImpl.getInstance().retrieveAllCategory(request);
        categoryNameList = FXCollections.observableArrayList(categoryViewModels.stream()
                .map(CategoryViewModel::getCategoryName).collect(Collectors.toList()));

        cbbCategoryName.setItems(categoryNameList);
        cbbCategoryName.getSelectionModel().selectFirst();

        cbbPayment.setItems(paymentNameList);
        cbbDelivery.setItems(deliveryNameList);

        cbbPayment.getSelectionModel().selectFirst();
        cbbDelivery.getSelectionModel().selectFirst();
    }

    private void retrieveAllCardItem() {
        cardItemViewModels = FXCollections.observableArrayList();
        idProductInCartColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameProductInCartColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        priceSaleInCartColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        quantityProductInCartColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        cardItemsTableView.setItems(cardItemViewModels);
    }

    private void addListenerForEachRowOrder() {
        orderWaitingTableView.setRowFactory(param -> {
            TableRow<OrderWaitingViewModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    int rowIndex = orderWaitingTableView.getSelectionModel().getSelectedIndex();
                    OrderWaitingViewModel orderWaitingViewModel = orderWaitingTableView.getItems().get(rowIndex);
                    CardItemPagingRequest request = new CardItemPagingRequest();
                    List<CardItemViewModel> cardItemList =
                            OrderItemServiceImpl.getInstance().retrieveAllCartItem(orderWaitingViewModel.getId(), request);
                    cardItemViewModels = FXCollections.observableArrayList(cardItemList);
                }
            });
            return row;
        });
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
}
