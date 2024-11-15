package com.fgiotlead.ds.center.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "signage_template")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignageTemplateEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotBlank(message = "名稱不能為空白")
    private String name;
    private HashMap<String, String> blocksType;

    @OneToMany(mappedBy = "template", cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
//    @Schema(hidden = true)
    @Builder.Default
    @JsonIgnore
    private List<SignageStyleEntity> styles = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SignageTemplateEntity that = (SignageTemplateEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
