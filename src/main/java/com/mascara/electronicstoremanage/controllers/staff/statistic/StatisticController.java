package com.mascara.electronicstoremanage.controllers.staff.statistic;

import com.mascara.electronicstoremanage.enums.order.OrderStatusEnum;
import com.mascara.electronicstoremanage.services.category.CategoryServiceImpl;
import com.mascara.electronicstoremanage.services.customer.CustomerServiceImpl;
import com.mascara.electronicstoremanage.services.order.OrderServiceImpl;
import com.mascara.electronicstoremanage.services.product.ProductServiceImpl;
import com.mascara.electronicstoremanage.utils.AlertUtils;
import com.mascara.electronicstoremanage.utils.CurrencyUtils;
import com.mascara.electronicstoremanage.utils.MessageUtils;
import com.mascara.electronicstoremanage.view_model.category.CategoryPagingRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryViewModel;
import com.mascara.electronicstoremanage.view_model.statistic.OrderCancelStatisticPagingRequest;
import com.mascara.electronicstoremanage.view_model.statistic.OrderCancelStatisticViewModel;
import com.mascara.electronicstoremanage.view_model.statistic.ProductStatisticPagingRequest;
import com.mascara.electronicstoremanage.view_model.statistic.ProductStatisticViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 5:59 CH
 * Filename  : StatisticController
 */
public class StatisticController implements Initializable {
    @FXML
    private Text lblRevenue;
    @FXML
    private Text lblNumOrder;
    @FXML
    private Text lblNumOrderCancel;
    @FXML
    private DatePicker dtpStart;
    @FXML
    private DatePicker dtpEnd;
    @FXML
    private Tab tabProduct;
    @FXML
    private ComboBox cbbCategoryName;
    @FXML
    private TextField txtSearchProductName;
    @FXML
    private TableView tableViewProductStatistic;
    @FXML
    private Tab tabOrderCancel;
    @FXML
    private TableView tableViewOrderCancel;
    @FXML
    private Button btnSendReport;

    private ObservableList<String> categoryNameList;
    @FXML
    private ToggleGroup chooseStatistic;
    @FXML
    private Text lblDateNow;
    @FXML
    private Text lblTimeNow;

    private ObservableList<ProductStatisticViewModel> productStatisticViewModels;
    private ObservableList<OrderCancelStatisticViewModel> orderCancelStatisticViewModels;
    @FXML
    private TableColumn idProductColumn;
    @FXML
    private TableColumn codeProductColumn;
    @FXML
    private TableColumn productNameColumn;
    @FXML
    private TableColumn quantityColumn;
    @FXML
    private TableColumn revenueColumn;
    @FXML
    private TableColumn idOrderColumn;
    @FXML
    private TableColumn totalPayColumn;
    @FXML
    private TableColumn modeOfPaymentColumn;
    @FXML
    private TableColumn dateCreatedColumn;
    @FXML
    private TableColumn nameCustomerColumn;
    @FXML
    private TableColumn noteColumn;
    @FXML
    private Button btnFilterRangeDate;
    private ProductStatisticPagingRequest request;
    private OrderCancelStatisticPagingRequest orderCancelPagingRequest;
    @FXML
    private RadioButton rdbStatisticAll;
    @FXML
    private RadioButton rdbStatisticDateNow;
    @FXML
    private RadioButton rdbStatisticRangeDate;
    @FXML
    private Text lblNumCustomer;
    @FXML
    private TextField txtSearchOrderCancel;

    @FXML
    public void setOnActionSendReport(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        request = new ProductStatisticPagingRequest();
        orderCancelPagingRequest = new OrderCancelStatisticPagingRequest();
        setUpUI();
        getStatisticProductDateNow();
        getStatisticOrderCancelDateNow();
        addListener();
        double totalRevenue = productStatisticViewModels.stream().mapToDouble(product -> product.getRevenueMoney() == null ? 0.0 : product.getRevenueMoney()).sum();
        long totalOrderPaid = OrderServiceImpl.getInstance().countNumberOfOrderRangeDate(LocalDate.now(), null, OrderStatusEnum.PAID);
        long totalOrderCancel = OrderServiceImpl.getInstance().countNumberOfOrderRangeDate(LocalDate.now(), null, OrderStatusEnum.CANCELED);
        lblRevenue.setText(CurrencyUtils.getInstance().convertVietnamCurrency(totalRevenue));
        lblNumCustomer.setText(CustomerServiceImpl.getInstance().countTotalCustomer().toString());
        lblNumOrder.setText(String.valueOf(totalOrderPaid));
        lblNumOrderCancel.setText(String.valueOf(totalOrderCancel));
    }

