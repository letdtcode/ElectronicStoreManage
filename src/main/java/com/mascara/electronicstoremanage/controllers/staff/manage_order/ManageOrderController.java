package com.mascara.electronicstoremanage.controllers.staff.manage_order;

import com.mascara.electronicstoremanage.enums.order.ModeOfDeliveryEnum;
import com.mascara.electronicstoremanage.enums.order.ModeOfPaymentEnum;
import com.mascara.electronicstoremanage.enums.order.OrderStatusEnum;
import com.mascara.electronicstoremanage.services.order.OrderServiceImpl;
import com.mascara.electronicstoremanage.services.order_item.OrderItemServiceImpl;
import com.mascara.electronicstoremanage.view_model.order.OrderItemPagingRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderItemViewModel;
import com.mascara.electronicstoremanage.view_model.order.OrderPagingRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * Time      : 5:59 CH
 * Filename  : ManageOrderController
 */
public class ManageOrderController implements Initializable {
    @FXML
    private ComboBox cbbStatusOrder;
    @FXML
    private ComboBox cbbModeDelivery;
    @FXML
    private ComboBox cbbModePayment;
    @FXML
    private ComboBox cbbRangeBill;
    @FXML
    private ComboBox cbbMonthList;
    @FXML
    private ComboBox cbbYearList;
    @FXML
    private TextField txtSearchOrder;
    @FXML
    private TableColumn orderIdColumn;
    @FXML
    private TableColumn totalBillColumn;
    @FXML
    private TableColumn totalPayColumn;
    @FXML
    private TableColumn changeMoneyColumn;
    @FXML
    private TableColumn modePaymentColumn;
    @FXML
    private TableColumn modeDeliveryColumn;
    @FXML
    private TableColumn dateCreatedColumn;
    @FXML
    private TableColumn statusColumn;
    @FXML
    private TableColumn nameStaffColumn;
    @FXML
    private TableColumn nameCustomerColumn;
    @FXML
    private TableColumn noteColumn;

    private ObservableList<OrderViewModel> orderViewModels;
    private ObservableList<OrderItemViewModel> orderItemViewModels;

    private ObservableList<String> statusOrderList = FXCollections.observableArrayList("Tất cả",
            OrderStatusEnum.PAID.getDisplay(),
            OrderStatusEnum.PENDING.getDisplay(),
            OrderStatusEnum.CANCELED.getDisplay());

    private ObservableList<String> modeOfDeliveryList = FXCollections.observableArrayList("Tất cả",
            ModeOfDeliveryEnum.DIRECT_SALE.getDisplay(),
            ModeOfDeliveryEnum.OTHER.getDisplay());

    private ObservableList<String> modeOfPaymentList = FXCollections.observableArrayList("Tất cả",
            ModeOfPaymentEnum.BANK_TRANSFER.getDisplay(),
            ModeOfPaymentEnum.CARD_SWIPE.getDisplay(),
            ModeOfPaymentEnum.CASH.getDisplay());

    private ObservableList<String> rangeBillList = FXCollections.observableArrayList(
            "Tất cả",
            "0 VND - 500,000 VND",
            "500,000 VND - 1,000,000 VND",
            "1,000,000 VND - 5,000,000 VND",
            "5,000,000 VND - 10,000,000 VND",
            "1,000,000 VND - 20,000,000 VND",
            "20,000,000 VND - 50,000,000 VND",
            "Trên 50,000,000");
    private ObservableList<Object> monthList = FXCollections.observableArrayList("Tất cả", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

    private ObservableList<Object> yearList = FXCollections.observableArrayList("Tất cả", 2020, 2021, 2022, 2023, 2024);
    @FXML
    private TableView<OrderViewModel> orderTableView;
    @FXML
    private TableView<OrderItemViewModel> orderItemTableView;
    @FXML
    private TableColumn orderItemIdColumn;
    @FXML
    private TableColumn productIdColumn;
    @FXML
    private TableColumn productNameColumn;
    @FXML
    private TableColumn quantityColumn;
    @FXML
    private TableColumn unitPriceColumn;
    @FXML
    private TableColumn totalItemColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retrieveAllOrder();
        retrieveAllOrderItem();
        setUpUI();
        addListenerForEachRow();
    }


    private void addListenerForEachRow() {
        orderTableView.setRowFactory(param -> {
            TableRow<OrderViewModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    OrderViewModel orderViewModel = orderTableView.getSelectionModel().getSelectedItem();

                    OrderItemPagingRequest request = new OrderItemPagingRequest();
                    request.setCondition(" orderId = " + orderViewModel.getId());
                    List<OrderItemViewModel> orderItemList =
                            OrderItemServiceImpl.getInstance().retrieveAllOrderItem(request);
                    orderItemViewModels = FXCollections.observableArrayList(orderItemList);
                    orderItemTableView.setItems(orderItemViewModels);
                }
            });
            return row;
        });
    }

    private void setUpUI() {
        cbbStatusOrder.setItems(statusOrderList);
        cbbModeDelivery.setItems(modeOfDeliveryList);
        cbbModePayment.setItems(modeOfPaymentList);
        cbbRangeBill.setItems(rangeBillList);
        cbbMonthList.setItems(monthList);
        cbbYearList.setItems(yearList);

        cbbStatusOrder.getSelectionModel().selectFirst();
        cbbModeDelivery.getSelectionModel().selectFirst();
        cbbModePayment.getSelectionModel().selectFirst();
        cbbRangeBill.getSelectionModel().selectFirst();
        cbbMonthList.getSelectionModel().selectFirst();
        cbbYearList.getSelectionModel().selectFirst();
    }

    private void retrieveAllOrderItem() {
        orderItemViewModels = FXCollections.observableArrayList();
        orderItemIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPriceShow"));
        totalItemColumn.setCellValueFactory(new PropertyValueFactory<>("totalItemShow"));
        orderItemTableView.setItems(orderItemViewModels);
    }

    private void retrieveAllOrder() {
        OrderPagingRequest request = new OrderPagingRequest();
        List<OrderViewModel> orderList = OrderServiceImpl.getInstance().retrieveAllOrder(request);
        orderViewModels = FXCollections.observableList(orderList);

        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        totalBillColumn.setCellValueFactory(new PropertyValueFactory<>("totalBillShow"));
        totalPayColumn.setCellValueFactory(new PropertyValueFactory<>("totalPayShow"));
        changeMoneyColumn.setCellValueFactory(new PropertyValueFactory<>("changeMoneyShow"));
        modePaymentColumn.setCellValueFactory(new PropertyValueFactory<>("modeOfPaymentShow"));
        modeDeliveryColumn.setCellValueFactory(new PropertyValueFactory<>("modeOfDeliveryShow"));
        dateCreatedColumn.setCellValueFactory(new PropertyValueFactory<>("dateCheckout"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("statusShow"));
        nameStaffColumn.setCellValueFactory(new PropertyValueFactory<>("fullNameStaff"));
        nameCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("fullNameCustomer"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));

        orderTableView.setItems(orderViewModels);
    }
}
