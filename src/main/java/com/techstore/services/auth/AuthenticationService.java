package com.techstore.services.auth;

import com.techstore.dto.AuthDto;


public interface AuthenticationService {
    String auth(final AuthDto dto);
}
