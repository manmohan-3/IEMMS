//SHIVAAAAHHHH

package com.iemms.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.iemms.entity.UserRole;

@Service
public class CurrentUserService {

    public UserRole getCurrentUserRole() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }

        return authentication.getAuthorities()
                .stream()
                .map(authority -> authority.getAuthority())
                .filter(authority -> authority.startsWith("ROLE_"))
                .map(authority -> authority.substring(5))
                .map(UserRole::valueOf)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("User role not found"));
    }
}
