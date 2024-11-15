package com.fgiotlead.ds.center.tb.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "solution")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TbSolutionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID tenantId;

    @OneToMany
    private List<TbDashboardEntity> dashboards;
    @OneToMany
    private List<TbWidgetEntity> widgets;
    @OneToMany
    private List<TbDeviceEntity> devices;
}
