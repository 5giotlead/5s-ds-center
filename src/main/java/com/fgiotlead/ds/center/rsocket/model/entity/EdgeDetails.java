package com.fgiotlead.ds.center.rsocket.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class EdgeDetails extends User {

    private UUID edgeId;

    public EdgeDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, UUID edgeId) {
        super(username, password, authorities);
        this.edgeId = edgeId;
    }
}



