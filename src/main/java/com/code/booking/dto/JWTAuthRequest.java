package com.code.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTAuthRequest {

    private String token;
    private String refreshToken;
}
