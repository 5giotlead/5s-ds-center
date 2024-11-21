package com.fgiotlead.ds.center.model.entity.schedule;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.validator.constraints.Range;

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
}