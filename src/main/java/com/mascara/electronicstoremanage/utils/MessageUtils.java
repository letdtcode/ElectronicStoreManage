package com.mascara.electronicstoremanage.utils;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 5:09 CH
 * Filename  : MessageUtils
 */
public class MessageUtils {
    public static String TITLE_SUCCESS = "Thông báo thành công";
    public static String TITLE_FAILED = "Thông báo thất bại";

    public static String WARNING_DATA_NOT_VALID = "Vui lòng nhập đầy đủ dữ liệu";
    public static String WARNING_SELECT_ROW = "Vui lòng chọn 1 hàng để cập nhật";
    //    Brand CRUD Message
    public static String INFO_CREATE_BRAND_SUCCESS = "Thêm mới thương hiệu thành công";
    public static String INFO_UPDATE_BRAND_SUCCESS = "Cập nhật thương hiệu thành công";
    public static String INFO_DELETE_BRAND_SUCCESS = "Xóa thương hiệu thành công";
    public static String WARNING_BRAND_NAME_DUPLICATED = "Tên thương hiệu đã tồn tại";
    public static String WARNING_BRAND_CAN_NOT_DELETE = "Thương hiệu đang được sử dụng, không thể xóa";

    //    Color CRUD Message
    public static String INFO_CREATE_COLOR_SUCCESS = "Thêm mới màu sắc thành công";
    public static String INFO_UPDATE_COLOR_SUCCESS = "Cập nhật màu sắc thành công";
    public static String INFO_DELETE_COLOR_SUCCESS = "Xóa màu sắc thành công";
    public static String WARNING_COLOR_NAME_DUPLICATED = "Màu sắc đã tồn tại";
    public static String WARNING_COLOR_CAN_NOT_DELETE = "Màu sắc đang được sử dụng, không thể xóa";

    //    Feature CRUD Message
    public static String INFO_CREATE_FEATURE_SUCCESS = "Thêm mới tính năng thành công";
    public static String INFO_UPDATE_FEATURE_SUCCESS = "Cập nhật tính năng thành công";
    public static String INFO_DELETE_FEATURE_SUCCESS = "Xóa tính năng thành công";
    public static String WARNING_FEATURE_NAME_DUPLICATED = "Tính năng đã tồn tại";
    public static String WARNING_FEATURE_CAN_NOT_DELETE = "Tính năng đang được sử dụng, không thể xóa";

    //    Material CRUD Message
    public static String INFO_CREATE_MATERIAL_SUCCESS = "Thêm mới chất liệu thành công";
    public static String INFO_UPDATE_MATERIAL_SUCCESS = "Cập nhật chất liệu thành công";
    public static String INFO_DELETE_MATERIAL_SUCCESS = "Xóa chất liệu thành công";
    public static String WARNING_MATERIAL_NAME_DUPLICATED = "Chất liệu đã tồn tại";
    public static String WARNING_MATERIAL_CAN_NOT_DELETE = "Chất liệu đang được sử dụng, không thể xóa";

    //    Category CRUD Message
    public static String INFO_CREATE_CATEGORY_SUCCESS = "Thêm mới danh mục thành công";
    public static String INFO_UPDATE_CATEGORY_SUCCESS = "Cập nhật danh mục thành công";
    public static String INFO_DELETE_CATEGORY_SUCCESS = "Xóa danh mục thành công";
    public static String WARNING_CATEGORY_NAME_DUPLICATED = "Danh mục đã tồn tại";
    public static String WARNING_CATEGORY_CAN_NOT_DELETE = "Danh mục đang được sử dụng, không thể xóa";

    //    Product CRUD Message
    public static String INFO_CREATE_PRODUCT_SUCCESS = "Thêm mới sản phẩm thành công";
    public static String INFO_UPDATE_PRODUCT_SUCCESS = "Cập nhật sản phẩm thành công";
    public static String INFO_DELETE_PRODUCT_SUCCESS = "Xóa sản phẩm thành công";
    public static String WARNING_PRODUCT_CAN_NOT_DELETE = "Sản phẩm nằm trong hóa đơn trạng thái chờ, không thể xóa";
    public static String WARNING_CREATE_PRODUCT_MUST_HAVE_COLOR = "Sản phẩm phải có ít nhất 1 màu sắc";
    public static String WARNING_CREATE_PRODUCT_MUST_HAVE_FEATURE = "Sản phẩm phải có ít nhất 1 tính năng";
    public static String WARNING_PRODUCT_NAME_DUPLICATED = "Tên sản phẩm bị trùng";
    public static String WARNING_CREATE_PRODUCT_IMPORT_PRICE_MUST_LESS_THAN_EXPORT_PRICE = "Gía nhập phải nhỏ hơn giá bán";

    //    Customer CRUD Message
    public static String INFO_CREATE_CUSTOMER_SUCCESS = "Thêm mới khách hàng thành công";
    public static String INFO_UPDATE_CUSTOMER_SUCCESS = "Cập nhật khách hàng thành công";
    public static String WARNING_PHONE_NUMBER_DUPLICATED = "Số điện thoại đã tồn tại";
}
