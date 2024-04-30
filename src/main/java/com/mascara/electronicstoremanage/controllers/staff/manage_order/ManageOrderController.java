package com.mascara.electronicstoremanage.controllers.staff.manage_order;

import com.mascara.electronicstoremanage.enums.order.ModeOfDeliveryEnum;
import com.mascara.electronicstoremanage.enums.order.ModeOfPaymentEnum;
import com.mascara.electronicstoremanage.enums.order.OrderStatusEnum;
import com.mascara.electronicstoremanage.services.order.OrderServiceImpl;
import com.mascara.electronicstoremanage.services.order_item.OrderItemServiceImpl;
import com.mascara.electronicstoremanage.utils.AlertUtils;
import com.mascara.electronicstoremanage.utils.MessageUtils;
import com.mascara.electronicstoremanage.utils.TableVieExporterUtils;
import com.mascara.electronicstoremanage.view_model.order.OrderItemPagingRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderItemViewModel;
import com.mascara.electronicstoremanage.view_model.order.OrderPagingRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
 * Time      : 5:59 CH
 * Filename  : ManageOrderController
 */
public class ManageOrderController implements Initializable {
    @FXML
    private ComboBox<String> cbbStatusOrder;
    @FXML
    private ComboBox<String> cbbModeDelivery;
    @FXML
    private ComboBox<String> cbbModePayment;
    @FXML
    private ComboBox<String> cbbRangeBill;
    @FXML
    private ComboBox<Object> cbbMonthList;
    @FXML
    private ComboBox<Object> cbbYearList;
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
            "10,000,000 VND - 20,000,000 VND",
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
    @FXML
    private Button btnExportExcel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retrieveAllOrder();
        retrieveAllOrderItem();
        setUpUI();
        addListener();
    }


    private void addListener() {
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

        //        search filter event
        FilteredList<OrderViewModel> filteredList = new FilteredList<>(orderViewModels, b -> true);
        txtSearchOrder.textProperty().addListener((observable, oldValue, newValue) -> {
            searchAndFilterOrder(newValue, cbbStatusOrder.getValue(), cbbModeDelivery.getValue(), cbbModePayment.getValue(), cbbRangeBill.getSelectionModel().getSelectedIndex(), cbbMonthList.getValue(), cbbYearList.getValue(), filteredList);
        });

        //        filter sex and status
        cbbStatusOrder.valueProperty().addListener((observable, oldValue, newValue) -> {
                    String statusSelected = cbbStatusOrder.getSelectionModel().getSelectedItem();
                    searchAndFilterOrder(txtSearchOrder.getText().trim(), statusSelected, cbbModeDelivery.getValue(), cbbModePayment.getValue(), cbbRangeBill.getSelectionModel().getSelectedIndex(), cbbMonthList.getSelectionModel().getSelectedItem(), cbbYearList.getSelectionModel().getSelectedItem(), filteredList);
                }
        );
        cbbModeDelivery.valueProperty().addListener((observable, oldValue, newValue) -> {
                    String deliverySelected = cbbModeDelivery.getSelectionModel().getSelectedItem();
                    searchAndFilterOrder(txtSearchOrder.getText().trim(), cbbStatusOrder.getValue(), deliverySelected, cbbModePayment.getValue().toString(), cbbRangeBill.getSelectionModel().getSelectedIndex(), cbbMonthList.getSelectionModel().getSelectedItem(), cbbYearList.getSelectionModel().getSelectedItem(), filteredList);
                }
        );
        cbbModePayment.valueProperty().addListener((observable, oldValue, newValue) -> {
                    String paymentSelected = cbbModePayment.getSelectionModel().getSelectedItem();
                    searchAndFilterOrder(txtSearchOrder.getText().trim(), cbbStatusOrder.getValue(), cbbModeDelivery.getValue(), paymentSelected, cbbRangeBill.getSelectionModel().getSelectedIndex(), cbbMonthList.getSelectionModel().getSelectedItem(), cbbYearList.getSelectionModel().getSelectedItem(), filteredList);
                }
        );
        cbbRangeBill.valueProperty().addListener((observable, oldValue, newValue) -> {
                    int indexCbb = cbbRangeBill.getSelectionModel().getSelectedIndex();
                    searchAndFilterOrder(txtSearchOrder.getText().trim(), cbbStatusOrder.getValue(), cbbModeDelivery.getValue(), cbbModePayment.getValue(), indexCbb, cbbMonthList.getSelectionModel().getSelectedItem(), cbbYearList.getSelectionModel().getSelectedItem(), filteredList);
                }
        );
        cbbMonthList.valueProperty().addListener((observable, oldValue, newValue) -> {
                    Object monthSelected = cbbMonthList.getSelectionModel().getSelectedItem();
                    searchAndFilterOrder(txtSearchOrder.getText().trim(), cbbStatusOrder.getValue(), cbbModeDelivery.getValue(), cbbModePayment.getValue(), cbbRangeBill.getSelectionModel().getSelectedIndex(), monthSelected, cbbYearList.getSelectionModel().getSelectedItem(), filteredList);
                }
        );
        cbbYearList.valueProperty().addListener((observable, oldValue, newValue) -> {
                    Object yearSelected = cbbYearList.getSelectionModel().getSelectedItem();
                    searchAndFilterOrder(txtSearchOrder.getText().trim(), cbbStatusOrder.getValue(), cbbModeDelivery.getValue(), cbbModePayment.getValue(), cbbRangeBill.getSelectionModel().getSelectedIndex(), cbbMonthList.getSelectionModel().getSelectedItem(), yearSelected, filteredList);
                }
        );

        SortedList<OrderViewModel> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(orderTableView.comparatorProperty());
        orderTableView.setItems(sortedList);
    }

    private void searchAndFilterOrder(String newValueTextField,
                                      String orderStatusFilter,
                                      String modeDeliveryFilter,
                                      String modePaymentFilter,
                                      int indexRangeBillFilter,
                                      Object monthFilter,
                                      Object yearFilter,
                                      FilteredList<OrderViewModel> filteredList) {
        filteredList.setPredicate(orderViewModel -> {
            boolean resultSearch = searchByOrderIdOrFullnameCustomer(newValueTextField, orderViewModel);
            boolean resultStatusFilter = filterByStatus(orderStatusFilter, orderViewModel);
            boolean resultDeliveryFilter = filterByDelivery(modeDeliveryFilter, orderViewModel);
            boolean resultPaymentFilter = filterByPayment(modePaymentFilter, orderViewModel);
            boolean resultRangeBillFilter = filterByRangeBill(indexRangeBillFilter, orderViewModel);
            boolean resultMonthYearCreatedFilter = filterByMonthYearCreated(monthFilter, yearFilter, orderViewModel);
            return resultSearch && resultStatusFilter && resultDeliveryFilter && resultRangeBillFilter && resultPaymentFilter && resultMonthYearCreatedFilter;
        });
    }

    private boolean filterByStatus(String status, OrderViewModel orderViewModel) {
        boolean result = false;
        switch (status) {
            case "Tất cả":
                result = true;
                break;
            case "Chờ thanh toán":
                if (orderViewModel.getStatus().equals(OrderStatusEnum.PENDING))
                    result = true;
                break;
            case "Đã thanh toán":
                if (orderViewModel.getStatus().equals(OrderStatusEnum.PAID))
                    result = true;
                break;
            case "Đã hủy":
                if (orderViewModel.getStatus().equals(OrderStatusEnum.CANCELED))
                    result = true;
                break;
        }
        return result;
    }

    private boolean filterByDelivery(String modeDelivery, OrderViewModel orderViewModel) {
        boolean result = false;
        switch (modeDelivery) {
            case "Tất cả":
                result = true;
                break;
            case "Trực tiếp":
                if (orderViewModel.getModeOfDelivery() != null && orderViewModel.getModeOfDelivery().equals(ModeOfDeliveryEnum.DIRECT_SALE))
                    result = true;
                break;
            case "Khác":
                if (orderViewModel.getModeOfDelivery() != null && orderViewModel.getModeOfDelivery().equals(ModeOfDeliveryEnum.OTHER))
                    result = true;
                break;
        }
        return result;
    }

    private boolean filterByPayment(String modePayment, OrderViewModel orderViewModel) {
        boolean result = false;
        switch (modePayment) {
            case "Tất cả":
                result = true;
                break;
            case "Tiền mặt":
                if (orderViewModel.getModeOfPayment() != null && orderViewModel.getModeOfPayment().equals(ModeOfPaymentEnum.CASH))
                    result = true;
                break;
            case "Quẹt thẻ":
                if (orderViewModel.getModeOfPayment() != null && orderViewModel.getModeOfPayment().equals(ModeOfPaymentEnum.CARD_SWIPE))
                    result = true;
                break;
            case "Chuyển khoản":
                if (orderViewModel.getModeOfPayment() != null && orderViewModel.getModeOfPayment().equals(ModeOfPaymentEnum.BANK_TRANSFER))
                    result = true;
                break;
        }
        return result;
    }

    private boolean filterByRangeBill(int index, OrderViewModel orderViewModel) {
        boolean result = false;
        Double totalBill = orderViewModel.getTotalBill();
        switch (index) {
            case 0:
                result = true;
                break;
            case 1:
                if (totalBill == null || (totalBill >= 0 && totalBill <= 500000))
                    result = true;
                break;
            case 2:
                if (totalBill >= 500000 && totalBill <= 1000000)
                    result = true;
                break;
            case 3:
                if (totalBill >= 1000000 && totalBill <= 5000000)
                    result = true;
                break;
            case 4:
                if (totalBill >= 5000000 && totalBill <= 1000000)
                    result = true;
                break;
            case 5:
                if (totalBill >= 10000000 && totalBill <= 20000000)
                    result = true;
                break;
            case 6:
                if (totalBill >= 20000000 && totalBill <= 50000000)
                    result = true;
                break;
            case 7:
                if (totalBill > 50000000)
                    result = true;
                break;
        }
        return result;
    }

    private boolean filterByMonthYearCreated(Object month, Object year, OrderViewModel orderViewModel) {
        boolean result = false;
        int monthCreatedOfOrder = orderViewModel.getDateCheckout().getMonthValue();
        int yearCreatedOfOrder = orderViewModel.getDateCheckout().getYear();

        if (month.equals("Tất cả") && year.equals("Tất cả"))
            result = true;
        if (month.equals("Tất cả") && year instanceof Integer) {
            if (yearCreatedOfOrder == (int) year)
                result = true;
        }
        if (month instanceof Integer && year.equals("Tất cả")) {
            if (monthCreatedOfOrder == (int) month)
                result = true;
        }
        if (month instanceof Integer && year instanceof Integer) {
            if (monthCreatedOfOrder == (int) month && yearCreatedOfOrder == (int) year)
                result = true;
        }
        return result;
    }

    private boolean searchByOrderIdOrFullnameCustomer(String newValueTextField, OrderViewModel orderViewModel) {
        if (newValueTextField.isEmpty() || newValueTextField.isBlank() || newValueTextField == null)
            return true;
        String searchKeyword = newValueTextField.toLowerCase();

        if (searchKeyword.matches("\\d+") && orderViewModel.getId() == Double.parseDouble(newValueTextField))
            return true;
        else if (orderViewModel.getFullNameCustomer().toLowerCase().contains(searchKeyword))
            return true;
        else
            return false;
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

    @FXML
    public void setOnActionExportExcel(ActionEvent actionEvent) {
        boolean exportExcel = TableVieExporterUtils.getInstance().exportExcel(orderTableView);
        if (exportExcel)
            AlertUtils.showMessageInfo(MessageUtils.TITLE_SUCCESS, MessageUtils.EXPORT_EXCEL_SUCCESS);
        else
            AlertUtils.showMessageInfo(MessageUtils.TITLE_FAILED, MessageUtils.EXPORT_EXCEL_FAILED);
    }
}
