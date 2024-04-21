package com.mascara.electronicstoremanage.utils;

import javafx.scene.Node;
import lombok.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:02 CH
 * Filename  : StageRequestUtils
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StageRequestUtils {
    private String url;
    private String title;
    private Node nodeOwner;
    private Double width;
    private Double height;
}
