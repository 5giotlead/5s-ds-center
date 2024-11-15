package com.fgiotlead.ds.center.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fgiotlead.ds.center.model.enumEntity.DownlinkStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
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
@Table(name = "signage_edge")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignageEdgeEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull(message = "Edge ID 不能為空白")
    private UUID id;
    private boolean enable;
    private DownlinkStatus status;

    @CreatedDate
    @Column(name = "created_time", updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

//    @CreatedBy
//    @Column(name = "created_by", updatable = false)
//    private String createdBy;

    @OneToMany(mappedBy = "edge", cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @Builder.Default
    @ToString.Exclude
    @JsonManagedReference
    private List<SignageProfileEntity> profiles = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SignageEdgeEntity that = (SignageEdgeEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
