package com.fgiotlead.ds.center.tb.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "widget_type")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TbWidgetEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    //    @Column(name = "created_time")
//    private Long createdTime;
    private String name;
//    private String description;
//    private String alias;
//    @Column(name = "bundle_alias")
//    private String bundleAlias;
//    @Convert(converter = JsonNodeConverter.class)
//    @Column(columnDefinition = "VARCHAR(1000000)")
//    private JsonNode descriptor;
//    @Column(columnDefinition = "VARCHAR(1000000)")
//    private String image;
//    @Column(name = "tenant_id")
//    private UUID tenantId;
}
