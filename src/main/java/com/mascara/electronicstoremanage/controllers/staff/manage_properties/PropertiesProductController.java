package com.mascara.electronicstoremanage.controllers.staff.manage_properties;

import com.mascara.electronicstoremanage.utils.FXMLLoaderUtils;
import com.mascara.electronicstoremanage.utils.StageRequestUtils;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 5:59 CH
 * Filename  : PropertiesProductController
 */
public class PropertiesProductController {
    @FXML
    private Button btnColor;
    @FXML
    private Button btnBrand;
    @FXML
    private Button btnFeature;
    @FXML
    private Button btnMaterial;

    @FXML
    public void showFormColor(Event event) {
        StageRequestUtils requestUtils = StageRequestUtils.builder()
                .url("/form/properties/form_color_property.fxml")
                .title("Quản lý màu sắc")
                .nodeOwner((Node) event.getSource())
                .width(405d)
                .height(433d)
                .build();
        FXMLLoaderUtils.getInstance().showFormChild(requestUtils);
    }

    @FXML
    public void showFormBrand(Event event) {
        StageRequestUtils requestUtils = StageRequestUtils.builder()
                .url("/form/properties/form_brand_property.fxml")
                .title("Quản lý thương hiệu")
                .nodeOwner((Node) event.getSource())
                .width(405d)
                .height(433d)
                .build();
        FXMLLoaderUtils.getInstance().showFormChild(requestUtils);
    }

    @FXML
    public void showFormFeature(Event event) {
        StageRequestUtils requestUtils = StageRequestUtils.builder()
                .url("/form/properties/form_feature_property.fxml")
                .title("Quản lý tính năng")
                .nodeOwner((Node) event.getSource())
                .width(405d)
                .height(433d)
                .build();
        FXMLLoaderUtils.getInstance().showFormChild(requestUtils);
    }

    @FXML
    public void showFormMaterial(Event event) {
        StageRequestUtils requestUtils = StageRequestUtils.builder()
                .url("/form/properties/form_material_property.fxml")
                .title("Quản lý chất liệu")
                .nodeOwner((Node) event.getSource())
                .width(405d)
                .height(433d)
                .build();
        FXMLLoaderUtils.getInstance().showFormChild(requestUtils);
    }
}

