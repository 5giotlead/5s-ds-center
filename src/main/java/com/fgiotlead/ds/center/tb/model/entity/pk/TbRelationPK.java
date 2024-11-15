package com.fgiotlead.ds.center.tb.model.entity.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TbRelationPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "from_id")
    private UUID fromId;
    @Column(name = "to_id")
    private UUID toId;
}
