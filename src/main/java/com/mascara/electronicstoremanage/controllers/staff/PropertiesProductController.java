package com.mascara.electronicstoremanage.controllers.staff;

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
        StageRequestUtils requestUtils = new StageRequestUtils("/form/properties/form_color_property.fxml",
                "Quản lý màu sắc",
                (Node) event.getSource());
        FXMLLoaderUtils.getInstance().showFormChild(requestUtils);
    }

    @FXML
    public void showFormBrand(Event event) {
        StageRequestUtils requestUtils = new StageRequestUtils("/form/properties/form_brand_property.fxml",
                "Quản lý thương hiệu",
                (Node) event.getSource());
        FXMLLoaderUtils.getInstance().showFormChild(requestUtils);
    }

    @FXML
    public void showFormFeature(Event event) {
        StageRequestUtils requestUtils = new StageRequestUtils("/form/properties/form_feature_property.fxml",
                "Quản lý tính năng",
                (Node) event.getSource());
        FXMLLoaderUtils.getInstance().showFormChild(requestUtils);
    }

    @FXML
    public void showFormMaterial(Event event) {
        StageRequestUtils requestUtils = new StageRequestUtils("/form/properties/form_material_property.fxml",
                "Quản lý chất liệu",
                (Node) event.getSource());
        FXMLLoaderUtils.getInstance().showFormChild(requestUtils);
    }
}

