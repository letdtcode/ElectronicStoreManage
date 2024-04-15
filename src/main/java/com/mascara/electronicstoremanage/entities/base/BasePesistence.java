package com.mascara.electronicstoremanage.entities.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 06/04/2024
 * Time      : 10:15 CH
 * Filename  : BasePesistence
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder(toBuilder = true)
@MappedSuperclass
public abstract class BasePesistence extends Audit<String> {
    @Column(name = "deleted", nullable = false, columnDefinition = "boolean")
    @Builder.Default
    private boolean deleted = false;
}
