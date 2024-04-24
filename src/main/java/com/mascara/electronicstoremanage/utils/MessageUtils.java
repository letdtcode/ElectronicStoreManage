package com.mascara.electronicstoremanage.utils;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 5:09 CH
 * Filename  : MessageUtils
 */
public class MessageUtils {
    public static final String TITLE_SUCCESS = "Thông báo thành công";
    public static final String TITLE_FAILED = "Thông báo thất bại";

    public static final String WARNING_DATA_NOT_VALID = "Vui lòng nhập đầy đủ dữ liệu";
    public static final String WARNING_SELECT_ROW = "Vui lòng chọn 1 hàng để cập nhật";
    public static final String WARNING_CART_EMPTY = "Giỏ hàng hiện tại trống";
    public static final String WARNING_HAS_ERROR_OCCURRED = "Đã có lỗi xảy ra";

    //    Brand CRUD Message
    public static final String INFO_CREATE_BRAND_SUCCESS = "Thêm mới thương hiệu thành công";
    public static final String INFO_UPDATE_BRAND_SUCCESS = "Cập nhật thương hiệu thành công";
    public static final String INFO_DELETE_BRAND_SUCCESS = "Xóa thương hiệu thành công";
    public static final String WARNING_BRAND_NAME_DUPLICATED = "Tên thương hiệu đã tồn tại";
    public static final String WARNING_BRAND_CAN_NOT_DELETE = "Thương hiệu đang được sử dụng, không thể xóa";

    //    Color CRUD Message
    public static final String INFO_CREATE_COLOR_SUCCESS = "Thêm mới màu sắc thành công";
    public static final String INFO_UPDATE_COLOR_SUCCESS = "Cập nhật màu sắc thành công";
    public static final String INFO_DELETE_COLOR_SUCCESS = "Xóa màu sắc thành công";
    public static final String WARNING_COLOR_NAME_DUPLICATED = "Màu sắc đã tồn tại";
    public static final String WARNING_COLOR_CAN_NOT_DELETE = "Màu sắc đang được sử dụng, không thể xóa";

    //    Feature CRUD Message
    public static final String INFO_CREATE_FEATURE_SUCCESS = "Thêm mới tính năng thành công";
    public static final String INFO_UPDATE_FEATURE_SUCCESS = "Cập nhật tính năng thành công";
    public static final String INFO_DELETE_FEATURE_SUCCESS = "Xóa tính năng thành công";
    public static final String WARNING_FEATURE_NAME_DUPLICATED = "Tính năng đã tồn tại";
    public static final String WARNING_FEATURE_CAN_NOT_DELETE = "Tính năng đang được sử dụng, không thể xóa";

    //    Material CRUD Message
    public static final String INFO_CREATE_MATERIAL_SUCCESS = "Thêm mới chất liệu thành công";
    public static final String INFO_UPDATE_MATERIAL_SUCCESS = "Cập nhật chất liệu thành công";
    public static final String INFO_DELETE_MATERIAL_SUCCESS = "Xóa chất liệu thành công";
    public static final String WARNING_MATERIAL_NAME_DUPLICATED = "Chất liệu đã tồn tại";
    public static final String WARNING_MATERIAL_CAN_NOT_DELETE = "Chất liệu đang được sử dụng, không thể xóa";

    //    Category CRUD Message
    public static final String INFO_CREATE_CATEGORY_SUCCESS = "Thêm mới danh mục thành công";
    public static final String INFO_UPDATE_CATEGORY_SUCCESS = "Cập nhật danh mục thành công";
    public static final String INFO_DELETE_CATEGORY_SUCCESS = "Xóa danh mục thành công";
    public static final String WARNING_CATEGORY_NAME_DUPLICATED = "Danh mục đã tồn tại";
    public static final String WARNING_CATEGORY_CAN_NOT_DELETE = "Danh mục đang được sử dụng, không thể xóa";

