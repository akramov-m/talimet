package com.example.talimet.lessonDays.service.impl;

import com.example.talimet.common.enums.DaysOfWeek;
import com.example.talimet.common.exception.BadRequestException;
import com.example.talimet.group.entity.Group;
import com.example.talimet.lessonDays.dto.request.LessonDayCreateDto;
import com.example.talimet.lessonDays.entity.LessonDays;
import com.example.talimet.lessonDays.repository.LessonDaysRepository;
import com.example.talimet.lessonDays.service.LessonDaysService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonDaysServiceImpl implements LessonDaysService {
    private final LessonDaysRepository lessonDaysRepository;


    @Override
    public List<LessonDays> create(Group group, List<LessonDayCreateDto> dto) {
        List<LessonDays> days = dto.stream()
                .map(day->{
                    DaysOfWeek validatedDay = parseDay(day.day());
                    LessonDays lessonDay = new LessonDays();
                    lessonDay.setDay(validatedDay);
                    lessonDay.setGroup(group);
                    return lessonDay;
                }).toList();
         return lessonDaysRepository.saveAll(days);
    }

    private DaysOfWeek parseDay(String value) {
        try {
            return DaysOfWeek.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Incorrect value: " + value);
        }
    }
    @Override
    public List<LessonDays> getLessonDaysByGroup(UUID groupId) {
        return lessonDaysRepository.getLessonDaysByGroup(groupId);
    }
}
