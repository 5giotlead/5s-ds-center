package com.fgiotlead.ds.center.component;
//
//import org.thingsboard.server.service.security.model.SecurityUser;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class EntityAuditorAware implements AuditorAware<String> {
//
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
//            return Optional.of(securityUser.getEmail());
//        } else {
//            return Optional.empty();
//        }
//    }
//}
