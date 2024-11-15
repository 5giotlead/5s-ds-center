package com.fgiotlead.ds.center.model.entity.schedule;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Range;

import java.util.Objects;

@Entity
@Table(name = "signage_regular_schedule")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegularScheduleEntity extends SignageScheduleEntity {

    @Range(min = 1, max = 7, message = "星期需介於 1 到 7 之間")
    private Integer weekDay;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RegularScheduleEntity that = (RegularScheduleEntity) o;
        return this.getId() != null && Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}