package com.fgiotlead.ds.center.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "signage_block")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignageBlockEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotBlank(message = "名稱不能為空白")
    private String name;
    private Integer interval;
    private ArrayList<String> text;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "signage_block_file",
            joinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "BLOCK_ID_FK")),
            inverseJoinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "FILE_ID_FK")))
    @Builder.Default
    private List<SignageFileEntity> files = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(foreignKey = @ForeignKey(name = "STYLE_ID_FK"), updatable = false)
    @JsonBackReference
    private SignageStyleEntity style;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SignageBlockEntity that = (SignageBlockEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
