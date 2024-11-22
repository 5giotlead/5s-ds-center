package com.fgiotlead.ds.center.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fgiotlead.ds.center.model.entity.schedule.RegularScheduleEntity;
import com.fgiotlead.ds.center.model.enumEntity.SignageType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "signage_profile")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignageProfileEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull(message = "設備 ID 不能為空白")
    private UUID id;
    @NotNull
    private SignageType type;
//    private HashMap<String, String> lastPlayInfo;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(foreignKey = @ForeignKey(name = "EDGE_ID_FK"))
    @JsonBackReference
    private SignageEdgeEntity edge;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("weekDay")
    @Builder.Default
    @ToString.Exclude
    @JsonManagedReference
    private List<RegularScheduleEntity> schedules = new ArrayList<>();


}