    private void getStatisticOrderCancelDateNow() {
        orderCancelPagingRequest.setDateStart(LocalDate.now());
        orderCancelPagingRequest.setDateEnd(null);
        List<OrderCancelStatisticViewModel> orderList = OrderServiceImpl.getInstance().retrieveOrderCancelStatistic(orderCancelPagingRequest);
        orderCancelStatisticViewModels = FXCollections.observableArrayList(orderList);

        idOrderColumn.setCellValueFactory(new PropertyValueFactory<>("idOrder"));
        totalPayColumn.setCellValueFactory(new PropertyValueFactory<>("totalPayShow"));
        modeOfPaymentColumn.setCellValueFactory(new PropertyValueFactory<>("modeOfPaymentShow"));
        dateCreatedColumn.setCellValueFactory(new PropertyValueFactory<>("timeCheckout"));
        nameCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("nameCustomer"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));

        tableViewOrderCancel.setItems(orderCancelStatisticViewModels);
    }

    private void addListener() {
        //        search and filter category
        FilteredList<ProductStatisticViewModel> filteredProductList = new FilteredList<>(productStatisticViewModels, b -> true);
        txtSearchProductName.textProperty().addListener((observable, oldValue, newValue) -> {
            searchAndFilterProductStatistic(newValue, cbbCategoryName.getValue().toString(), filteredProductList);
        });

        cbbCategoryName.valueProperty().addListener((observable, oldValue, newValue) -> {
                    String categoryName = (String) cbbCategoryName.getSelectionModel().getSelectedItem();
                    searchAndFilterProductStatistic(txtSearchProductName.getText().trim(), categoryName, filteredProductList);
                }
        );

        SortedList<ProductStatisticViewModel> sortedProductList = new SortedList<>(filteredProductList);
        sortedProductList.comparatorProperty().bind(tableViewProductStatistic.comparatorProperty());
        tableViewProductStatistic.setItems(sortedProductList);

        //        search and filter order cancel
        FilteredList<OrderCancelStatisticViewModel> filteredOrderCancelList = new FilteredList<>(orderCancelStatisticViewModels, b -> true);
        txtSearchOrderCancel.textProperty().addListener((observable, oldValue, newValue) -> {
            searchAndFilterOrderCancelStatistic(newValue, filteredOrderCancelList);
        });

        SortedList<OrderCancelStatisticViewModel> sortedOrderCancelList = new SortedList<>(filteredOrderCancelList);
        sortedOrderCancelList.comparatorProperty().bind(tableViewOrderCancel.comparatorProperty());
        tableViewOrderCancel.setItems(sortedOrderCancelList);
    }

    private void searchAndFilterProductStatistic(String newValueTextField, String categoryName, FilteredList<ProductStatisticViewModel> filteredList) {
        filteredList.setPredicate(productStatisticViewModel -> {
            boolean resultSearch = searchByProductNameOrProductCode(newValueTextField, productStatisticViewModel);
            boolean resultFilterCategoryName = filterByCategoryName(categoryName, productStatisticViewModel);
            return resultSearch && resultFilterCategoryName;
        });
    }

    private void searchAndFilterOrderCancelStatistic(String newValueTextField, FilteredList<OrderCancelStatisticViewModel> filteredList) {
        filteredList.setPredicate(orderCancel -> {
            boolean resultSearch = searchByOrderIdOrNameCustomer(newValueTextField, orderCancel);
            return resultSearch;
        });
    }

    private boolean searchByOrderIdOrNameCustomer(String newValueTextField, OrderCancelStatisticViewModel orderCancelStatisticViewModel) {
        if (newValueTextField.isEmpty() || newValueTextField.isBlank() || newValueTextField == null)
            return true;
        String searchKeyword = newValueTextField.toLowerCase();

        if (orderCancelStatisticViewModel.getIdOrder().toString().contains(searchKeyword) || orderCancelStatisticViewModel.getNameCustomer().toLowerCase().contains(searchKeyword))
            return true;
        else
            return false;
    }

    private boolean searchByProductNameOrProductCode(String newValueTextField, ProductStatisticViewModel productStatisticViewModel) {
        if (newValueTextField.isEmpty() || newValueTextField.isBlank() || newValueTextField == null)
            return true;
        String searchKeyword = newValueTextField.toLowerCase();

        if (productStatisticViewModel.getProductName().toLowerCase().contains(searchKeyword) || productStatisticViewModel.getCode().toLowerCase().contains(searchKeyword))
            return true;
        else
            return false;
    }

    private boolean filterByCategoryName(String categoryName, ProductStatisticViewModel productStatisticViewModel) {
        if (categoryName.equals("Tất cả"))
            return true;
        if (categoryName.equals(productStatisticViewModel.getCategoryName()))
            return true;
        return false;
    }

    private void setUpUI() {
        //        Set up combobox category name
        CategoryPagingRequest categoryPagingRequest = new CategoryPagingRequest();
        List<CategoryViewModel> categoryViewModels = CategoryServiceImpl.getInstance().retrieveAllCategory(categoryPagingRequest);
        categoryNameList = FXCollections.observableArrayList(categoryViewModels.stream()
                .map(CategoryViewModel::getCategoryName).collect(Collectors.toList()));
        categoryNameList.add(0, "Tất cả");
        cbbCategoryName.setItems(categoryNameList);
        cbbCategoryName.getSelectionModel().selectFirst();

    }

    @FXML
    public void setOnActionFilterRangeDate(ActionEvent actionEvent) {
        LocalDate dateStart = dtpStart.getValue();
        LocalDate dateEnd = dtpEnd.getValue();
        if (dateStart == null || dateEnd == null || dateEnd.isBefore(dateStart)) {
            AlertUtils.showMessageWarning(MessageUtils.TITLE_FAILED, MessageUtils.WARNING_DATE_END_MUST_AFTER_DATE_START);
        } else {
            request.setDateStart(dateStart);
            request.setDateEnd(dateEnd);
            List<ProductStatisticViewModel> productList = ProductServiceImpl.getInstance().statisticProductList(request);
            productStatisticViewModels = FXCollections.observableArrayList(productList);
            tableViewProductStatistic.setItems(productStatisticViewModels);

            orderCancelPagingRequest.setDateStart(dateStart);
            orderCancelPagingRequest.setDateEnd(dateEnd);
            List<OrderCancelStatisticViewModel> orderCancelList = OrderServiceImpl.getInstance().retrieveOrderCancelStatistic(orderCancelPagingRequest);
            orderCancelStatisticViewModels = FXCollections.observableArrayList(orderCancelList);
            tableViewOrderCancel.setItems(orderCancelStatisticViewModels);

            double totalRevenue = productList.stream().mapToDouble(product -> product.getRevenueMoney() == null ? 0.0 : product.getRevenueMoney()).sum();
            long totalOrderPaid = OrderServiceImpl.getInstance().countNumberOfOrderRangeDate(dateStart, dateEnd, OrderStatusEnum.PAID);
            long totalOrderCancel = OrderServiceImpl.getInstance().countNumberOfOrderRangeDate(dateStart, dateEnd, OrderStatusEnum.CANCELED);

            lblRevenue.setText(CurrencyUtils.getInstance().convertVietnamCurrency(totalRevenue));
            lblNumOrder.setText(String.valueOf(totalOrderPaid));
            lblNumOrderCancel.setText(String.valueOf(totalOrderCancel));
        }
    }

    @FXML
    public void setOnActionChooseTypeStatistic(ActionEvent actionEvent) {
        if (rdbStatisticAll.isSelected()) {
            btnFilterRangeDate.setDisable(true);
            getStatisticProductAll();
            getStatisticOrderCancelAll();
        } else if (rdbStatisticDateNow.isSelected()) {
            btnFilterRangeDate.setDisable(true);
            getStatisticProductDateNow();
            getStatisticOrderCancelDateNow();
        } else if (rdbStatisticRangeDate.isSelected()) {
            btnFilterRangeDate.setDisable(false);
        }
    }

    private void getStatisticProductDateNow() {
        request.setDateStart(LocalDate.now());
        request.setDateEnd(null);
        List<ProductStatisticViewModel> productList = ProductServiceImpl.getInstance().statisticProductList(request);
        productStatisticViewModels = FXCollections.observableArrayList(productList);

        idProductColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        codeProductColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        revenueColumn.setCellValueFactory(new PropertyValueFactory<>("revenueMoneyShow"));

        tableViewProductStatistic.setItems(productStatisticViewModels);
        Double totalRevenue = productList.stream().mapToDouble(product -> product.getRevenueMoney() == null ? 0.0 : product.getRevenueMoney()).sum();
        lblRevenue.setText(CurrencyUtils.getInstance().convertVietnamCurrency(totalRevenue));
    }

    private void getStatisticOrderCancelAll() {
        orderCancelPagingRequest.setDateStart(null);
        orderCancelPagingRequest.setDateEnd(null);
        List<OrderCancelStatisticViewModel> orderCancelList = OrderServiceImpl.getInstance().retrieveOrderCancelStatistic(orderCancelPagingRequest);
        orderCancelStatisticViewModels = FXCollections.observableArrayList(orderCancelList);

        tableViewOrderCancel.setItems(orderCancelStatisticViewModels);
    }

    private void getStatisticProductAll() {
        request.setDateStart(null);
        request.setDateEnd(null);
        List<ProductStatisticViewModel> productList = ProductServiceImpl.getInstance().statisticProductList(request);
        productStatisticViewModels = FXCollections.observableArrayList(productList);

        tableViewProductStatistic.setItems(productStatisticViewModels);

        double totalRevenue = productList.stream().mapToDouble(product -> product.getRevenueMoney() == null ? 0.0 : product.getRevenueMoney()).sum();
        long totalOrderPaid = OrderServiceImpl.getInstance().countNumberOfOrderRangeDate(null, null, OrderStatusEnum.PAID);
        long totalOrderCancel = OrderServiceImpl.getInstance().countNumberOfOrderRangeDate(null, null, OrderStatusEnum.CANCELED);

        lblRevenue.setText(CurrencyUtils.getInstance().convertVietnamCurrency(totalRevenue));
        lblNumOrder.setText(String.valueOf(totalOrderPaid));
        lblNumOrderCancel.setText(String.valueOf(totalOrderCancel));
    }

}
