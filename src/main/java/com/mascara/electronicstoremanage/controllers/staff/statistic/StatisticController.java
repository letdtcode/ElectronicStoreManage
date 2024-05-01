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
import com.mascara.electronicstoremanage.view_model.statistic.ProductStatisticPagingRequest;
import com.mascara.electronicstoremanage.view_model.statistic.ProductStatisticViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TextField txtIdOrder;
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
    @FXML
    private RadioButton rdbStatisticAll;
    @FXML
    private RadioButton rdbStatisticDateNow;
    @FXML
    private RadioButton rdbStatisticRangeDate;
    @FXML
    private Text lblNumCustomer;

    @Deprecated
    public void setOnActionGetAll(ActionEvent actionEvent) {
    }

    @Deprecated
    public void setOnActionGetRangeDate(ActionEvent actionEvent) {
    }

    @FXML
    public void setOnActionChooseCategoryName(ActionEvent actionEvent) {
    }

    @FXML
    public void setOnActionSendReport(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        request = new ProductStatisticPagingRequest();
        setUpUI();
        getStatisticProductDateNow();
        addListener();
        double totalRevenue = productStatisticViewModels.stream().mapToDouble(product -> product.getRevenueMoney() == null ? 0.0 : product.getRevenueMoney()).sum();
        long totalOrderPaid = OrderServiceImpl.getInstance().countNumberOfOrderRangeDate(LocalDate.now(), null, OrderStatusEnum.PAID);
        long totalOrderCancel = OrderServiceImpl.getInstance().countNumberOfOrderRangeDate(LocalDate.now(), null, OrderStatusEnum.CANCELED);
        lblRevenue.setText(CurrencyUtils.getInstance().convertVietnamCurrency(totalRevenue));
        lblNumCustomer.setText(CustomerServiceImpl.getInstance().countTotalCustomer().toString());
        lblNumOrder.setText(String.valueOf(totalOrderPaid));
        lblNumOrderCancel.setText(String.valueOf(totalOrderCancel));
    }

    private void addListener() {
    }

    private void setUpUI() {
        //        Set up combobox category name
        CategoryPagingRequest categoryPagingRequest = new CategoryPagingRequest();
        List<CategoryViewModel> categoryViewModels = CategoryServiceImpl.getInstance().retrieveAllCategory(categoryPagingRequest);
        categoryNameList = FXCollections.observableArrayList(categoryViewModels.stream()
                .map(CategoryViewModel::getCategoryName).collect(Collectors.toList()));
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
            ProductStatisticPagingRequest request = new ProductStatisticPagingRequest();
            request.setDateStart(dateStart);
            request.setDateEnd(dateEnd);
            List<ProductStatisticViewModel> productList = ProductServiceImpl.getInstance().statisticProductList(request);
            productStatisticViewModels = FXCollections.observableArrayList(productList);
            tableViewProductStatistic.setItems(productStatisticViewModels);

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
        } else if (rdbStatisticDateNow.isSelected()) {
            btnFilterRangeDate.setDisable(true);
            getStatisticProductDateNow();
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

    private void getStatisticProductAll() {
        request.setDateStart(null);
        request.setDateEnd(null);
        List<ProductStatisticViewModel> productList = ProductServiceImpl.getInstance().statisticProductList(request);
        productStatisticViewModels = FXCollections.observableArrayList(productList);

        idProductColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        codeProductColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        revenueColumn.setCellValueFactory(new PropertyValueFactory<>("revenueMoneyShow"));

        tableViewProductStatistic.setItems(productStatisticViewModels);

        double totalRevenue = productList.stream().mapToDouble(product -> product.getRevenueMoney() == null ? 0.0 : product.getRevenueMoney()).sum();
        long totalOrderPaid = OrderServiceImpl.getInstance().countNumberOfOrderRangeDate(null, null, OrderStatusEnum.PAID);
        long totalOrderCancel = OrderServiceImpl.getInstance().countNumberOfOrderRangeDate(null, null, OrderStatusEnum.CANCELED);

        lblRevenue.setText(CurrencyUtils.getInstance().convertVietnamCurrency(totalRevenue));
        lblNumOrder.setText(String.valueOf(totalOrderPaid));
        lblNumOrderCancel.setText(String.valueOf(totalOrderCancel));
    }

}
