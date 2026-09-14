package com.example.talimet.attendance.mapper;

import com.example.talimet.attendance.dto.response.StudentAttendanceResponse;
import com.example.talimet.common.enums.AttendanceEnum;
import com.example.talimet.user.entity.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class StudentAttendanceMapper {

    public static List<StudentAttendanceResponse> entitiesToDto(List<User> students, Map<UUID, AttendanceEnum> statusMap){
        return students.stream().map(
                s->new StudentAttendanceResponse(
                        s.getId(),
                        s.getFirstName()+" " + s.getLastName(),
                        statusMap.get(s.getId())
                )
        ).toList();
    }
}
