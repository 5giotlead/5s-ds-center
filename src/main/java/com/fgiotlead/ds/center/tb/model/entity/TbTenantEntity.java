package com.fgiotlead.ds.center.tb.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tenant")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TbTenantEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    //    private Long createdTime;
    private String title;
//    private String email;
//    private String phone;
//    private String region;
//    private String state;
//    private String zip;
//    private String country;
//    private String city;
//    @Column(columnDefinition = "VARCHAR")
//    private String address;
//    @Column(columnDefinition = "VARCHAR")
//    private String address2;
    //    @Convert(converter = JsonNodeConverter.class)
//    @Column(columnDefinition = "VARCHAR")
//    private JsonNode additionalInfo;
//    private UUID tenantProfileId;
}