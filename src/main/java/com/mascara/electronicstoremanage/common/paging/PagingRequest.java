package com.mascara.electronicstoremanage.common.paging;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:33 CH
 * Filename  : PagingRequest
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class PagingRequest {
    private int pageIndex = 1;
    private int pageSize = 1000;
    private String sortBy = null;
    private List<String> columnName;
    private String typeSort = "ASC";
    private String keyword = null;
    private String condition = null;
}
