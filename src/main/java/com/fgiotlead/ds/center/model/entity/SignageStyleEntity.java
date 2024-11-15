package com.fgiotlead.ds.center.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fgiotlead.ds.center.model.entity.schedule.RegularScheduleEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "signage_style")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignageStyleEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotBlank(message = "名稱不能為空白")
    private String name;
    private boolean editable;

    @LastModifiedDate
    @Column(name = "modified_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedTime;

//    @LastModifiedBy
//    @Column(name = "modified_by")
//    private String modifiedBy;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(foreignKey = @ForeignKey(name = "TEMPLATE_ID_FK"))
    private SignageTemplateEntity template;

    @OneToMany(mappedBy = "style", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @OrderBy("name")
    @Builder.Default
    @JsonManagedReference
    private List<SignageBlockEntity> blocks = new ArrayList<>();

    @OneToMany(mappedBy = "style", cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
//    @Schema(hidden = true)
    @Builder.Default
    @JsonIgnore
    private List<RegularScheduleEntity> schedules = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SignageStyleEntity that = (SignageStyleEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
