package com.fgiotlead.ds.center.model.entity;//package com.fgiotlead.ds.center.model.entity;
//
//import com.fgiotlead.ds.center.model.enumEntity.SettingsStatus;
//import lombok.*;
//
//import jakarta.persistence.*;
//import java.io.Serializable;
//import java.util.UUID;
//
//@Entity
//@Table(name = "signage_edge_details")
//@Getter
//@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class SignageEdgeDetailsEntity implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private UUID id;
//    private SettingsStatus status;
//
//    @OneToOne(cascade = CascadeType.REFRESH)
//    @JoinColumn(foreignKey = @ForeignKey(name = "EDGE_ID_FK"))
//    private SignageEdgeEntity edge;
//}
