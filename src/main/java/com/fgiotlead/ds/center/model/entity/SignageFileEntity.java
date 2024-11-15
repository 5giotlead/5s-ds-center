package com.fgiotlead.ds.center.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
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
@Table(name = "file")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignageFileEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;
    private String originalName;
    private String mimeType;
    private String access;
    private String hash;

    @CreatedDate
    @Column(name = "created_time", updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;
//    @CreatedBy
//    @Column(name = "created_by", updatable = false)
//    private String createdBy;

    @ManyToMany(mappedBy = "files", cascade = CascadeType.REFRESH)
    @Builder.Default
    @JsonIgnore
    private List<SignageBlockEntity> blocks = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SignageFileEntity that = (SignageFileEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
