package com.fgiotlead.ds.center.tb.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "edge")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TbEdgeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "routing_key")
    private String routingKey;
    private String secret;
    private String type;
    //    private Long createdTime;
    private String name;
    private String label;
    @Column(name = "tenant_id")
    private UUID tenantId;
//    @Convert(converter = JsonNodeConverter.class)
//    @Column(columnDefinition = "VARCHAR")
//    private JsonNode additionalInfo;
//    private UUID rootRuleChainId;

}
