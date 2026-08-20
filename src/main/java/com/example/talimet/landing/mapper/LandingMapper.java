package com.example.talimet.landing.mapper;

import com.example.talimet.landing.dto.response.CountOfMembersResponse;

public class LandingMapper {
    public static CountOfMembersResponse countsToResponse(Long users,Long students,Long eduCenter){
        return new CountOfMembersResponse(
                users,
                students,
                eduCenter
        );
    }
}
