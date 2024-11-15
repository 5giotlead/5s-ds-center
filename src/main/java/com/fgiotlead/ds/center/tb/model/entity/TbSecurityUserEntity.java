package com.fgiotlead.ds.center.tb.model.entity;

import lombok.*;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TbSecurityUserEntity {

    private Collection<Map<String, String>> authorities;
    private boolean enabled;
    private Map<String, String> userPrincipal;
    private UUID sessionId;
}
