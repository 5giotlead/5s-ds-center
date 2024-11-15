package com.fgiotlead.ds.center.model.entity.schedule;//package com.fgiotlead.ds.center.model.entity.schedule;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.*;
//import org.hibernate.Hibernate;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotNull;
//import java.time.LocalDate;
//import java.util.Objects;
//
//@Entity
//@Table(name = "signage_schedule_period")
//@Getter
//@Setter
//@Builder
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//public class PeriodScheduleEntity extends SignageScheduleEntity {
//
//    @NotNull(message = "開始日期不能為空白")
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private LocalDate startDate;
//    @NotNull(message = "結束日期不能為空白")
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private LocalDate endDate;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        PeriodScheduleEntity that = (PeriodScheduleEntity) o;
//        return this.getId() != null && Objects.equals(this.getId(), that.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
//}