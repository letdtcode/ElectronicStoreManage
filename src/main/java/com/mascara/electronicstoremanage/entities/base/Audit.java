package com.mascara.electronicstoremanage.entities.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 06/04/2024
 * Time      : 10:15 CH
 * Filename  : Audit
 */
@Getter
@Setter
@MappedSuperclass
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Audit<U extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "created_by", length = 50, updatable = false, nullable = false)
    @Builder.Default
    private U createdBy = (U) getActorName();

    @Column(name = "created_date", updatable = false, nullable = false)
    @Builder.Default
    private LocalDateTime createdDate = getLocalDateTimeGMT7();

    @Column(name = "last_modified_by", length = 50)
    @Builder.Default
    private U lastModifiedBy = (U) getActorName();

    @Column(name = "last_modified_date")
    @Builder.Default
    private LocalDateTime lastModifiedDate = getLocalDateTimeGMT7();

    private static String getActorName() {
        return "anonymousUser";
    }

    private static LocalDateTime getLocalDateTimeGMT7() {
        DateTimeZone timeZone = DateTimeZone.forID("Asia/Ho_Chi_Minh");
        DateTime now = DateTime.now(timeZone);

        return LocalDateTime.of(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), now.getHourOfDay(), now.getMinuteOfHour(), now.getSecondOfMinute());
    }
}
