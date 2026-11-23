package org.petcare.petcare.Response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public record LoginResponse(
        String accessToken,
        String tokenType,
        Long id,
        String email,
        Collection<? extends GrantedAuthority> rolls
) {}