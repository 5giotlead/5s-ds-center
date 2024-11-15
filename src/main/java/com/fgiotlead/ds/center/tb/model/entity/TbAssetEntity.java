package com.fgiotlead.ds.center.tb.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "asset")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TbAssetEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    //    private Long createdTime;
    private String name;
//    private String type;
//    private String label;
    //    @Convert(converter = JsonNodeConverter.class)
//    @Column(columnDefinition = "VARCHAR")
//    private JsonNode additionalInfo;
//    private String searchText;
//    private UUID assetProfileId;
//    private UUID customerId;
//    private UUID tenantId;
//    private UUID externalId;
}
