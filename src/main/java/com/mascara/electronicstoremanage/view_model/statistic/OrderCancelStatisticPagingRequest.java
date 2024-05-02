package com.mascara.electronicstoremanage.view_model.statistic;

import com.mascara.electronicstoremanage.common.paging.PagingRequest;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 01/05/2024
 * Time      : 5:52 CH
 * Filename  : OrderCancelStatisticPagingRequest
 */
@Getter
@Setter
public class OrderCancelStatisticPagingRequest extends PagingRequest {
    private LocalDate dateStart;
    private LocalDate dateEnd;
}
