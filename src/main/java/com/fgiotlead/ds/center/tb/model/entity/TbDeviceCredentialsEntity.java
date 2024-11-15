package com.fgiotlead.ds.center.tb.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "device_credentials")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TbDeviceCredentialsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    //    @Column(name = "created_time")
//    private Long createdTime;
    @Column(name = "credentials_type")
    private String credentialsType;
    @Column(name = "credentials_id", columnDefinition = "VARCHAR")
    private String credentialsId;
    @Column(name = "credentials_value", columnDefinition = "VARCHAR")
    private String credentialsValue;
    @Column(name = "device_id")
    private UUID deviceId;
}
