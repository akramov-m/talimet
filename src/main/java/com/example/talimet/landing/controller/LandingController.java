package com.example.talimet.landing.controller;


import com.example.talimet.eduCenter.service.EduService;
import com.example.talimet.landing.dto.response.CountOfMembersResponse;
import com.example.talimet.landing.mapper.LandingMapper;
import com.example.talimet.studentEnrollment.repository.StudentEnrollmentRepository;
import com.example.talimet.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/landing")
@RequiredArgsConstructor
@Tag(
        name="Landing"
)
public class LandingController {
    private final StudentEnrollmentRepository studentEnrollmentRepository;
    private final UserService userService;
    private final EduService eduService;

    @GetMapping("/count/members")
    public ResponseEntity<CountOfMembersResponse> getCountsOfMembers(){
        Long users = userService.countOfUsers();
        Long students = studentEnrollmentRepository.countOfStudents();
        Long eduCenters = eduService.countOfEduCenter();
        CountOfMembersResponse response = LandingMapper.countsToResponse(users,students,eduCenters);
        return ResponseEntity.ok(response);
    }
}
