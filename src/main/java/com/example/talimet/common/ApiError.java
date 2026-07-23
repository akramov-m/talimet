package com.example.talimet.common;


public record ApiError(
        int status,
        String message
) {}