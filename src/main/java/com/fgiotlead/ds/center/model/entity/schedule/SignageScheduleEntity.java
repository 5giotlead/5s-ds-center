package com.fgiotlead.ds.center.model.entity.schedule;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fgiotlead.ds.center.model.entity.SignageProfileEntity;
import com.fgiotlead.ds.center.model.entity.SignageStyleEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@EqualsAndHashCode
public abstract class SignageScheduleEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull(message = "開始時間不能為空白")
    @JsonFormat(pattern = "HH:mm")
    @Schema(type = "string", format = "HH:mm", example = "00:00")
    private LocalTime startTime;
    @NotNull(message = "結束時間不能為空白")
    @JsonFormat(pattern = "HH:mm")
    @Schema(type = "string", format = "HH:mm", example = "23:59")
    private LocalTime endTime;

    @LastModifiedDate
    @Column(name = "modified_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedTime;

//    @LastModifiedBy
//    @Column(name = "modified_by")
//    private String modifiedBy;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(foreignKey = @ForeignKey(name = "STYLE_ID_FK"))
    private SignageStyleEntity style;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(foreignKey = @ForeignKey(name = "PROFILE_ID_FK"))
    @JsonBackReference
    private SignageProfileEntity profile;

}