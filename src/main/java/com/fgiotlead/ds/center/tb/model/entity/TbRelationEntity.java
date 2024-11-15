package com.fgiotlead.ds.center.tb.model.entity;

import com.fgiotlead.ds.center.tb.model.entity.pk.TbRelationPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "relation")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TbRelationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private TbRelationPK id;
}
