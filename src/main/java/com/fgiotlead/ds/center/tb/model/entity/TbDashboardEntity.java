package com.fgiotlead.ds.center.tb.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "dashboard")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TbDashboardEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    //    @Column(name = "created_time")
//    private Long createdTime;
    private String title;
    //    @Convert(converter = JsonNodeConverter.class)
//    @Column(columnDefinition = "VARCHAR")
//    private JsonNode configuration;
//    @Convert(converter = JsonNodeConverter.class)
//    @Column(name = "assigned_customers", columnDefinition = "VARCHAR")
//    private JsonNode assignedCustomers;
//    @Column(name = "search_text")
//    private String searchText;
//    @Column(columnDefinition = "VARCHAR(1000000)")
//    private String image;
//    @Column(name = "mobile_hide")
//    private Boolean mobileHide;
//    @Column(name = "mobile_order")
//    private Integer mobileOrder;
//    @Column(name = "tenant_id")
//    private UUID tenantId;
//    @Column(name = "external_id")
//    private UUID externalId;
}