    //    Product CRUD Message
    public static final String INFO_CREATE_PRODUCT_SUCCESS = "Thêm mới sản phẩm thành công";
    public static final String INFO_UPDATE_PRODUCT_SUCCESS = "Cập nhật sản phẩm thành công";
    public static final String INFO_DELETE_PRODUCT_SUCCESS = "Xóa sản phẩm thành công";
    public static final String WARNING_PRODUCT_CAN_NOT_DELETE = "Sản phẩm nằm trong hóa đơn trạng thái chờ, không thể xóa";
    public static final String WARNING_CREATE_PRODUCT_MUST_HAVE_COLOR = "Sản phẩm phải có ít nhất 1 màu sắc";
    public static final String WARNING_CREATE_PRODUCT_MUST_HAVE_FEATURE = "Sản phẩm phải có ít nhất 1 tính năng";
    public static final String WARNING_PRODUCT_NAME_DUPLICATED = "Tên sản phẩm bị trùng";
    public static final String WARNING_CREATE_PRODUCT_IMPORT_PRICE_MUST_LESS_THAN_EXPORT_PRICE = "Gía nhập phải nhỏ hơn giá bán";

    //    Customer CRUD Message
    public static final String INFO_CREATE_CUSTOMER_SUCCESS = "Thêm mới khách hàng thành công";
    public static final String INFO_UPDATE_CUSTOMER_SUCCESS = "Cập nhật khách hàng thành công";
    public static final String WARNING_PHONE_NUMBER_DUPLICATED = "Số điện thoại đã tồn tại";

    //    Sale Message
    public static final String INFO_CREATE_ORDER_SUCCESS = "Tạo hóa đơn mới thành công";
    public static final String INFO_ADD_CART_ITEM_SUCCESS = "Đã thêm sản phẩm vào giỏ hàng";
    public static final String INFO_CHANGE_QUANTITY_PRODUCT_IN_CART_SUCCESS = "Thay đối số lượng thành công";
    public static final String INFO_DELETE_CART_ITEM_SUCCESS = "Đã xóa sản phẩm khỏi giỏ hàng";
    public static final String INFO_DELETE_ALL_CART_ITEM_SUCCESS = "Đã xóa tất cả sản phẩm trong hóa đơn đang chờ";
    public static final String WARNING_MUST_CHOOSE_PRODUCT_AND_ORDER = "Vui lòng chọn sản phẩm muốn thêm vào giỏ";
    public static final String WARNING_PRODUCT_NOT_ENOUGH = "Số lượng sản phẩm không đủ";


    //    public static String INFO_UPDATE_CUSTOMER_SUCCESS = "Cập nhật khách hàng thành công";

    //    Discount CRUD Message
    public static final String INFO_CREATE_DISCOUNT_SUCCESS = "Thêm mới nhân viên thành công";
    public static final String INFO_UPDATE_DISCOUNT_SUCCESS = "Cập nhật nhân viên thành công";
    public static final String INFO_DELETE_DISCOUNT_SUCCESS = "Xóa nhân viên thành công";
    public static final String WARNING_PRODUCT_RANGE_DATE_OVERLAP = "Sản phẩm được chọn bị trùng thời gian áp dụng giảm giá, vui lòng kiểm tra lại";
    public static final String WARNING_DATE_RANGE_CHOOSE_NOT_VALID = "Thời gian bắt đầu phải trước thời gian kết thúc";
    public static final String WARNING_DISCOUNT_PERCENT_CAN_NOT_OVER_100 = "Mức giảm giá không được vượt quá 100%";
    public static final String WARNING_DISCOUNT_VALUE_CAN_NOT_OVER_PRICE_PRODUCT = "Mức giảm giá phải thấp hơn giá của các sản phẩm. Vui lòng kiểm tra lại";


    //    public static String WARNING_PASSWORD_NOT_ENOUGH_STRONG = "Password phải từ 8-20 kí tự bao gồm chữ cái viết hóa, thường, số và kí tự đặc biệt";


    //    Staff CRUD Message
    public static final String INFO_CREATE_STAFF_SUCCESS = "Thêm mới nhân viên thành công";
    public static final String INFO_UPDATE_STAFF_SUCCESS = "Cập nhật nhân viên thành công";
    public static final String INFO_DELETE_STAFF_SUCCESS = "Xóa nhân viên thành công";
    public static final String WARNING_USER_NAME_OR_PHONE_NUMBER_DUPLICATED = "User name hoặc số điện thoại đã tồn tại";
    public static final String WARNING_PASSWORD_NOT_ENOUGH_STRONG = "Password phải từ 8-20 kí tự bao gồm chữ cái viết hóa, thường, số và kí tự đặc biệt";
}
