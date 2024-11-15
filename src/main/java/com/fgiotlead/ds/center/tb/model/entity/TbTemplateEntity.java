package com.fgiotlead.ds.center.tb.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "template")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TbTemplateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String description;
    private ArrayList<TbDashboardEntity> dashboards;
}
