package com.example.talimet.auth.dto.register.response;

public record UserVerifyPhoneResponse(
        String phoneNumber,
        String message
) {
}
